package com.markusdel.apirestfullspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 24)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 15, max = 120)
    private String description;

    @NotNull
    @DecimalMax(value = "1000.00")
    @DecimalMin(value = "0.30")
    private BigDecimal price;

    @NotNull
    @Max(50)
    @Min(1)
    private Integer amount;
}
