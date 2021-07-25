package com.markusdel.apirestfullspringboot.controller;

import com.markusdel.apirestfullspringboot.assembler.ProductModelAssembler;
import com.markusdel.apirestfullspringboot.dto.CreateProductDTO;
import com.markusdel.apirestfullspringboot.dto.UpdateProductDTO;
import com.markusdel.apirestfullspringboot.model.Product;
import com.markusdel.apirestfullspringboot.repository.ProductRepository;
import com.markusdel.apirestfullspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/products")
public class ProductController implements ProductControllerDocs {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductModelAssembler assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<Product>> postProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
        var productCreated = service.createNewProduct(createProductDTO);
        var productEntityModel = assembler.toModel(productCreated);

        return ResponseEntity.created(
                productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(productEntityModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Product>>> getAllProduct() {
        var productEntityModel = service.getAllProduct()
                .stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(
                productEntityModel,
                linkTo(methodOn(ProductController.class).getAllProduct())
                        .withSelfRel())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getProduct(@PathVariable Long id) {
        var productEntityModel = assembler.toModel(service.getProductById(id));
        return ResponseEntity.ok(productEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> updateProductById(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductDTO updateProductDTO
    ) {
        var productEntityModel = assembler.toModel(
                service.updateProduct(id, updateProductDTO));

        return ResponseEntity.created(
                productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(productEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
        return  ResponseEntity.noContent().build();
    }
}
