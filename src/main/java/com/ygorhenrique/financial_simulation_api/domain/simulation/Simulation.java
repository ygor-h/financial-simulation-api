package com.ygorhenrique.financial_simulation_api.domain.simulation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulations")
@Getter
@Setter
@NoArgsConstructor
public class Simulation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "initial_value", nullable = false)
    private BigDecimal initialValue;
    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;
    @Column(name = "period_in_months", nullable = false)
    private Integer periodInMonths;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
