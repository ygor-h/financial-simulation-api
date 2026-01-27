package com.ygorhenrique.financial_simulation_api.service;

import com.ygorhenrique.financial_simulation_api.domain.simulation.Simulation;
import com.ygorhenrique.financial_simulation_api.dto.SimulationDTO;
import com.ygorhenrique.financial_simulation_api.repository.SimulationRepository;
import org.springframework.stereotype.Service;

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
}
