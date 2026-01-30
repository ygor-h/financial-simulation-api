package com.ygorhenrique.financial_simulation_api.service;

import com.ygorhenrique.financial_simulation_api.domain.simulation.Simulation;
import com.ygorhenrique.financial_simulation_api.dto.CalculationResponseDTO;
import com.ygorhenrique.financial_simulation_api.dto.SimulationDTO;
import com.ygorhenrique.financial_simulation_api.repository.SimulationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SimulationService {
    private final SimulationRepository simulationRepository;

    public SimulationService(SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    public void saveSimulation(SimulationDTO simulationDTO) {
        Simulation simulation = new Simulation();
        simulation.setInitialValue(simulationDTO.getInitialValue());
        simulation.setInterestRate(simulationDTO.getInterestRate());
        simulation.setPeriodInMonths(simulationDTO.getPeriodInMonths());
        simulation.setCreatedAt(LocalDateTime.now());
        simulationRepository.save(simulation);
    }

    public List<Simulation> getAllSimulations() {
        return simulationRepository.findAll();
    }

    public Simulation getSimulationById(Long id) {
        return simulationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID não encontrado")
        );
    }

    public void deleteSimulation(Long id) {
        Simulation simulation = simulationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID não encontrado")
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
