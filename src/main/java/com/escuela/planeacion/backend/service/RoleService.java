package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.Role;
import com.escuela.planeacion.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<Role> getTodosRoles() {
        return repository.findAll();
    }

    public Role getRolePorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public Role crearRole(Role role) {
        return repository.save(role);
    }

    public Role actualizarRole(Integer id, Role roleActualizado) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        role.setCode_rol(roleActualizado.getCode_rol());
        role.setDescription(roleActualizado.getDescription());
        role.setCreated_user_id(roleActualizado.getCreated_user_id());
        role.setCreated_at(roleActualizado.getCreated_at());
        role.setUpdated_user_id(roleActualizado.getUpdated_user_id());
        role.setUpdated_at(roleActualizado.getUpdated_at());

        return repository.save(role);
    }

    public void eliminarRole(Integer id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(role);
    }
}
