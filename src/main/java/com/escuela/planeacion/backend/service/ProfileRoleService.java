package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.ProfileRole;
import com.escuela.planeacion.backend.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileRoleService {

    @Autowired
    private ProfileRoleRepository repository;

    public List<ProfileRole> getTodosProfileRoles() {
        return repository.findAll();
    }

    public ProfileRole getProfileRolePorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public List<ProfileRole> getProfileRolesPorProfileId(Integer profileId) {
        return repository.findByProfileId(profileId);
    }

    public List<ProfileRole> getProfileRolesPorRoleId(Integer roleId) {
        return repository.findByRoleId(roleId);
    }

    public ProfileRole crearProfileRole(ProfileRole profileRole) {
        return repository.save(profileRole);
    }

    public ProfileRole actualizarProfileRole(Integer id, ProfileRole profileRoleActualizado) {
        ProfileRole profileRole = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        profileRole.setProfileId(profileRoleActualizado.getProfileId());
        profileRole.setRoleId(profileRoleActualizado.getRoleId());
        profileRole.setCreatedUserId(profileRoleActualizado.getCreatedUserId());
        profileRole.setCreatedAt(profileRoleActualizado.getCreatedAt());
        profileRole.setUpdatedUserId(profileRoleActualizado.getUpdatedUserId());
        profileRole.setUpdatedAt(profileRoleActualizado.getUpdatedAt());

        return repository.save(profileRole);
    }

    public void eliminarProfileRole(Integer id) {
        ProfileRole profileRole = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(profileRole);
    }
    public void eliminarRolesPorProfileId(Integer profileId) {
        List<ProfileRole> roles = repository.findByProfileId(profileId);
        if (!roles.isEmpty()) {
            repository.deleteAll(roles);
        }
    }
}
