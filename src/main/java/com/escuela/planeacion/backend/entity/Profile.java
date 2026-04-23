package com.escuela.planeacion.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "profile", schema = "dbo" , catalog= "Planeacion")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @Column(name = "code_profile")
    private String code_profile;

    @Column(name = "description")
    private String description;

    @Column(name = "updated_user_id")
    private Integer updated_user_id;

    @Column(name = "created_user_id")
    private Integer created_user_id;

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getCreated_at() { return created_at; }
    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Date getUpdated_at() { return updated_at; }
    public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

    public String getCode_profile() { return code_profile; }
    public void setCode_profile(String code_profile) { this.code_profile = code_profile; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getUpdated_user_id() { return updated_user_id; }
    public void setUpdated_user_id(Integer updated_user_id) { this.updated_user_id = updated_user_id; }

    public Integer getCreated_user_id() { return created_user_id; }
    public void setCreated_user_id(Integer created_user_id) { this.created_user_id = created_user_id; }
}
