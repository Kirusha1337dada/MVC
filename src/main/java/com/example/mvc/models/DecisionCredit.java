package com.example.mvc.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "decision_credit")
public class DecisionCredit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String creditStatus;
    private Integer days;
    private BigDecimal approvedAmount;

    @OneToOne (mappedBy = "decisionCredit")
    @ToString.Exclude
    private ContractCredit contractCredit;
}