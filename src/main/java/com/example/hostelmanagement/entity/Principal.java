package com.example.hostelmanagement.entity;

import jakarta.persistence.*;

@Entity
public class Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String permission;

    private Long colgid;

    @ManyToOne
    @JoinColumn(name = "colgid", referencedColumnName = "id", insertable = false, updatable = false)
    private College college;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public College getCollege() {
        return college;
    }

    public String getPermission() {
        return permission;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Long getColgid() {
        return colgid;
    }

    public void setColgid(Long colgid) {
        this.colgid = colgid;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
