package com.markusdel.apirestfullspringboot.controller;

import com.markusdel.apirestfullspringboot.dto.CreateProductDTO;
import com.markusdel.apirestfullspringboot.dto.UpdateProductDTO;
import com.markusdel.apirestfullspringboot.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api("Restfull api in Spring Boot")
public interface ProductControllerDocs {

    @ApiOperation(value = "Create product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success beer creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value")
    })
    public ResponseEntity<EntityModel<Product>> postProduct(@Valid @RequestBody CreateProductDTO createProductDTO);

    @ApiOperation(value = "Get all product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success beer found in the system"),
            @ApiResponse(code = 404, message = "Product with given id not found.")
    })
    public ResponseEntity<CollectionModel<EntityModel<Product>>> getAllProduct();

    @ApiOperation(value = "Get one product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all product registered"),
    })
    public ResponseEntity<EntityModel<Product>> getProduct(@PathVariable Long id);

    @ApiOperation(value = "Update product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Update product"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value"),
            @ApiResponse(code = 404, message = "product with given id not found.")
    })
    public ResponseEntity<EntityModel<Product>> updateProductById(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductDTO updateProductDTO
    );

    @ApiOperation(value = "delete product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success product deleted"),
            @ApiResponse(code = 404, message = "Product with given id not found.")
    })
    public ResponseEntity deleteProductById(@PathVariable Long id);
}
