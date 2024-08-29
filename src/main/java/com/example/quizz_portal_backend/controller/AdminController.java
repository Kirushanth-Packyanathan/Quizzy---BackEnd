package com.example.quizz_portal_backend.controller;

import com.example.quizz_portal_backend.entity.Admin;
import com.example.quizz_portal_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.findByEmail(email);
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }
}