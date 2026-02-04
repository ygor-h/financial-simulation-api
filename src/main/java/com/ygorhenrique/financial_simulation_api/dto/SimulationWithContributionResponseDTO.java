package com.ygorhenrique.financial_simulation_api.dto;

import java.math.BigDecimal;
import java.util.List;

public record SimulationWithContributionResponseDTO(BigDecimal initialValue, BigDecimal monthlyContribution, Integer periodInMonths, BigDecimal interestRate, BigDecimal totalContributed, List<BigDecimal> monthlyResults, BigDecimal finalValue, BigDecimal totalProfit){
}
