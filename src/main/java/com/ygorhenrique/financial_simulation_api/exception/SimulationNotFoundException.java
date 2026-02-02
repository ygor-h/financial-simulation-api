package com.ygorhenrique.financial_simulation_api.exception;

public class SimulationNotFoundException extends RuntimeException {
    public SimulationNotFoundException(String message) {
        super(message);
    }
}
