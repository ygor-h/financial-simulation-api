package com.ygorhenrique.financial_simulation_api.controller;

import com.ygorhenrique.financial_simulation_api.dto.*;
import com.ygorhenrique.financial_simulation_api.service.SimulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Simulações", description = "Endpoints para simulação de investimentos")
@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationService simulationService;

    @Operation(
            summary = "Buscar todas as simulações"
    )
    @GetMapping
    public ResponseEntity<List<SimulationResponseDTO>> getAllSimulations() {
        return ResponseEntity.ok(simulationService.getAllSimulations());
    }

    @Operation(
            summary = "Buscar simulação usando ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SimulationResponseDTO> getSimulationById(@PathVariable Long id) {
        return ResponseEntity.ok(simulationService.getSimulationById(id));
    }

    @Operation(
            summary = "Deletar simulação pelo ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimulationById(@PathVariable Long id) {
        simulationService.deleteSimulation(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Simular investimento sem aportes mensais",
            description = "Realiza uma simulação de investimento considerando valor inicial e juros compostos."
    )
    @PostMapping("/simulate")
    public ResponseEntity<CalculationResponseDTO> simulate(@Valid @RequestBody SimulationDTO simulationDTO) {
        CalculationResponseDTO response = simulationService.simulate(simulationDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Simular investimento com aportes mensais",
            description = "Realiza uma simulação de investimento considerando valor inicial, juros compostos e aportes mensais"
    )
    @PostMapping("/simulate-with-contribution")
    public ResponseEntity<SimulationContributionResponseDTO> simulateWithMonthlyContribution(@Valid @RequestBody MonthlyContributionDTO monthlyContributionDTO) {
        SimulationContributionResponseDTO response = simulationService.simulateWithMonthlyContribution(monthlyContributionDTO);
        return ResponseEntity.ok(response);
    }
}
