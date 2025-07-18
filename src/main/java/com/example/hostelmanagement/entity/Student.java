package com.example.hostelmanagement.entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rollnumber;
    private String email;
    private String password;
    private String address;
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

    public String getRollnumber() {
        return rollnumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public College getCollege() {
        return college;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
