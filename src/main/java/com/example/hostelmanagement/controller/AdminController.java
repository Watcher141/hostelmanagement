package com.example.hostelmanagement.controller;
import com.example.hostelmanagement.entity.Principal;

import com.example.hostelmanagement.entity.Admin;
import com.example.hostelmanagement.service.AdminService;
import com.example.hostelmanagement.service.PrincipalService;

import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @Autowired
    private PrincipalService principalService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpServletRequest request) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // First try Admin
        Admin existingAdmin = adminService.getByEmail(email);
        if (existingAdmin != null && existingAdmin.getPassword().equals(password)) {
            authenticateUser(email, request);

            return ResponseEntity.ok(Map.of(
                    "message", "Admin login successful",
                    "permission", existingAdmin.getPermission()));
        }

        // Then try Principal
        Principal principal = principalService.getByEmail(email);
        if (principal != null && principal.getPassword().equals(password)) {
            authenticateUser(email, request);

            return ResponseEntity.ok(Map.of(
                    "message", "Principal login successful",
                    "permission", principal.getPermission()));
        }

        // If neither found
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }

    // ✅ Reusable auth logic
    private void authenticateUser(String email, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email, null, Collections.emptyList());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);
        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", context);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        return adminService.updateAdmin(id, adminDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping("/secure")
    public ResponseEntity<String> secure() {
        return ResponseEntity.ok("You are authenticated as ADMIN.");
    }

    @GetMapping("/email/{email}")
    public Admin getByEmail(@PathVariable String email) {
        return adminService.getByEmail(email);
    }

}
