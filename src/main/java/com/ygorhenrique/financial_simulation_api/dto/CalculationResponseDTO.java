package com.ygorhenrique.financial_simulation_api.dto;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

public record CalculationResponseDTO(BigDecimal initialValue, BigDecimal interestRate, Integer periodInMonths, BigDecimal finalValue, BigDecimal totalProfit) {

}
