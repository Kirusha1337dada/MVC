package com.example.mvc.dto;

import com.example.mvc.models.Client;
import com.example.mvc.models.ContractCredit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DecisionCreditDto
{
    private Long id;
    private Long clientId;
    private String creditStatus;
    private Integer days;
    private BigDecimal approvedAmount;
    private Long contractCreditId;
}
