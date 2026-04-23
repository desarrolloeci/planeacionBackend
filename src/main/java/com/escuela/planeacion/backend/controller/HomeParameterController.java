package com.escuela.planeacion.backend.controller;

import com.escuela.planeacion.backend.dto.HomeParameterDTO;
import com.escuela.planeacion.backend.entity.HomeParameter;
import com.escuela.planeacion.backend.service.HomeParameterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/home-parameters")
public class HomeParameterController {

    private final HomeParameterService service;

    public HomeParameterController(HomeParameterService service) {
        this.service = service;
    }

    // 🔹 Convertir Entity → DTO
    private HomeParameterDTO toDTO(HomeParameter entity) {
        HomeParameterDTO dto = new HomeParameterDTO();
        dto.setId(entity.getId());
        dto.setContenidoBase64(Base64.getEncoder().encodeToString(entity.getContenido()));
        return dto;
    }

    // 🔹 Convertir DTO → Entity
    private HomeParameter toEntity(HomeParameterDTO dto) {
        HomeParameter entity = new HomeParameter();
        entity.setId(dto.getId());
        entity.setContenido(Base64.getDecoder().decode(dto.getContenidoBase64()));
        return entity;
    }

    @GetMapping
    public List<HomeParameterDTO> getAll() {
        return service.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeParameterDTO> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(entity -> ResponseEntity.ok(toDTO(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HomeParameterDTO> create(@RequestBody HomeParameterDTO dto) {
        HomeParameter saved = service.save(toEntity(dto));
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HomeParameterDTO> update(@PathVariable Integer id, @RequestBody HomeParameterDTO dto) {
        return service.findById(id)
                .map(existing -> {
                    existing.setContenido(Base64.getDecoder().decode(dto.getContenidoBase64()));
                    HomeParameter updated = service.save(existing);
                    return ResponseEntity.ok(toDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
