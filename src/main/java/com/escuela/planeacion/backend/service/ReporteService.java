package com.escuela.planeacion.backend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import lombok.RequiredArgsConstructor;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final EntityManager em;

   
    public byte[] generarReportePlaneacion(
            String fechaInicio, String fechaFin,
            Long idPlan, Long idEje, // (no usado por no haber confirmación de estructura)
            Long idEstadoProyecto, Long idEstadoEjecucion,
            Long idUnidad, Long idProyecto
    ) throws IOException {

        List<Row> rows = consultarDatos(
                fechaInicio, fechaFin, idPlan, idEstadoProyecto, idEstadoEjecucion, idUnidad, idProyecto
        );
        Map<Long, ProyectoAgregado> proyectos = agrupar(rows);
        return buildDocxPlaneacion(proyectos);
    }

    public byte[] generarReporteAvance(
            String fechaInicio, String fechaFin,
            Long idPlan, Long idEje, 
            Long idEstadoProyecto, Long idEstadoEjecucion,
            Long idUnidad, Long idProyecto
    ) throws IOException {

        List<Row> rows = consultarDatos(
                fechaInicio, fechaFin, idPlan, idEstadoProyecto, idEstadoEjecucion, idUnidad, idProyecto
        );
        Map<Long, ProyectoAgregado> proyectos = agrupar(rows);
        return buildDocxAvance(proyectos);
    }

    
    @SuppressWarnings("unchecked")
    private List<Row> consultarDatos(String fechaInicio, String fechaFin,
                                     Long idPlan, Long idEstadoProyecto, Long idEstadoEjecucion,
                                     Long idUnidad, Long idProyecto) {

        StringBuilder sql = new StringBuilder("""
            SELECT 
                p.idproyecto,                                 -- 0
                p.nombrepr,                                   -- 1
                p.estadopr,                                   -- 2
                p.estadoejecucion,                            -- 3
                CONVERT(varchar, p.fechainipr, 23),           -- 4
                CONVERT(varchar, p.fechafinpr, 23),           -- 5
                ue.nombreunidad,                              -- 6
                p.metapr,                                     -- 7
                p.justificacionpr,                            -- 8
                p.idplan,                                     -- 9
                -- Objetivos
                o.idobjetivo,                                 -- 10
                o.descripcionob,                              -- 11
                o.tipoob,                                     -- 12
                -- Actividades
                a.idactividad,                                -- 13
                a.nombreact,                                  -- 14
                a.descripcionact,                             -- 15
                CONVERT(varchar, a.fechainiact, 23),          -- 16
                CONVERT(varchar, a.fechafinact, 23),          -- 17
                a.porcejecucionact,                           -- 18
               LTRIM(RTRIM(
    ISNULL(e.ap1_emp,'') + ' ' +
    ISNULL(e.ap2_emp,'') + ' ' +
    ISNULL(e.nom_emp,'')
)) AS responsableActividad,
                -- Indicadores
                i.idindicador,                                -- 20
                i.nombreind,                                  -- 21
                i.tipoind,                                    -- 22
                i.periodicidadind,                            -- 23
                i.calculoind,                                 -- 24
                i.descripcioncal                              -- 25
            FROM [Planeacion].[odi].[Proyecto] p
            LEFT JOIN [Planeacion].[odi].[UnidadEjecutora] ue ON ue.idunidadej = p.unidadejecutora
            LEFT JOIN [Planeacion].[odi].[Objetivo] o         ON o.idproyecto   = p.idproyecto
            LEFT JOIN [Planeacion].[odi].[Actividad] a        ON a.idproyecto   = p.idproyecto
            LEFT JOIN [Planeacion].[odi].[Indicador] i        ON i.idproyecto   = p.idproyecto
            LEFT JOIN [Planeacion].[odi].[empnomina] e
       ON e.cod_emp = a.responsableact
            WHERE 1=1
        """);

        if (fechaInicio != null && !fechaInicio.isBlank()) sql.append(" AND p.fechainipr >= :fechaInicio");
        if (fechaFin != null && !fechaFin.isBlank())       sql.append(" AND p.fechafinpr <= :fechaFin");
        if (idPlan != null)                                sql.append(" AND p.idplan = :idPlan");
        if (idEstadoProyecto != null)                      sql.append(" AND p.estadopr = :idEstadoProyecto");
        if (idEstadoEjecucion != null)                     sql.append(" AND p.estadoejecucion = :idEstadoEjecucion");
        if (idUnidad != null)                              sql.append(" AND p.unidadejecutora = :idUnidad");
        if (idProyecto != null)                            sql.append(" AND p.idproyecto = :idProyecto");

        sql.append(" ORDER BY p.idproyecto");

        Query q = em.createNativeQuery(sql.toString());
        if (fechaInicio != null && !fechaInicio.isBlank()) q.setParameter("fechaInicio", fechaInicio);
        if (fechaFin != null && !fechaFin.isBlank())       q.setParameter("fechaFin",   fechaFin);
        if (idPlan != null)                                q.setParameter("idPlan",     idPlan);
        if (idEstadoProyecto != null)                      q.setParameter("idEstadoProyecto", idEstadoProyecto);
        if (idEstadoEjecucion != null)                     q.setParameter("idEstadoEjecucion", idEstadoEjecucion);
        if (idUnidad != null)                              q.setParameter("idUnidad",   idUnidad);
        if (idProyecto != null)                            q.setParameter("idProyecto", idProyecto);

        List<Object[]> raw = q.getResultList();
        List<Row> out = new ArrayList<>(raw.size());
        for (Object[] r : raw) {
            int i = 0;
            Row row = new Row();
            row.idProyecto                   = toLong(r[i++]);
            row.nombreProyecto               = toStr(r[i++]);
            row.estadoProyecto               = toStr(r[i++]);
            row.estadoEjecucion              = toStr(r[i++]);
            row.fechaini                     = toStr(r[i++]);
            row.fechafin                     = toStr(r[i++]);
            row.unidadEjecutora              = toStr(r[i++]);
            row.metaProyecto                 = toStr(r[i++]);
            row.justificacionProyecto        = toStr(r[i++]);
            row.idPlan                       = toLong(r[i++]);

            row.idObjetivo                   = toLong(r[i++]);
            row.descripcionObjetivo          = toStr(r[i++]);
            row.tipoObjetivo                 = toStr(r[i++]);

            row.idActividad                  = toLong(r[i++]);
            row.nombreActividad              = toStr(r[i++]);
            row.descripcionActividad         = toStr(r[i++]);
            row.fechainiAct                  = toStr(r[i++]);
            row.fechafinAct                  = toStr(r[i++]);
            row.porcEjecActividad            = toStr(r[i++]);
            row.responsableActividad         = toStr(r[i++]);

            row.idIndicador                  = toLong(r[i++]);
            row.nombreIndicador              = toStr(r[i++]);
            row.tipoIndicador                = toStr(r[i++]);
            row.periodicidadIndicador        = toStr(r[i++]);
            row.calculoIndicador             = toStr(r[i++]);
            row.descripcionCalculoIndicador  = toStr(r[i++]);

            out.add(row);
        }
        return out;
    }

    private List<AvanceDetallado> consultarAvancePorActividad(Long idActividad) {
        String sql = """
            SELECT 
                YEAR(s.fechaseg)                    AS anio,
                MAX(CONVERT(varchar, s.fechaseg, 23)) AS fechaUltima,
                MAX(p.descripcion)                 AS estado,
                MAX(CAST(s.prcntavanceproyseg as varchar)) AS porcAvance,
                MAX(s.descripavanceseg)            AS descripcion,
                MAX(s.accionesseg)                 AS acciones,
                MAX(s.dificultadesavance)          AS dificultades
            FROM [Planeacion].[odi].[SegActividad] sa
            INNER JOIN [Planeacion].[odi].[Seguimiento] s
                    ON sa.idseguimiento = s.idseguimiento
            LEFT JOIN  [Planeacion].[odi].[Parametros] p
                    ON p.tipo = 6 AND p.secuencial = s.estadoproyseg
            WHERE sa.idactividad = :idActividad
            GROUP BY YEAR(s.fechaseg)
            ORDER BY anio
        """;

        Query q = em.createNativeQuery(sql);
        q.setParameter("idActividad", idActividad);

        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        List<AvanceDetallado> out = new ArrayList<>();
        for (Object[] r : rows) {
            Integer anio = null;
            if (r[0] != null) {
                anio = ((Number) r[0]).intValue();
            }

            out.add(new AvanceDetallado(
                    anio == null ? 0 : anio,  // Si no hay año, guardamos 0
                    toStr(r[1]),
                    toStr(r[2]),
                    toStr(r[3]),
                    toStr(r[4]),
                    toStr(r[5]),
                    toStr(r[6])
            ));
        }
        return out;
    }

    private Map<Long, ProyectoAgregado> agrupar(List<Row> rows) {
        Map<Long, ProyectoAgregado> map = new LinkedHashMap<>();

        for (Row r : rows) {
            if (r.idProyecto == null) continue;

            ProyectoAgregado p = map.computeIfAbsent(r.idProyecto, k -> new ProyectoAgregado(
                    r.idProyecto, nvl(r.nombreProyecto), nvl(r.estadoProyecto), nvl(r.estadoEjecucion),
                    nvl(r.fechaini), nvl(r.fechafin), nvl(r.unidadEjecutora),
                    nvl(r.metaProyecto), nvl(r.justificacionProyecto), r.idPlan
            ));

            if (r.idObjetivo != null) {
                p.objetivos.add(new ObjetivoAgg(
                        r.idObjetivo, nvl(r.descripcionObjetivo), nvl(r.tipoObjetivo)
                ));
            }

            if (r.idActividad != null) {
                p.actividades.putIfAbsent(r.idActividad, new ActividadAgg(
                        r.idActividad, nvl(r.nombreActividad), nvl(r.descripcionActividad),
                        nvl(r.fechainiAct), nvl(r.fechafinAct), nvl(r.porcEjecActividad), nvl(r.responsableActividad)
                ));
            }

            if (r.idIndicador != null) {
                p.indicadores.putIfAbsent(r.idIndicador, new IndicadorAgg(
                        r.idIndicador, nvl(r.nombreIndicador), nvl(r.tipoIndicador),
                        nvl(r.periodicidadIndicador), nvl(r.calculoIndicador), nvl(r.descripcionCalculoIndicador)
                ));
            }
        }

        for (ProyectoAgregado p : map.values()) {
            p.objetivos = p.objetivos.stream()
                    .collect(Collectors.collectingAndThen(
                            Collectors.toMap(o -> o.idObjetivo == null ? (long) o.hashCode() : o.idObjetivo, o -> o, (a, b) -> a, LinkedHashMap::new),
                            m -> new ArrayList<>(m.values())
                    ));
        }

        return map;
    }

   private byte[] buildDocxPlaneacion(Map<Long, ProyectoAgregado> proyectos) throws IOException {
        try (XWPFDocument doc = new XWPFDocument()) {
            portada(doc, "Informe de Planeación", "Generado: " + LocalDate.now());

            for (ProyectoAgregado p : proyectos.values()) {
                agregarSeparador(doc);

                titulo(doc, "Proyecto: " + p.nombreProyecto);
                par(doc, "Estado: " + safe(p.estadoProyecto));
                par(doc, "Estado de ejecución: " + safe(p.estadoEjecucion));
                par(doc, "Vigencia: " + rangoFechas(p.fechaini, p.fechafin));
                par(doc, "Unidad ejecutora: " + safe(p.unidadEjecutora));
                if (p.idPlan != null) par(doc, "Id Plan: " + p.idPlan);

                if (!p.justificacionProyecto.isBlank()) {
                    subtitulo(doc, "Justificación");
                    par(doc, p.justificacionProyecto);
                }

                if (!p.metaProyecto.isBlank()) {
                    subtitulo(doc, "Meta(s)");
                    bullets(doc, List.of(p.metaProyecto));
                }

                if (!p.objetivos.isEmpty()) {
                    Map<String, List<ObjetivoAgg>> agrupados = p.objetivos.stream()
                            .collect(Collectors.groupingBy(o -> o.tipoObjetivo, LinkedHashMap::new, Collectors.toList()));

                    List<ObjetivoAgg> gen = agrupados.getOrDefault("1", Collections.emptyList());
                    if (!gen.isEmpty()) {
                        subtitulo(doc, "Objetivo General");
                        bullets(doc, gen.stream().map(o -> o.descripcionObjetivo).toList());
                    }

                    List<ObjetivoAgg> esp = agrupados.getOrDefault("2", Collections.emptyList());
                    if (!esp.isEmpty()) {
                        subtitulo(doc, "Objetivos Específicos");
                        bullets(doc, esp.stream().map(o -> o.descripcionObjetivo).toList());
                    }

                    List<ObjetivoAgg> metas = agrupados.getOrDefault("3", Collections.emptyList());
                    if (!metas.isEmpty()) {
                        subtitulo(doc, "Metas");
                        bullets(doc, metas.stream().map(o -> o.descripcionObjetivo).toList());
                    }
                }
                
                if (!p.indicadores.isEmpty()) {
                    subtitulo(doc, "Indicadores");
                    XWPFTable t = table(doc, new String[] { "Indicador", "Tipo", "Periodicidad", "Cálculo", "Descripción del cálculo" });
                    for (IndicadorAgg i : p.indicadores.values()) {
                        XWPFTableRow row = t.createRow();
                        setCell(row, 0, i.nombreIndicador);
                        setCell(row, 1, i.tipoIndicador);
                        setCell(row, 2, i.periodicidadIndicador);
                        setCell(row, 3, i.calculoIndicador);
                        setCell(row, 4, i.descripcionCalculoIndicador);
                    }
                }

                if (!p.actividades.isEmpty()) {
                    subtitulo(doc, "Actividades");
                    XWPFTable t = table(doc, new String[] { "Actividad", "Descripción", "Inicio", "Fin", "Responsable" });
                    for (ActividadAgg a : p.actividades.values()) {
                        XWPFTableRow row = t.createRow();
                        setCell(row, 0, a.nombreActividad);
                        setCell(row, 1, a.descripcionActividad);
                        setCell(row, 2, a.fechainiAct);
                        setCell(row, 3, a.fechafinAct);
                        setCell(row, 4, a.responsableActividad);
                    }
                }

               
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            doc.write(out);
            return out.toByteArray();
        }
    }

    private byte[] buildDocxAvance(Map<Long, ProyectoAgregado> proyectos) throws IOException {
        try (XWPFDocument doc = new XWPFDocument()) {
            portada(doc, "Informe de Avance", "Generado: " + LocalDate.now());

            for (ProyectoAgregado p : proyectos.values()) {
                agregarSeparador(doc);

                titulo(doc, "Proyecto: " + p.nombreProyecto);
                par(doc, "Estado: " + safe(p.estadoProyecto));
                par(doc, "Estado de ejecución: " + safe(p.estadoEjecucion));
                par(doc, "Vigencia: " + rangoFechas(p.fechaini, p.fechafin));
                par(doc, "Unidad ejecutora: " + safe(p.unidadEjecutora));

                if (!p.actividades.isEmpty()) {
                    subtitulo(doc, "Avance de Actividades");

                    for (ActividadAgg a : p.actividades.values()) {
                        subtitulo(doc, "Actividad: " + a.nombreActividad);
                        if (!a.descripcionActividad.isBlank()) par(doc, "Descripción: " + a.descripcionActividad);
                        if (!a.responsableActividad.isBlank())  par(doc, "Responsable: " + a.responsableActividad);

                        List<AvanceDetallado> avances = consultarAvancePorActividad(a.idActividad);
                        if (avances.isEmpty()) {
                            par(doc, "No se encontraron avances registrados para esta actividad.");
                            agregarSeparador(doc);
                            continue;
                        }

                        for (AvanceDetallado av : avances) {
                        	par(doc, "Año: " + (av.anio == 0 ? "Sin año definido" : av.anio));

                            par(doc, "Fecha del último Avance del año: " + av.fechaUltima);
                            par(doc, "Estado: " + safe(av.estado));
                            par(doc, "Porcentaje de Avance: " + safe(av.porcAvance) + "%");

                            subtitulo(doc, "Descripción del Avance:");
                            par(doc, safe(av.descripcion));

                            subtitulo(doc, "Acciones a tomar:");
                            par(doc, safe(av.acciones));

                            subtitulo(doc, "Dificultades del Avance:");
                            par(doc, safe(av.dificultades));

                            agregarSeparador(doc);
                        }
                    }
                }
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            doc.write(out);
            return out.toByteArray();
        }
    }

    private void portada(XWPFDocument doc, String titulo, String subtitulo) {
        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r = p.createRun();
        r.setText(titulo);
        r.setBold(true);
        r.setFontSize(20);

        XWPFParagraph p2 = doc.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = p2.createRun();
        r2.setText(subtitulo);
        r2.setFontSize(12);
    }

    private void agregarSeparador(XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun r = p.createRun();
        r.addBreak();
    }

    private void titulo(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun r = p.createRun();
        r.setBold(true);
        r.setFontSize(16);
        r.setText(text);
    }

    private void subtitulo(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun r = p.createRun();
        r.setBold(true);
        r.setFontSize(13);
        r.setText(text);
    }

    private void par(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun r = p.createRun();
        r.setFontSize(11);
        r.setText(text == null ? "" : text);
    }

    private void bullets(XWPFDocument doc, List<String> items) {
        for (String it : items) {
            XWPFParagraph p = doc.createParagraph();
            p.setStyle("ListParagraph"); // estilo de lista en Word
            XWPFRun r = p.createRun();
            r.setText("• " + (it == null ? "" : it));
        }
    }

    private XWPFTable table(XWPFDocument doc, String[] headers) {
        XWPFTable table = doc.createTable(1, headers.length);
        XWPFTableRow header = table.getRow(0);
        for (int c = 0; c < headers.length; c++) {
            setCell(header, c, headers[c], true);
        }
        return table;
    }

    private void setCell(XWPFTableRow row, int idx, String text) {
        setCell(row, idx, text, false);
    }

    private void setCell(XWPFTableRow row, int idx, String text, boolean bold) {
        var cell = row.getCell(idx);
        var p = cell.getParagraphs().get(0);
        var r = p.createRun();
        r.setText(text == null ? "" : text);
        r.setBold(bold);
    }

    private String rangoFechas(String ini, String fin) {
        if ((ini == null || ini.isBlank()) && (fin == null || fin.isBlank())) return "";
        return safe(ini) + " - " + safe(fin);
    }

    private String safe(String s) { return s == null ? "" : s; }

    private static class Row {
        Long idProyecto; String nombreProyecto; String estadoProyecto; String estadoEjecucion;
        String fechaini; String fechafin; String unidadEjecutora;
        String metaProyecto; String justificacionProyecto; Long idPlan;

        Long idObjetivo; String descripcionObjetivo; String tipoObjetivo;

        Long idActividad; String nombreActividad; String descripcionActividad;
        String fechainiAct; String fechafinAct; String porcEjecActividad; String responsableActividad;

        Long idIndicador; String nombreIndicador; String tipoIndicador;
        String periodicidadIndicador; String calculoIndicador; String descripcionCalculoIndicador;
    }

    private static class ProyectoAgregado {
        final Long idProyecto;
        final String nombreProyecto;
        final String estadoProyecto;
        final String estadoEjecucion;
        final String fechaini;
        final String fechafin;
        final String unidadEjecutora;
        final String metaProyecto;
        final String justificacionProyecto;
        final Long idPlan;

        List<ObjetivoAgg> objetivos = new ArrayList<>();
        LinkedHashMap<Long, ActividadAgg> actividades = new LinkedHashMap<>();
        LinkedHashMap<Long, IndicadorAgg> indicadores = new LinkedHashMap<>();

        ProyectoAgregado(Long idProyecto, String nombreProyecto, String estadoProyecto, String estadoEjecucion,
                         String fechaini, String fechafin, String unidadEjecutora,
                         String metaProyecto, String justificacionProyecto, Long idPlan) {
            this.idProyecto = idProyecto;
            this.nombreProyecto = nombreProyecto;
            this.estadoProyecto = estadoProyecto;
            this.estadoEjecucion = estadoEjecucion;
            this.fechaini = fechaini;
            this.fechafin = fechafin;
            this.unidadEjecutora = unidadEjecutora;
            this.metaProyecto = metaProyecto;
            this.justificacionProyecto = justificacionProyecto;
            this.idPlan = idPlan;
        }
    }

    private static class ObjetivoAgg {
        final Long idObjetivo; final String descripcionObjetivo; final String tipoObjetivo;
        ObjetivoAgg(Long idObjetivo, String descripcionObjetivo, String tipoObjetivo) {
            this.idObjetivo = idObjetivo;
            this.descripcionObjetivo = descripcionObjetivo;
            this.tipoObjetivo = tipoObjetivo;
        }
    }

    private static class ActividadAgg {
        final Long idActividad; final String nombreActividad; final String descripcionActividad;
        final String fechainiAct; final String fechafinAct; final String porcEjecActividad; final String responsableActividad;
        ActividadAgg(Long idActividad, String nombreActividad, String descripcionActividad,
                     String fechainiAct, String fechafinAct, String porcEjecActividad, String responsableActividad) {
            this.idActividad = idActividad;
            this.nombreActividad = nombreActividad;
            this.descripcionActividad = descripcionActividad;
            this.fechainiAct = fechainiAct;
            this.fechafinAct = fechafinAct;
            this.porcEjecActividad = porcEjecActividad;
            this.responsableActividad = responsableActividad;
        }
    }

    private static class IndicadorAgg {
        final Long idIndicador; final String nombreIndicador; final String tipoIndicador;
        final String periodicidadIndicador; final String calculoIndicador; final String descripcionCalculoIndicador;
        IndicadorAgg(Long idIndicador, String nombreIndicador, String tipoIndicador,
                     String periodicidadIndicador, String calculoIndicador, String descripcionCalculoIndicador) {
            this.idIndicador = idIndicador;
            this.nombreIndicador = nombreIndicador;
            this.tipoIndicador = tipoIndicador;
            this.periodicidadIndicador = periodicidadIndicador;
            this.calculoIndicador = calculoIndicador;
            this.descripcionCalculoIndicador = descripcionCalculoIndicador;
        }
    }

    private static class AvanceDetallado {
        final int anio;
        final String fechaUltima;
        final String estado;
        final String porcAvance;
        final String descripcion;
        final String acciones;
        final String dificultades;

        AvanceDetallado(int anio, String fechaUltima, String estado, String porcAvance,
                        String descripcion, String acciones, String dificultades) {
            this.anio = anio;
            this.fechaUltima = fechaUltima;
            this.estado = estado;
            this.porcAvance = porcAvance;
            this.descripcion = descripcion;
            this.acciones = acciones;
            this.dificultades = dificultades;
        }
    }

    private static Long toLong(Object o) { return o == null ? null : ((Number) o).longValue(); }
    private static String toStr(Object o) { return o == null ? null : String.valueOf(o); }
    private static String nvl(String s) { return s == null ? "" : s.trim(); }
}
