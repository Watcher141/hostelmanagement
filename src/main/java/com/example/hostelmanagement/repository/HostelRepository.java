package com.example.hostelmanagement.repository;

import com.example.hostelmanagement.entity.Hostel;
import com.example.hostelmanagement.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelRepository extends JpaRepository<Hostel, Long> {
    Hostel findByRollnumber(String rollnumber);

}
