package com.ygorhenrique.financial_simulation_api.service;

import com.ygorhenrique.financial_simulation_api.domain.simulation.Simulation;
import com.ygorhenrique.financial_simulation_api.dto.*;
import com.ygorhenrique.financial_simulation_api.exception.SimulationNotFoundException;
import com.ygorhenrique.financial_simulation_api.repository.SimulationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationService {
    private final SimulationRepository simulationRepository;

    public SimulationService(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    private void saveSimulation(SimulationDTO simulationDTO) {
        Simulation simulation = new Simulation();
        simulation.setInitialValue(simulationDTO.getInitialValue());
        simulation.setInterestRate(simulationDTO.getInterestRate());
        simulation.setPeriodInMonths(simulationDTO.getPeriodInMonths());
        simulation.setCreatedAt(LocalDateTime.now());
        simulationRepository.save(simulation);
    }

    public SimulationResponseDTO toResponseDTO(Simulation simulation) {
        return new SimulationResponseDTO(
                simulation.getId(),
                simulation.getInitialValue(),
                simulation.getInterestRate(),
                simulation.getPeriodInMonths(),
                simulation.getFinalValue(),
                simulation.getTotalProfit(),
                simulation.getCreatedAt()
        );
    }

    public List<SimulationResponseDTO> getAllSimulations() {
        return simulationRepository.findAll()
                .stream()
                .map(this::toResponseDTO).toList();
    }

    public SimulationResponseDTO getSimulationById(Long id) {
        Simulation simulation = simulationRepository.findById(id)
                .orElseThrow(() -> new SimulationNotFoundException("ID não encontrado"));
        return toResponseDTO(simulation);
    }

    public void deleteSimulation(Long id) {
        Simulation simulation = simulationRepository.findById(id).orElseThrow(
                () -> new SimulationNotFoundException("ID não encontrado")
        );
        simulationRepository.delete(simulation);
    }

    private Simulation createSimulationFromDTO(SimulationDTO simulationDTO) {
        BigDecimal finalValue = calculateFinalValue(simulationDTO);
        BigDecimal totalProfit = calculateTotalProfit(simulationDTO);
        Simulation simulation = new Simulation();
        simulation.setInitialValue(simulationDTO.getInitialValue());
        simulation.setInterestRate(simulationDTO.getInterestRate());
        simulation.setPeriodInMonths(simulationDTO.getPeriodInMonths());
        simulation.setFinalValue(finalValue);
        simulation.setTotalProfit(totalProfit);
        return simulation;
    }

    public CalculationResponseDTO simulate(SimulationDTO simulationDTO) {
        Simulation simulation = createSimulationFromDTO(simulationDTO);
        simulationRepository.save(simulation);
        return new CalculationResponseDTO(
                simulation.getInitialValue(),
                simulation.getInterestRate(),
                simulation.getPeriodInMonths(),
                simulation.getFinalValue(),
                simulation.getTotalProfit());
    }

    public SimulationWithContributionResponseDTO simulateWithMonthlyContribution(MonthlyContributionDTO monthlyContributionDTO) {
        BigDecimal decimalRate = toDecimalRate(monthlyContributionDTO.getInterestRate());
        BigDecimal growthFactor = decimalRate.add(BigDecimal.ONE);
        BigDecimal balance = monthlyContributionDTO.getInitialValue();
        BigDecimal monthlyContribution = monthlyContributionDTO.getMonthlyContribution();
        BigDecimal interestRate = monthlyContributionDTO.getInterestRate();
        BigDecimal initialValue = monthlyContributionDTO.getInitialValue();

        List<BigDecimal> monthlyResults = new ArrayList<>();

        int months = monthlyContributionDTO.getPeriodInMonths();

        for(int i = 0; i < months; i++) {
            balance = balance.multiply(growthFactor);
            balance = balance.add(monthlyContribution);
            monthlyResults.add(balance.setScale(2, RoundingMode.HALF_UP));
        }

        BigDecimal finalValue = balance.setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalContributed = monthlyContributionDTO.getInitialValue()
                .add(monthlyContribution.multiply(BigDecimal.valueOf(months)));
        BigDecimal totalProfit = finalValue.subtract(totalContributed);
        return new SimulationWithContributionResponseDTO(initialValue,
                monthlyContribution,
                months,
                interestRate,
                totalContributed,
                monthlyResults,
                finalValue,
                totalProfit);
    }

    private BigDecimal toDecimalRate(BigDecimal rate) {
        return rate.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFinalValue(SimulationDTO simulationDTO) {
        BigDecimal decimalRate = simulationDTO.getInterestRate()
                .divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
        BigDecimal growthFactor = decimalRate.add(BigDecimal.ONE);

        return simulationDTO.getInitialValue()
                .multiply(growthFactor.pow(simulationDTO.getPeriodInMonths()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalProfit(SimulationDTO simulationDTO) {
        return calculateFinalValue(simulationDTO).subtract(simulationDTO.getInitialValue());
    }
}
