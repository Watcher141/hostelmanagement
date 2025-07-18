package com.example.hostelmanagement.controller;

import com.example.hostelmanagement.entity.Principal;
import com.example.hostelmanagement.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/principals")
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;

    @GetMapping
    public List<Principal> getAllPrincipals() {
        return principalService.getAllPrincipals();
    }

    @GetMapping("/{id}")
    public Principal getPrincipalById(@PathVariable Long id) {
        return principalService.getPrincipalById(id);
    }

    @PostMapping
    public Principal createPrincipal(@RequestBody Principal principal) {
        return principalService.createPrincipal(principal);
    }

    @PutMapping("/{id}")
    public Principal updatePrincipal(@PathVariable Long id, @RequestBody Principal principalDetails) {
        return principalService.updatePrincipal(id, principalDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePrincipal(@PathVariable Long id) {
        principalService.deletePrincipal(id);
    }
}
