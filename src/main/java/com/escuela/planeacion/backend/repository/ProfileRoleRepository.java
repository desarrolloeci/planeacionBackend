package com.escuela.planeacion.backend.repository;

import com.escuela.planeacion.backend.entity.ProfileRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRoleRepository extends JpaRepository<ProfileRole, Integer> {

    // Búsqueda por profileId
    List<ProfileRole> findByProfileId(Integer profileId);

    // Búsqueda por roleId
    List<ProfileRole> findByRoleId(Integer roleId);
}
