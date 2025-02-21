package com.example.mvc.service;

import com.example.mvc.models.Client;
import com.example.mvc.models.ContractCredit;
import com.example.mvc.models.DecisionCredit;

import java.util.List;
import java.util.Optional;

public interface ContractCreditService
{
    Optional<ContractCredit> findByIdService(Long id);
    List<ContractCredit> findByContractStatusService(String status);
    void SaveOrUpdateService(ContractCredit contractCredit);
    void createContractApprovedService(DecisionCredit decisionCredit, Client client);
}
