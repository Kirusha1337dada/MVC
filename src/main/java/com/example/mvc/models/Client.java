package com.example.mvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String passportInfo;
    private String isMarried;
    private String address;
    private String numberPhone;
    private String workPeriod;
    private String jobName;
    private String companyName;
    private BigDecimal amount;

    @OneToOne(mappedBy = "client")
    private DecisionCredit decisionCredit;

    @OneToOne(mappedBy = "client")
    private ContractCredit contractCredit;
}