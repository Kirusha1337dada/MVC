package com.example.mvc.dto;

import com.example.mvc.models.Client;
import com.example.mvc.models.DecisionCredit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContractCreditDto
{
    private Long id;
    private Long clientId;
    private Long decisionCreditId;
    private LocalDateTime dateSign;
    private String status;
}
