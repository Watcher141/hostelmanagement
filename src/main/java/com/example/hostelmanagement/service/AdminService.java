package com.example.hostelmanagement.service;

import com.example.hostelmanagement.entity.Admin;
import com.example.hostelmanagement.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean validateCredentials(Admin adminRequest) {
        Admin admin = adminRepository.findByEmail(adminRequest.getEmail());
        return admin != null && admin.getPassword().equals(adminRequest.getPassword());
    }

    public Admin getByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public Admin getLoggedInAdmin(HttpSession session) {
        String email = (String) session.getAttribute("adminEmail");
        return email != null ? adminRepository.findByEmail(email) : null;
    }

    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("adminEmail") != null;
    }

    public void createDefaultAdminIfNotExists() {
        if (adminRepository.findByEmail("admin@sys.com") == null) {
            Admin admin = new Admin();
            admin.setName("Super Admin");
            admin.setEmail("admin@sys.com");
            admin.setPassword("admin123");
            admin.setPermission("ALL");
            adminRepository.save(admin);
        }
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            admin.setName(adminDetails.getName());
            admin.setEmail(adminDetails.getEmail());
            admin.setPassword(adminDetails.getPassword());
            admin.setPermission(adminDetails.getPermission());
            return adminRepository.save(admin);
        }
        return null;
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
