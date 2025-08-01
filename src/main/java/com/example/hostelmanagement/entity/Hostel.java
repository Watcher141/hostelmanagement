package com.example.hostelmanagement.entity;

import jakarta.persistence.*;

@Entity
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room;
    private String block;
    private String avail;
    private String money;
    private Long colgid;
    private String rollnumber;

    @ManyToOne
    @JoinColumn(name = "colgid", referencedColumnName = "id", insertable = false, updatable = false)
    private College college;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    public String getBlock() {
        return block;
    }

    public String getAvail() {
        return avail;
    }

    public String getMoney() {
        return money;
    }

    public College getCollege() {
        return college;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public void setMoney(String money) {
        this.money = money;
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

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }
}
