package com.ygorhenrique.financial_simulation_api.repository;

import com.ygorhenrique.financial_simulation_api.domain.simulation.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRepository extends JpaRepository<Simulation, Long> {

}
