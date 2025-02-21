package com.example.mvc.repository;

import com.example.mvc.models.Client;
import com.example.mvc.models.DecisionCredit;

import java.util.List;
import java.util.Optional;

public interface DecisionCreditRepository
{
    void SaveOrUpdate(DecisionCredit decisionCredit);
    Optional<DecisionCredit> findById(Long id);
    List<DecisionCredit> findByCreditStatus(String status);
    List<DecisionCredit> findAll();
    void deleteById(Long id);
    DecisionCredit makeDecisionCredit(Client client);
}
