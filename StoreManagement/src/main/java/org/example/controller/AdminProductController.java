package org.example.controller;

import org.example.domain.Product;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService service;

    public AdminProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> all() {
        return service.findAll();
    }
    // Create a product directly from JSON
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = service.add(product);
        return ResponseEntity.ok(savedProduct);
    }
}
