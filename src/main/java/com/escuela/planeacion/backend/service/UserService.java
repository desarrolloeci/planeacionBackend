package com.escuela.planeacion.backend.service;

import com.escuela.planeacion.backend.entity.ProfileRole;
import com.escuela.planeacion.backend.entity.Role;
import com.escuela.planeacion.backend.entity.User;
import com.escuela.planeacion.backend.repository.ProfileRoleRepository;
import com.escuela.planeacion.backend.repository.RoleRepository;
import com.escuela.planeacion.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    

    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getTodosUsers() {
        return repository.findAll();
    }

    public User getUserPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public User getUserPorMail(String mail) {
        return repository.findByMail(mail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User crearUser(User user) {
        return repository.save(user);
    }

    public User actualizarUser(Integer id, User userActualizado) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        user.setMail(userActualizado.getMail());
        user.setName(userActualizado.getName());
        user.setProfileId(userActualizado.getProfileId());
        user.setCreatedAt(userActualizado.getCreatedAt());
        user.setUpdatedAt(userActualizado.getUpdatedAt());
        user.setUpdatedUserId(userActualizado.getUpdatedUserId());
        user.setCreatedUserId(userActualizado.getCreatedUserId());

        return repository.save(user);
    }

    public void eliminarUser(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        repository.delete(user);
    }
    
 // Nuevo método: roles de un usuario
    public List<String> getRolesPorUsuario(Integer userId) {
        User user = getUserPorId(userId);

        int idParaBuscar = user.getProfileId().intValue();
        // Buscar todos los ProfileRole por profileId del usuario
        List<ProfileRole> profileRoles = profileRoleRepository.findByProfileId(idParaBuscar);

        
        // Obtener los roles correspondientes y mapear a description
        List<String> rolesDescriptions = profileRoles.stream()
                .map(pr -> roleRepository.findById(pr.getRoleId())
                        .orElseThrow(() -> new RuntimeException("Role no encontrado")))
                .map(Role::getDescription)
                .collect(Collectors.toList());

        return rolesDescriptions;
    }
}
