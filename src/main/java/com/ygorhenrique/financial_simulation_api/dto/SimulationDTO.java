package com.ygorhenrique.financial_simulation_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimulationDTO {
    private Long id;
    @Schema(description = "Valor inicial do investimento", example = "1000.00")
    @Positive(message = "Valor inicial precisa ser maior que zero")
    @NotNull(message = "Valor inicial é obrigatório")
    private BigDecimal initialValue;
    @Schema(description = "Período da simulação em meses", example = "12")
    @PositiveOrZero(message = "Taxa de Juros é necessário ser igual ou maior que zero")
    @NotNull(message = "Taxa de Juros é obrigatória")
    private BigDecimal interestRate;
    @Schema(description = "Taxa de juros mensal (%)", example = "1.0")
    @Positive(message = "Período precisar ser maior que zero")
    @NotNull(message = "Período é obrigatório")
    private Integer periodInMonths;
}
