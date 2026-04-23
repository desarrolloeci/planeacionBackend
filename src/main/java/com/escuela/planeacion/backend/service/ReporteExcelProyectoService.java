package com.escuela.planeacion.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteExcelProyectoService {

    private final EntityManager em;

    public byte[] generarExcelProyecto(Long idProyecto) throws IOException {
        ProyectoRow proyecto = obtenerProyecto(idProyecto);
        List<ObjetivoRow> objetivos = obtenerObjetivos(idProyecto);
        List<IndicadorRow> indicadoresProyecto = obtenerIndicadoresProyecto(idProyecto);
        List<ActividadRow> actividades = obtenerActividades(idProyecto);
        Map<Long, List<IndicadorRow>> indicadoresPorActividad = obtenerIndicadoresPorActividad(idProyecto);
        Map<Long, List<ErogacionRow>> erogacionesPorActividad = obtenerErogaciones(idProyecto);
        Map<Long, List<PersonalRow>> personalPorActividad = obtenerPersonalHoras(idProyecto);

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sh = wb.createSheet("Proyecto");

            // ===== Estilos =====
            CellStyle stTituloPrincipal = estiloTituloPrincipal(wb);
            CellStyle stTituloSeccion   = estiloTituloSeccion(wb);
            CellStyle stSubTituloAct    = estiloSubtituloActividad(wb);
            CellStyle stHeaderTabla     = estiloHeaderTabla(wb);
            CellStyle stValor           = estiloValor(wb);
            CellStyle stWrap            = estiloWrap(wb);
            CellStyle stFilaPar         = estiloFilaPar(wb);
            CellStyle stFilaImpar       = estiloFilaImpar(wb);

            int r = 0;

            // ===== Encabezado principal =====
            r = tituloPrincipal(sh, r, "Resumen del Proyecto", stTituloPrincipal);

            r = kv(sh, r, "ID Proyecto", safe(proyecto.idProyecto == null ? null : proyecto.idProyecto.toString()), stTituloSeccion, stValor);
            r = kv(sh, r, "Nombre del Proyecto", safe(proyecto.nombre), stTituloSeccion, stValor);
            r = kv(sh, r, "Tipo", safe(proyecto.tipo), stTituloSeccion, stValor);
            r = kv(sh, r, "Director (cc)", safe(proyecto.ccDirector), stTituloSeccion, stValor);
            r = kv(sh, r, "Responsable (cc)", safe(proyecto.ccResponsable), stTituloSeccion, stValor);
            r = kv(sh, r, "Unidad Ejecutora", safe(proyecto.unidadEjecutoraNombre), stTituloSeccion, stValor);
            r = kv(sh, r, "Plan", safe(proyecto.planNombre), stTituloSeccion, stValor);
            r = kv(sh, r, "Vigencia", rango(proyecto.fechaInicio, proyecto.fechaFin), stTituloSeccion, stValor);
            r = kv(sh, r, "Estado", safe(proyecto.estado), stTituloSeccion, stValor);
            r = kv(sh, r, "Estado ejecución", safe(proyecto.estadoEjec), stTituloSeccion, stValor);
            r = kv(sh, r, "Valor proyectado", formatoMoneda(proyecto.valorProy), stTituloSeccion, stValor);
            r = kv(sh, r, "Valor ejecutado", formatoMoneda(proyecto.valorEjec), stTituloSeccion, stValor);
            r = kvWrap(sh, r, "Justificación", safe(proyecto.justificacion), stTituloSeccion, stWrap);
            r += 2;

            // ===== Objetivos =====
            
            
            
            
            r = tituloSeccion(sh, r, "Objetivos", stTituloSeccion);
            r = tablaSimple(sh, r, stHeaderTabla, stValor,
                    new String[]{"Tipo", "Descripción"},
                    objetivos.stream().map(o -> new String[]{
                            tipoObjetivo(o.tipo), safe(o.descripcion)
                    }).collect(Collectors.toList()), stFilaPar, stFilaImpar);
            r += 2;

            // ===== Indicadores del proyecto =====
            r = tituloSeccion(sh, r, "Indicadores del Proyecto", stTituloSeccion);
            r = tablaSimple(sh, r, stHeaderTabla, stValor,
                    new String[]{"Nombre", "Tipo", "Periodicidad", "Cálculo", "Descripción del cálculo"},
                    indicadoresProyecto.stream().map(i -> new String[]{
                            safe(i.nombre), safeNum(i.tipo), safe(i.periodicidad), safeNum(i.calculo), safe(i.descripcionCal)
                    }).collect(Collectors.toList()), stFilaPar, stFilaImpar);
            r += 2;

            // ===== Actividades =====
            r = tituloSeccion(sh, r, "Actividades", stTituloSeccion);
            for (ActividadRow a : actividades) {
                r = subTituloActividad(sh, r, a.nombreact, stSubTituloAct);

                r = kvWrap(sh, r, "Descripción", safe(a.descripcion), stTituloSeccion, stWrap);
                r = kv(sh, r, "Fechas", rango(a.fechaIni, a.fechaFin), stTituloSeccion, stValor);
                r = kv(sh, r, "Responsable", safe(a.responsable), stTituloSeccion, stValor);
                r = kv(sh, r, "% Ejecución", safeNum(a.porcEjec), stTituloSeccion, stValor);
                r += 1;

                List<IndicadorRow> indsAct = indicadoresPorActividad.getOrDefault(a.idActividad, Collections.emptyList());
                if (!indsAct.isEmpty()) {
                    r = subTituloTabla(sh, r, "Indicadores asociados a la actividad", stHeaderTabla);
                    r = tablaSimple(sh, r, stHeaderTabla, stValor,
                            new String[]{"Nombre", "Tipo", "Periodicidad", "Cálculo", "Descripción del cálculo"},
                            indsAct.stream().map(i -> new String[]{
                                    safe(i.nombre), safeNum(i.tipo), safe(i.periodicidad), safeNum(i.calculo), safe(i.descripcionCal)
                            }).collect(Collectors.toList()), stFilaPar, stFilaImpar);
                    r += 1;
                }

                List<PersonalRow> pers = personalPorActividad.getOrDefault(a.idActividad, Collections.emptyList());
                if (!pers.isEmpty()) {
                    r = subTituloTabla(sh, r, "Personal (con horas por año)", stHeaderTabla);
                    r = tablaSimple(sh, r, stHeaderTabla, stValor,
                            new String[]{"Participante", "Cargo", "Estado (num)", "Año", "Horas"},
                            pers.stream().map(p -> new String[]{
                                    safe(p.nombre), safe(p.cargo), safeNum(p.estado), safeNum(p.agno), safeNum(p.horas)
                            }).collect(Collectors.toList()), stFilaPar, stFilaImpar);
                    r += 1;
                }

                List<ErogacionRow> eros = erogacionesPorActividad.getOrDefault(a.idActividad, Collections.emptyList());
                if (!eros.isEmpty()) {
                    r = subTituloTabla(sh, r, "Erogaciones", stHeaderTabla);
                    r = tablaSimple(sh, r, stHeaderTabla, stValor,
                            new String[]{"Año", "Tipo Rubro", "Rubro", "Valor", "Observación"},
                            eros.stream().map(e -> new String[]{
                                    safeNum(e.agno), safe(e.tipoRubro), safeNum(e.rubro), formatoMoneda(e.valor), safe(e.observacion)
                            }).collect(Collectors.toList()), stFilaPar, stFilaImpar);
                    r += 1;
                }

                r += 2; // separación entre actividades
            }

            // Autoajustar columnas útiles
            for (int c = 0; c < 10; c++) {
                sh.autoSizeColumn(c);
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            wb.write(bos);
            return bos.toByteArray();
        }
    }

    // =====================================================
    //  CONSULTAS A BD (idénticas a tu versión original)
    // =====================================================

    private ProyectoRow obtenerProyecto(Long idProyecto) {
        String sql = """
           SELECT 
              p.idproyecto, p.nombrepr, p.tipopr, p.ccdirectorpr, p.ccresponsablepr,
              p.metapr, p.justificacionpr, p.Prioridadpr,
              CONVERT(varchar, p.fechainipr, 23) AS fechaini,
              CONVERT(varchar, p.fechafinpr, 23) AS fechafin,
              pare.valor as estadopr, p.valorproyectadopr, p.valorejecutadopr,
              parej.valor as estadoejecucion,
              p.unidadejecutora,
              ue.nombreunidad,
              p.idplan,
              pl.nombrepl
            FROM [Planeacion].[odi].[Proyecto] p
            LEFT JOIN [Planeacion].[odi].[UnidadEjecutora] ue 
                   ON TRY_CONVERT(int, p.unidadejecutora) = ue.idunidadej
            LEFT JOIN [Planeacion].[odi].[Planes] pl
                   ON pl.idplan = p.idplan
            LEFT JOIN [Planeacion].[odi].[Parametros] pare on pare.secuencial = p.estadopr and pare.tipo=1    
            LEFT JOIN [Planeacion].[odi].[Parametros] parej on parej.secuencial = p.estadoejecucion and parej.tipo=2     
            WHERE p.idproyecto = :idProyecto
        """;
        Query q = em.createNativeQuery(sql);
        q.setParameter("idProyecto", idProyecto);
        Object[] r = (Object[]) q.getSingleResult();

        ProyectoRow row = new ProyectoRow();
        int i = 0;
        row.idProyecto = toLong(r[i++]);
        row.nombre = toStr(r[i++]);
        row.tipo = toStr(r[i++]);
        row.ccDirector = toStr(r[i++]);
        row.ccResponsable = toStr(r[i++]);
        row.meta = toStr(r[i++]);
        row.justificacion = toStr(r[i++]);
        row.prioridad = toInt(r[i++]);
        row.fechaInicio = toStr(r[i++]);
        row.fechaFin = toStr(r[i++]);
        row.estado = toStr(r[i++]);
        row.valorProy = toBig(r[i++]);
        row.valorEjec = toBig(r[i++]);
        row.estadoEjec = toStr(r[i++]);
        row.unidadEjecutoraCodigo = toStr(r[i++]);
        row.unidadEjecutoraNombre = toStr(r[i++]);
        row.idPlan = toLong(r[i++]);
        row.planNombre = toStr(r[i++]);
        return row;
    }

    private List<ObjetivoRow> obtenerObjetivos(Long idProyecto) {
        String sql = """
            SELECT tipoob, descripcionob
            FROM [Planeacion].[odi].[Objetivo]
            WHERE idproyecto = :idProyecto
            ORDER BY tipoob, idobjetivo
        """;
        Query q = em.createNativeQuery(sql);
        q.setParameter("idProyecto", idProyecto);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        List<ObjetivoRow> out = new ArrayList<>();
        for (Object[] r : rows) {
            ObjetivoRow o = new ObjetivoRow();
            o.tipo = toInt(r[0]);
            o.descripcion = toStr(r[1]);
            out.add(o);
        }
        return out;
    }

    private List<IndicadorRow> obtenerIndicadoresProyecto(Long idProyecto) {
        String sql = """
            SELECT nombreind, tipoind, periodicidadind, calculoind, descripcioncal
            FROM [Planeacion].[odi].[Indicador]
            WHERE idproyecto = :idProyecto AND (idactividad IS NULL)
            ORDER BY idindicador
        """;
        Query q = em.createNativeQuery(sql);
        q.setParameter("idProyecto", idProyecto);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        List<IndicadorRow> out = new ArrayList<>();
        for (Object[] r : rows) {
            IndicadorRow i = new IndicadorRow();
            int c = 0;
            i.nombre = toStr(r[c++]);
            i.tipo = toInt(r[c++]);
            i.periodicidad = toStr(r[c++]);
            i.calculo = toInt(r[c++]);
            i.descripcionCal = toStr(r[c++]);
            out.add(i);
        }
        return out;
    }

    private List<ActividadRow> obtenerActividades(Long idProyecto) {
        String sql = """
            SELECT idactividad, nombreact, descripcionact,
                   CONVERT(varchar, fechainiact, 23) AS fechaini,
                   CONVERT(varchar, fechafinact, 23) AS fechafin,
                   porcejecucionact, responsableact
            FROM [Planeacion].[odi].[Actividad]
            WHERE idproyecto = :idProyecto
            ORDER BY fechainiact, idactividad
        """;
        Query q = em.createNativeQuery(sql);
        q.setParameter("idProyecto", idProyecto);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        List<ActividadRow> out = new ArrayList<>();
        for (Object[] r : rows) {
            ActividadRow a = new ActividadRow();
            int i = 0;
            a.idActividad = toLong(r[i++]);
            a.nombreact = toStr(r[i++]);
            a.descripcion = toStr(r[i++]);
            a.fechaIni = toStr(r[i++]);
            a.fechaFin = toStr(r[i++]);
            a.porcEjec = toInt(r[i++]);
            a.responsable = toStr(r[i++]);
            out.add(a);
        }
        return out;
    }

    private Map<Long, List<IndicadorRow>> obtenerIndicadoresPorActividad(Long idProyecto) {
        String sql = """
            SELECT idactividad, nombreind, tipoind, periodicidadind, calculoind, descripcioncal
            FROM [Planeacion].[odi].[Indicador]
            WHERE idproyecto = :idProyecto AND idactividad IS NOT NULL
            ORDER BY idactividad, idindicador
        """;
        Query q = em.createNativeQuery(sql);
        q.setParameter("idProyecto", idProyecto);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        Map<Long, List<IndicadorRow>> map = new LinkedHashMap<>();
        for (Object[] r : rows) {
            int i = 0;
            Long idAct = toLong(r[i++]);
            IndicadorRow ind = new IndicadorRow();
            ind.nombre = toStr(r[i++]);
            ind.tipo = toInt(r[i++]);
            ind.periodicidad = toStr(r[i++]);
            ind.calculo = toInt(r[i++]);
            ind.descripcionCal = toStr(r[i++]);
            map.computeIfAbsent(idAct, k -> new ArrayList<>()).add(ind);
        }
        return map;
    }

    private Map<Long, List<ErogacionRow>> obtenerErogaciones(Long idProyecto) {
        String sql = """
            SELECT idactividad, agno, tiporubpl, rubropl, valor, observacionpl
            FROM [Planeacion].[odi].[ErogacionPL]
            WHERE idproyecto = :idProyecto
            ORDER BY idactividad, agno, iderogacionpl
        """;
        Query q = em.createNativeQuery(sql);
        q.setParameter("idProyecto", idProyecto);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        Map<Long, List<ErogacionRow>> map = new LinkedHashMap<>();
        for (Object[] r : rows) {
            int i = 0;
            Long idAct = toLong(r[i++]);
            ErogacionRow e = new ErogacionRow();
            e.agno = toInt(r[i++]);
            e.tipoRubro = toStr(r[i++]);
            e.rubro = toLong(r[i++]);
            e.valor = toBig(r[i++]);
            e.observacion = toStr(r[i++]);
            map.computeIfAbsent(idAct, k -> new ArrayList<>()).add(e);
        }
        return map;
    }

    private Map<Long, List<PersonalRow>> obtenerPersonalHoras(Long idProyecto) {
        String sql = """
            SELECT 
              per.idactividad, per.idpersonal, per.nombreparticpprs, per.cargoparticprs, per.estado,
              hp.agno, hp.horas
            FROM [Planeacion].[odi].[Personal] per
            INNER JOIN [Planeacion].[odi].[Actividad] a ON a.idactividad = per.idactividad
            LEFT JOIN  [Planeacion].[odi].[Horas_Personal] hp ON hp.idpersonal = per.idpersonal
            WHERE a.idproyecto = :idProyecto
            ORDER BY per.idactividad, per.idpersonal, hp.agno
        """;
        Query q = em.createNativeQuery(sql);
        q.setParameter("idProyecto", idProyecto);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        Map<Long, List<PersonalRow>> map = new LinkedHashMap<>();
        for (Object[] r : rows) {
            int i = 0;
            Long idAct = toLong(r[i++]);
            PersonalRow p = new PersonalRow();
            p.idPersonal = toLong(r[i++]);
            p.nombre = toStr(r[i++]);
            p.cargo = toStr(r[i++]);
            p.estado = toInt(r[i++]);
            p.agno = toInt(r[i++]);
            p.horas = toInt(r[i++]);
            map.computeIfAbsent(idAct, k -> new ArrayList<>()).add(p);
        }
        return map;
    }

    // =====================================================
    //  Estilos
    // =====================================================

    private CellStyle estiloTituloPrincipal(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        Font f = wb.createFont();
        f.setBold(true);
        f.setFontHeightInPoints((short) 16);
        f.setColor(IndexedColors.DARK_RED.getIndex());
        st.setFont(f);
        st.setAlignment(HorizontalAlignment.CENTER);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        return st;
    }

    private CellStyle estiloTituloSeccion(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        Font f = wb.createFont();
        f.setBold(true);
        st.setFont(f);
        st.setAlignment(HorizontalAlignment.LEFT);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        return st;
    }

    private CellStyle estiloSubtituloActividad(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        st.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        st.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font f = wb.createFont();
        f.setBold(true);
        st.setFont(f);
        st.setAlignment(HorizontalAlignment.LEFT);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        return st;
    }

    private CellStyle estiloHeaderTabla(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        st.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        st.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font f = wb.createFont();
        f.setBold(true);
        f.setColor(IndexedColors.WHITE.getIndex());
        st.setFont(f);
        st.setAlignment(HorizontalAlignment.CENTER);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        st.setBorderBottom(BorderStyle.THIN);
        st.setBorderTop(BorderStyle.THIN);
        st.setBorderLeft(BorderStyle.THIN);
        st.setBorderRight(BorderStyle.THIN);
        return st;
    }

    private CellStyle estiloFilaPar(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        st.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        st.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        st.setBorderBottom(BorderStyle.THIN);
        st.setBorderTop(BorderStyle.THIN);
        st.setBorderLeft(BorderStyle.THIN);
        st.setBorderRight(BorderStyle.THIN);
        return st;
    }

    private CellStyle estiloFilaImpar(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        st.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        st.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        st.setBorderBottom(BorderStyle.THIN);
        st.setBorderTop(BorderStyle.THIN);
        st.setBorderLeft(BorderStyle.THIN);
        st.setBorderRight(BorderStyle.THIN);
        return st;
    }

    private CellStyle estiloValor(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        st.setAlignment(HorizontalAlignment.LEFT);
        st.setVerticalAlignment(VerticalAlignment.TOP);
        st.setWrapText(false);
        return st;
    }

    private CellStyle estiloWrap(Workbook wb) {
        CellStyle st = wb.createCellStyle();
        st.setAlignment(HorizontalAlignment.LEFT);
        st.setVerticalAlignment(VerticalAlignment.TOP);
        st.setWrapText(true);
        return st;
    }

    // =====================================================
    //  Ayudantes de escritura de Excel
    // =====================================================

    private int tituloPrincipal(Sheet sh, int r, String titulo, CellStyle st) {
        Row row = sh.createRow(r++);
        Cell c = row.createCell(0);
        c.setCellValue(titulo);
        c.setCellStyle(st);
        sh.addMergedRegion(org.apache.poi.ss.util.CellRangeAddress.valueOf("A" + r + ":H" + r));
        row.setHeightInPoints(24f);
        return r;
    }

    private int tituloSeccion(Sheet sh, int r, String titulo, CellStyle st) {
        Row row = sh.createRow(r++);
        Cell c = row.createCell(0);
        c.setCellValue(titulo);
        c.setCellStyle(st);
        sh.addMergedRegion(org.apache.poi.ss.util.CellRangeAddress.valueOf("A" + r + ":H" + r));
        row.setHeightInPoints(20f);
        return r;
    }

    private int subTituloActividad(Sheet sh, int r, String nombre, CellStyle st) {
        Row row = sh.createRow(r++);
        Cell c0 = row.createCell(0);
        c0.setCellValue("Actividad: " + safe(nombre));
        c0.setCellStyle(st);
        sh.addMergedRegion(org.apache.poi.ss.util.CellRangeAddress.valueOf("A" + r + ":H" + r));
        row.setHeightInPoints(18f);
        return r;
    }

    private int subTituloTabla(Sheet sh, int r, String titulo, CellStyle st) {
        Row row = sh.createRow(r++);
        Cell c = row.createCell(0);
        c.setCellValue(titulo);
        c.setCellStyle(st);
        sh.addMergedRegion(org.apache.poi.ss.util.CellRangeAddress.valueOf("A" + r + ":H" + r));
        row.setHeightInPoints(18f);
        return r;
    }

    private int kv(Sheet sh, int r, String key, String val, CellStyle stKey, CellStyle stVal) {
        Row row = sh.createRow(r++);
        Cell c0 = row.createCell(0);
        c0.setCellValue(key);
        c0.setCellStyle(stKey);
        Cell c1 = row.createCell(1);
        c1.setCellValue(val == null ? "" : val);
        c1.setCellStyle(stVal);
        sh.addMergedRegion(org.apache.poi.ss.util.CellRangeAddress.valueOf("B" + r + ":H" + r));
        return r;
    }

    private int kvWrap(Sheet sh, int r, String key, String val, CellStyle stKey, CellStyle stWrap) {
        Row row = sh.createRow(r++);
        Cell c0 = row.createCell(0);
        c0.setCellValue(key);
        c0.setCellStyle(stKey);
        Cell c1 = row.createCell(1);
        c1.setCellValue(val == null ? "" : val);
        c1.setCellStyle(stWrap);
        sh.addMergedRegion(org.apache.poi.ss.util.CellRangeAddress.valueOf("B" + r + ":H" + r));
        row.setHeightInPoints(Math.max(20f, calcHeight(val)));
        return r;
    }

    private int tablaSimple(Sheet sh, int r, CellStyle stHeader, CellStyle stBody, String[] headers,
                            List<String[]> rows, CellStyle stFilaPar, CellStyle stFilaImpar) {
        Row h = sh.createRow(r++);
        for (int i = 0; i < headers.length; i++) {
            Cell c = h.createCell(i);
            c.setCellValue(headers[i]);
            c.setCellStyle(stHeader);
        }
        int rowIndex = 0;
        for (String[] data : rows) {
            Row row = sh.createRow(r++);
            for (int i = 0; i < headers.length; i++) {
                Cell c = row.createCell(i);
                c.setCellValue(i < data.length ? (data[i] == null ? "" : data[i]) : "");
                c.setCellStyle(rowIndex % 2 == 0 ? stFilaPar : stFilaImpar);
            }
            rowIndex++;
        }
        return r;
    }

    private float calcHeight(String text) {
        if (text == null || text.isBlank()) return 20f;
        int lines = Math.max(1, text.length() / 120 + 1);
        return 18f * lines;
    }

    // =====================================================
    //  DTOs internos
    // =====================================================

    private static class ProyectoRow {
        Long idProyecto;
        String nombre;
        String tipo;
        String ccDirector;
        String ccResponsable;
        String meta;
        String justificacion;
        Integer prioridad;
        String fechaInicio;
        String fechaFin;
        String estado;
        BigDecimal valorProy;
        BigDecimal valorEjec;
        String estadoEjec;
        String unidadEjecutoraCodigo;
        String unidadEjecutoraNombre;
        Long idPlan;
        String planNombre;
    }

    private static class ObjetivoRow {
        Integer tipo; // 1=General; 2=Específico; 3=Meta
        String descripcion;
    }

    private static class IndicadorRow {
        String nombre;
        Integer tipo;      
        String periodicidad;
        Integer calculo;    
        String descripcionCal;
    }

    private static class ActividadRow {
        Long idActividad;
        String nombreact;
        String descripcion;
        String fechaIni;
        String fechaFin;
        Integer porcEjec;
        String responsable;
    }

    private static class ErogacionRow {
        Integer agno;
        String tipoRubro;
        Long rubro;
        BigDecimal valor;
        String observacion;
    }

    private static class PersonalRow {
        Long idPersonal;
        String nombre;
        String cargo;
        Integer estado;
        Integer agno;
        Integer horas;
    }

    // =====================================================
    //  Utilidades
    // =====================================================

    private static String tipoObjetivo(Integer t) {
        if (t == null) return "Sin tipo";
        return switch (t) {
            case 1 -> "General";
            case 2 -> "Específico";
            case 3 -> "Meta";
            default -> "Tipo " + t;
        };
    }

    private static String rango(String i, String f) {
        if ((i == null || i.isBlank()) && (f == null || f.isBlank())) return "";
        return safe(i) + " - " + safe(f);
    }

    private static String formatoMoneda(BigDecimal v) {
        if (v == null) return "";
        return String.format("%,d", v.longValue());
    }

    private static String safe(String s) { return s == null ? "" : s; }
    private static String safeNum(Number n) { return n == null ? "" : String.valueOf(n); }
    private static Long toLong(Object o){ return o==null?null:((Number)o).longValue(); }
    private static Integer toInt(Object o){ return o==null?null:((Number)o).intValue(); }
    private static BigDecimal toBig(Object o){
        if (o == null) return null;
        if (o instanceof BigDecimal bd) return bd;
        if (o instanceof Number n) return BigDecimal.valueOf(n.doubleValue());
        try { return new BigDecimal(String.valueOf(o)); } catch (Exception e){ return null; }
    }
    private static String toStr(Object o){ return o==null?null:String.valueOf(o); }
}
