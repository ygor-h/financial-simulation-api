package com.ygorhenrique.financial_simulation_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlyContributionDTO {
    @Schema(description = "Valor inicial do investimento", example = "1000.00")
    @Positive(message = "Valor inicial precisa ser maior que zero")
    @NotNull(message = "Valor inicial é obrigatório")
    private BigDecimal initialValue;
    @Schema(description = "Valor do aporte mensal", example = "200.00")
    @Positive(message = "Contribuição mensal precisa ser maior que zero")
    @NotNull(message = "Contribuição mensal é obrigatório")
    private BigDecimal monthlyContribution;
    @Schema(description = "Período da simulação em meses", example = "12")
    @Positive(message = "Período precisar ser maior que zero")
    @NotNull(message = "Período é obrigatório")
    private Integer periodInMonths;
    @Schema(description = "Taxa de juros mensal (%)", example = "1.0")
    @PositiveOrZero(message = "Taxa de Juros é necessário ser igual ou maior que zero")
    @NotNull(message = "Taxa de Juros é obrigatória")
    private BigDecimal interestRate;
}
