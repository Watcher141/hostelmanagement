package com.example.hostelmanagement.service;

import com.example.hostelmanagement.entity.Principal;
import com.example.hostelmanagement.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrincipalService {

    @Autowired
    private PrincipalRepository principalRepository;

    public List<Principal> getAllPrincipals() {
        return principalRepository.findAll();
    }

    public Principal getPrincipalById(Long id) {
        return principalRepository.findById(id).orElse(null);
    }

    public Principal createPrincipal(Principal principal) {
        return principalRepository.save(principal);
    }

    public Principal updatePrincipal(Long id, Principal principalDetails) {
        Principal principal = principalRepository.findById(id).orElse(null);
        if (principal != null) {
            principal.setName(principalDetails.getName());
            principal.setEmail(principalDetails.getEmail());
            principal.setPassword(principalDetails.getPassword());
            principal.setCollege(principalDetails.getCollege());
            principal.setPermission(principalDetails.getPermission());
            return principalRepository.save(principal);
        }
        return null;
    }

    public void deletePrincipal(Long id) {
        principalRepository.deleteById(id);
    }
}
