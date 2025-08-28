package org.example.controller;

import org.example.domain.Product;
import org.example.dto.ProductRequest;
import org.example.dto.UpdateNameRequest;
import org.example.dto.UpdatePriceRequest;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;
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
    /*// Create a product directly from JSON
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = service.add(product);
        return ResponseEntity.ok(savedProduct);
    }
    */
    @PostMapping
    public Product add(@Valid @RequestBody ProductRequest req) {
        return service.add(new Product(req.name(),req.price()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/name")
    public Product changeName(@PathVariable Long id,@Valid @RequestBody UpdateNameRequest req) {
        return service.changeName(id,req.name());
    }

    @PutMapping("/{id}/price")
    public Product changePrice(@PathVariable Long id,@Valid @RequestBody UpdatePriceRequest req) {
        return service.changePrice(id,req.price());
    }


}
