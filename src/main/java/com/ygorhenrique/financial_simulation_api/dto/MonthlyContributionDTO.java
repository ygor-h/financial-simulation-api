package com.ygorhenrique.financial_simulation_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlyContributionDTO {
    @Positive(message = "Valor inicial precisa ser maior que zero")
    @NotNull(message = "Valor inicial é obrigatório")
    private BigDecimal initialValue;
    @Positive(message = "Contribuição mensal precisa ser maior que zero")
    @NotNull(message = "Contribuição mensal é obrigatório")
    private BigDecimal monthlyContribution;
    @Positive(message = "Período precisar ser maior que zero")
    @NotNull(message = "Período é obrigatório")
    private Integer periodInMonths;
    @PositiveOrZero(message = "Taxa de Juros é necessário ser igual ou maior que zero")
    @NotNull(message = "Taxa de Juros é obrigatória")
    private BigDecimal interestRate;
}
