package com.example.hostelmanagement.repository;

import com.example.hostelmanagement.entity.Principal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrincipalRepository extends JpaRepository<Principal, Long> {
}
