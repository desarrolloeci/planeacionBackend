package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Profile;
import com.escuela.planeacion.backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public List<Profile> getTodosProfiles() {
        return repository.findAll();
    }

    public Profile getProfilePorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public Profile crearProfile(Profile profile) {
        return repository.save(profile);
    }

    public Profile actualizarProfile(Integer id, Profile profileActualizado) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        profile.setName(profileActualizado.getName());
        profile.setCreated_at(profileActualizado.getCreated_at());
        profile.setUpdated_at(profileActualizado.getUpdated_at());
        profile.setCode_profile(profileActualizado.getCode_profile());
        profile.setDescription(profileActualizado.getDescription());
        profile.setUpdated_user_id(profileActualizado.getUpdated_user_id());
        profile.setCreated_user_id(profileActualizado.getCreated_user_id());

        return repository.save(profile);
    }

    public void eliminarProfile(Integer id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(profile);
    }
}
