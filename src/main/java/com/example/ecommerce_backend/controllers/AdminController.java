package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Admin;
import com.example.ecommerce_backend.repositories.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:5173"})
public class AdminController {

    AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository)
    {
        this.adminRepository = adminRepository;
    }


    @PostMapping("/admin")
    public ResponseEntity<?> Login(@RequestBody Admin data)
    {

        Admin result = adminRepository.findByUsernameAndPassword(data.getUsername(), data.getPassword());

        if(result != null)
        {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Login Success",
                    "data", result
            ));
        }
        else
        {
            return  ResponseEntity.ok(Map.of(
                    "status", "error",
                    "message", "Login Failed"
            ));
        }

    }

}
