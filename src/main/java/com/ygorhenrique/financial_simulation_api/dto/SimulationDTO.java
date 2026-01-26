package com.ygorhenrique.financial_simulation_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimulationDTO {
    private Long id;
    @Positive
    @NotNull(message = "Valor inicial é obrigatório")
    private BigDecimal initialValue;
    @PositiveOrZero
    @NotNull(message = "Taxa de Juros é obrigatória")
    private BigDecimal interestRate;
    @Positive
    @NotNull(message = "Período é obrigatório")
    private Integer periodInMonths;
}
