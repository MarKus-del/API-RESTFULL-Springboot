package com.markusdel.apirestfullspringboot.service;


import com.markusdel.apirestfullspringboot.dto.CreateProductDTO;
import com.markusdel.apirestfullspringboot.dto.UpdateProductDTO;
import com.markusdel.apirestfullspringboot.exception.ProductNotFoundException;
import com.markusdel.apirestfullspringboot.mapper.ProductMapper;
import com.markusdel.apirestfullspringboot.model.Product;
import com.markusdel.apirestfullspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    private ProductMapper mapper = ProductMapper.INSTANCE;

    public Product createNewProduct(CreateProductDTO createProductDTO) {
        Product newProduct = mapper.toModel(createProductDTO);

        return repository.save(newProduct);
    }

    public Product getProductById(Long id) {
        Product productIfExists = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productIfExists;
    }

    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    public Product updateProduct(Long id, UpdateProductDTO updateProductDTO) {
        Product productIfExists = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productIfExists.setAmount(updateProductDTO.getAmount());
        productIfExists.setPrice(updateProductDTO.getPrice());

        return repository.save(productIfExists);
    }

    public void deleteProductById(Long id) {
        Product productIfExists = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        repository.delete(productIfExists);
    }
}
