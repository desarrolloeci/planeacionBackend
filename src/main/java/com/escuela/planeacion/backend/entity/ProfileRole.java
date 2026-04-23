package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "profile_role", schema = "dbo" , catalog= "Planeacion")
public class ProfileRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Propiedades renombradas para que el repositorio pueda usar findByProfileId / findByRoleId
    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "created_user_id")
    private Integer createdUserId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_user_id")
    private Integer updatedUserId;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getProfileId() { return profileId; }
    public void setProfileId(Integer profileId) { this.profileId = profileId; }

    public Integer getRoleId() { return roleId; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public Integer getCreatedUserId() { return createdUserId; }
    public void setCreatedUserId(Integer createdUserId) { this.createdUserId = createdUserId; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Integer getUpdatedUserId() { return updatedUserId; }
    public void setUpdatedUserId(Integer updatedUserId) { this.updatedUserId = updatedUserId; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
