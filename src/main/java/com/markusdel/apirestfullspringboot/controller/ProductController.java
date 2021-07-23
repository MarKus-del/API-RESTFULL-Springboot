package com.markusdel.apirestfullspringboot.controller;

import com.markusdel.apirestfullspringboot.model.Product;
import com.markusdel.apirestfullspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public Product postProduct(@Valid @RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return repository.findById(id).get();
    }
}
