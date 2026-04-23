package com.escuela.planeacion.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path storageLocation = Paths.get(System.getProperty("user.dir"), "uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(storageLocation); // crea carpeta si no existe
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear la carpeta de uploads", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String filename = System.currentTimeMillis() + "_" + originalFilename; // Nombre único

        try {
            Path targetLocation = this.storageLocation.resolve(filename);
            file.transferTo(targetLocation.toFile());
            return filename; // Devolvemos el nombre almacenado
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo: " + originalFilename, e);
        }
    }

    public Path getFilePath(String filename) {
        return storageLocation.resolve(filename).normalize();
    }
}
