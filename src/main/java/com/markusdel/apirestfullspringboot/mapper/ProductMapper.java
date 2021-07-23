package com.markusdel.apirestfullspringboot.mapper;

import com.markusdel.apirestfullspringboot.dto.CreateProductDTO;
import com.markusdel.apirestfullspringboot.dto.UpdateProductDTO;
import com.markusdel.apirestfullspringboot.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(CreateProductDTO createProductDTO);

    Product toModel(UpdateProductDTO updateProductDTO);

}