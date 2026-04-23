package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.entity.Archivos;
import com.escuela.planeacion.backend.service.ArchivosService;
import com.escuela.planeacion.backend.service.FileStorageService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = "*")
public class ArchivosController {

    private final ArchivosService archivosService;
    private final FileStorageService fileStorageService;

    public ArchivosController(ArchivosService archivosService, FileStorageService fileStorageService) {
        this.archivosService = archivosService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public List<Archivos> getAll() {
        return archivosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Archivos> getById(@PathVariable Integer id) {
        return archivosService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Archivos create(@RequestBody Archivos archivo) {
        return archivosService.save(archivo);
    }

    @PutMapping("/{id}")
    public Archivos update(@PathVariable Integer id, @RequestBody Archivos archivo) {
        return archivosService.update(id, archivo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        archivosService.delete(id);
    }

    // Métodos de filtrado
    @GetMapping("/actividad/{idactividad}")
    public List<Archivos> getByActividad(@PathVariable Integer idactividad) {
        return archivosService.getByIdActividad(idactividad);
    }

    @GetMapping("/proyecto/{idproyecto}")
    public List<Archivos> getByProyecto(@PathVariable Integer idproyecto) {
        return archivosService.getByIdProyecto(idproyecto);
    }

    // ===================== NUEVOS MÉTODOS =====================

    // Subir archivo físico y guardar info en DB
    @PostMapping("/upload")
    public Archivos uploadArchivo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idproyecto") Integer idproyecto,
            @RequestParam("idactividad") Integer idactividad,
            @RequestParam("observacion") String observacion,
            @RequestParam("seguimiento") String seguimiento
    ) {
        String filename = fileStorageService.storeFile(file);

        Archivos archivo = new Archivos();
        archivo.setNombrearc(filename);
        archivo.setNombreorig(file.getOriginalFilename());
        archivo.setEstado("1");
        archivo.setIdproyecto(idproyecto);
        archivo.setIdactividad(idactividad);
        archivo.setTipocargue("1");
        archivo.setObservacion(observacion);
        archivo.setSeguimiento(seguimiento);

        return archivosService.save(archivo);
    }

    // Descargar archivo
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadArchivo(@PathVariable Integer id) throws Exception {
        Archivos archivo = archivosService.getById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        Path filePath = fileStorageService.getFilePath(archivo.getNombrearc());
        byte[] data = Files.readAllBytes(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getNombreorig() + "\"")
                .body(data);
    }
}
