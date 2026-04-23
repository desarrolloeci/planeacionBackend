package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Búsqueda por mail
    Optional<User> findByMail(String mail);
}
