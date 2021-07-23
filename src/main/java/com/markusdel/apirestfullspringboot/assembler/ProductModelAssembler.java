package com.markusdel.apirestfullspringboot.assembler;

import com.markusdel.apirestfullspringboot.controller.ProductController;
import com.markusdel.apirestfullspringboot.model.Product;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public EntityModel<Product> toModel(Product entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductController.class).getProduct(entity.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProduct()).withRel("products")
        );
    }
}
