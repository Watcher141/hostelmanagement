package com.example.hostelmanagement.entity;

import jakarta.persistence.*;

@Entity
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String colgname;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getColgname() {
        return colgname;
    }

    public void setColgname(String colgname) {
        this.colgname = colgname;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
