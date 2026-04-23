package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.dto.ReportePresupuestoDTO;
import com.escuela.planeacion.backend.service.ProyectoService;
import com.escuela.planeacion.backend.service.ReportePresupuestoService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReportePresupuestoController {

    private final ReportePresupuestoService service;
    private final ProyectoService proyectoService;

    public ReportePresupuestoController(ReportePresupuestoService service, ProyectoService proyectoService) {
        this.service = service;
        this.proyectoService = proyectoService;
    }

    @GetMapping("/presupuesto/{idProyecto}")
    public ResponseEntity<byte[]> exportarPresupuesto(@PathVariable Integer idProyecto) throws Exception {

        List<ReportePresupuestoDTO> datos = service.obtenerReportePorProyecto(idProyecto);
        String nombreProyecto = proyectoService.obtenerNombreProyecto(idProyecto);

        Map<String, List<ReportePresupuestoDTO>> actividades = datos.stream()
                .collect(Collectors.groupingBy(ReportePresupuestoDTO::getActividad));

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Presupuesto");

            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 14);
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            DataFormat format = workbook.createDataFormat();
            CellStyle numberStyle = workbook.createCellStyle();
            numberStyle.setDataFormat(format.getFormat("#,##0"));

            CellStyle totalStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            totalStyle.setFont(boldFont);
            totalStyle.setDataFormat(format.getFormat("#,##0"));

            Row titulo = sheet.createRow(0);
            Cell cellTitulo = titulo.createCell(0);
            cellTitulo.setCellValue("Detalle del presupuesto planeado.");
            cellTitulo.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 6));

            Row filaId = sheet.createRow(1);
            filaId.createCell(0).setCellValue("ID Proyecto: " + idProyecto);

            Row filaNombre = sheet.createRow(2);
            filaNombre.createCell(0).setCellValue("Nombre del Proyecto: " + nombreProyecto);

            int rowIndex = 4;

            Row header = sheet.createRow(rowIndex++);
            header.createCell(0).setCellValue("Actividad / Año");
            int colIndex = 1;

            List<Integer> anios = datos.stream()
                    .map(ReportePresupuestoDTO::getAnio)
                    .distinct()
                    .sorted()
                    .toList();

            for (Integer anio : anios) {
                header.createCell(colIndex++).setCellValue(anio + " - Dedicación Personal");
                header.createCell(colIndex++).setCellValue(anio + " - Presupuesto Erogación");
            }
            header.createCell(colIndex).setCellValue("Subtotal");
            header.createCell(colIndex + 1).setCellValue("Total Actividad");

            for (Cell cell : header) {
                cell.setCellStyle(headerStyle);
            }

            for (var entry : actividades.entrySet()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(entry.getKey()); // nombre actividad

                colIndex = 1;
                long subtotal = 0L;
                for (Integer anio : anios) {
                    ReportePresupuestoDTO match = entry.getValue().stream()
                            .filter(r -> r.getAnio().equals(anio))
                            .findFirst().orElse(null);

                    long dedicacion = match != null ? match.getDedicacionPersonal() : 0L;
                    long erogacion = match != null ? match.getPresupuestoErogacion() : 0L;

                    Cell cellD = row.createCell(colIndex++);
                    cellD.setCellValue(dedicacion);
                    cellD.setCellStyle(numberStyle);

                    Cell cellE = row.createCell(colIndex++);
                    cellE.setCellValue(erogacion);
                    cellE.setCellStyle(numberStyle);

                    subtotal += dedicacion + erogacion;
                }

                Cell cellSubtotal = row.createCell(colIndex++);
                cellSubtotal.setCellValue(subtotal);
                cellSubtotal.setCellStyle(numberStyle);

                Cell cellTotal = row.createCell(colIndex);
                cellTotal.setCellValue(subtotal); // mismo valor que subtotal
                cellTotal.setCellStyle(numberStyle);
            }

            Row totalRow = sheet.createRow(rowIndex++);
            Cell totalLabel = totalRow.createCell(0);
            totalLabel.setCellValue("TOTAL GENERAL");
            totalLabel.setCellStyle(totalStyle);

            colIndex = 1;
            for (Integer anio : anios) {
                long totalDedicacion = datos.stream()
                        .filter(r -> r.getAnio().equals(anio))
                        .mapToLong(ReportePresupuestoDTO::getDedicacionPersonal)
                        .sum();

                long totalErogacion = datos.stream()
                        .filter(r -> r.getAnio().equals(anio))
                        .mapToLong(ReportePresupuestoDTO::getPresupuestoErogacion)
                        .sum();

                Cell cellD = totalRow.createCell(colIndex++);
                cellD.setCellValue(totalDedicacion);
                cellD.setCellStyle(totalStyle);

                Cell cellE = totalRow.createCell(colIndex++);
                cellE.setCellValue(totalErogacion);
                cellE.setCellStyle(totalStyle);
            }

            long granTotal = datos.stream()
                    .mapToLong(d -> d.getDedicacionPersonal() + d.getPresupuestoErogacion())
                    .sum();

            Cell cellSubtotal = totalRow.createCell(colIndex++);
            cellSubtotal.setCellValue(granTotal);
            cellSubtotal.setCellStyle(totalStyle);

            Cell cellTotal = totalRow.createCell(colIndex);
            cellTotal.setCellValue(granTotal);
            cellTotal.setCellStyle(totalStyle);

            for (int i = 0; i <= colIndex; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=presupuesto.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(out.toByteArray());
        }
    }
}
