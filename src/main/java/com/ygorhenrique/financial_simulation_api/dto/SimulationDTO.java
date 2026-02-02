package com.ygorhenrique.financial_simulation_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimulationDTO {
    private Long id;
    @Positive(message = "Valor inicial precisa ser maior que zero")
    @NotNull(message = "Valor inicial é obrigatório")
    private BigDecimal initialValue;
    @PositiveOrZero(message = "Taxa de Juros é necessário ser igual ou maior que zero")
    @NotNull(message = "Taxa de Juros é obrigatória")
    private BigDecimal interestRate;
    @Positive(message = "Período precisar ser maior que zero")
    @NotNull(message = "Período é obrigatório")
    private Integer periodInMonths;
}
