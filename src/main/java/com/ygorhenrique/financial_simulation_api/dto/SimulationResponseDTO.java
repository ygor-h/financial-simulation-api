package com.ygorhenrique.financial_simulation_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SimulationResponseDTO(Long id, BigDecimal initialValue, BigDecimal interestRate, Integer periodInMonths, BigDecimal finalValue, BigDecimal totalProfit, LocalDateTime createdAt) {
}
