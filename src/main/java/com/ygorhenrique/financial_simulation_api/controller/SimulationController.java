package com.ygorhenrique.financial_simulation_api.controller;

import com.ygorhenrique.financial_simulation_api.domain.simulation.Simulation;
import com.ygorhenrique.financial_simulation_api.dto.CalculationResponseDTO;
import com.ygorhenrique.financial_simulation_api.dto.SimulationDTO;
import com.ygorhenrique.financial_simulation_api.service.SimulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationService simulationService;

    @PostMapping
    public ResponseEntity<Void> saveSimulation(@Valid @RequestBody SimulationDTO simulationDTO) {
        simulationService.saveSimulation(simulationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Simulation>> getAllSimulations() {
        return ResponseEntity.ok(simulationService.getAllSimulations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Simulation> getSimulationById(@PathVariable Long id) {
        return ResponseEntity.ok(simulationService.getSimulationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimulationById(@PathVariable Long id) {
        simulationService.deleteSimulation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/simulate")
    public ResponseEntity<CalculationResponseDTO> simulate(@Valid @RequestBody SimulationDTO simulationDTO) {
        CalculationResponseDTO response = simulationService.simulate(simulationDTO);
        return ResponseEntity.ok(response);
    }
}
