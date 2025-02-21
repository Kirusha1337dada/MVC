package com.example.mvc.repository;

import com.example.mvc.models.Client;
import com.example.mvc.models.ContractCredit;
import com.example.mvc.models.DecisionCredit;

import java.util.List;
import java.util.Optional;

public interface ContractCreditRepository
{
    Optional<ContractCredit> findById(Long id);
    List<ContractCredit> findByContractStatus(String status);
    void SaveOrUpdate(ContractCredit contractCredit);
    void createContractApproved(DecisionCredit decisionCredit, Client client);
}
