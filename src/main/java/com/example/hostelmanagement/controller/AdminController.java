package com.example.hostelmanagement.controller;

import com.example.hostelmanagement.entity.Principal;
import com.example.hostelmanagement.entity.Student;

import com.example.hostelmanagement.entity.Admin;
import com.example.hostelmanagement.service.AdminService;
import com.example.hostelmanagement.service.PrincipalService;
import com.example.hostelmanagement.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpServletRequest request) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // for ADMIN part
        Admin existingAdmin = adminService.getByEmail(email);
        if (existingAdmin != null && existingAdmin.getPassword().equals(password)) {
            authenticateUser(email, existingAdmin.getPermission(), request);

            return ResponseEntity.ok(Map.of(
                    "message", "Admin login successful",
                    "permission", existingAdmin.getPermission(),
                    "user", existingAdmin // 🔥 Send the full principal object

            ));

        }

        // Then try Principal
        Principal principal = principalService.getByEmail(email);
        if (principal != null && principal.getPassword().equals(password)) {
            authenticateUser(email, principal.getPermission(), request);

            return ResponseEntity.ok(Map.of(
                    "message", "Principal login successful",
                    "permission", principal.getPermission(),
                    "user", principal // 🔥 Send the full principal object
            ));
        }

        // Then try Student

        Student student = studentService.getByEmail(email);
        if (student != null && student.getPassword().equals(password)) {
            authenticateUser(email, "STUDENT", request); // Explicitly set permission to "STUDENT"

            return ResponseEntity.ok(Map.of(
                    "message", "Student login successful",
                    "permission", "STUDENT",
                    "user", student));
        }

        // If neither found
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }

    // ✅ Reusable auth logic
    private void authenticateUser(String email, String permission, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email, null, List.of(() -> permission));
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
