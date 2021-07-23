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
public class UpdateProductDTO {

    @NotNull
    @DecimalMax(value = "1000.00")
    @DecimalMin(value = "0.30")
    private BigDecimal price;

    @NotNull
    @Max(50)
    @Min(1)
    private Integer amount;
}
