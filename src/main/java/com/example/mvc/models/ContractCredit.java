    package com.example.mvc.models;

    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDateTime;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table(name = "contract_credit")
    public class ContractCredit
    {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "client_id")
        private Client client;

        @OneToOne
        @JoinColumn(name = "decision_credit_id")
        @ToString.Exclude
        private DecisionCredit decisionCredit;

        private LocalDateTime dateSign;
        private String status;
    }