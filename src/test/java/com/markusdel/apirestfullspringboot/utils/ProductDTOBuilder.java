package com.markusdel.apirestfullspringboot.utils;

import com.markusdel.apirestfullspringboot.dto.CreateProductDTO;
import com.markusdel.apirestfullspringboot.dto.UpdateProductDTO;
import com.markusdel.apirestfullspringboot.model.Product;
import lombok.Builder;

import java.math.BigDecimal;


public class ProductDTOBuilder {

    public static CreateProductDTO returnCreateDTOProduct() {
        return CreateProductDTO.builder()
                .name("Cadeira de escritório")
                .description("Uma cadeira feita de couro com uma boa estrutura para quem passa seu bom tempo trabalhando.")
                .price(new BigDecimal("820.00"))
                .amount(12)
                .build();
    }

    public static Product returnProductFromRepository() {
        return Product.builder()
                .id(1L)
                .name("Cadeira de escritório")
                .description("Uma cadeira feita de couro com uma boa estrutura para quem passa seu bom tempo trabalhando.")
                .price(new BigDecimal("820.00"))
                .amount(12)
                .build();
    }

    public static UpdateProductDTO returnUpdateDTOProduct() {
        return UpdateProductDTO.builder()
                .price(new BigDecimal("856.00"))
                .amount(8)
                .build();
    }
}