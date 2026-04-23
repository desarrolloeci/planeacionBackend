package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuario", schema = "dbo" , catalog= "Planeacion")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "updated_user_id")
    private Integer updatedUserId;

    @Column(name = "created_user_id")
    private Integer createdUserId;

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getProfileId() { return profileId; }
    public void setProfileId(Integer profileId) { this.profileId = profileId; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public Integer getUpdatedUserId() { return updatedUserId; }
    public void setUpdatedUserId(Integer updatedUserId) { this.updatedUserId = updatedUserId; }

    public Integer getCreatedUserId() { return createdUserId; }
    public void setCreatedUserId(Integer createdUserId) { this.createdUserId = createdUserId; }
    
}
