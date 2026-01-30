package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Category;
import com.example.ecommerce_backend.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:5173"})
public class CategoryController {

    CategoryRepository cr;
    public CategoryController(CategoryRepository cr){
        this.cr = cr;
    }

    @PostMapping("/category")
    public Category Insert(@RequestBody Category data)
    {
        return cr.save(data);
    }

    @GetMapping("/category")
    public List<Category> show()
    {
        return cr.findAll();
    }

    @GetMapping("/category/{id}")
    public Category singleCategory(@PathVariable Long id)
    {
        return cr.findById(id).orElse(null);
    }

    @PutMapping("/category")
    public Category update(@RequestBody Category data)
    {
        return cr.save(data);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        cr.deleteById(id);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "category deleted successfully"
        ));
    }
}
