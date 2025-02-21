package com.example.mvc.service;

import com.example.mvc.models.Client;
import com.example.mvc.models.DecisionCredit;

import java.util.List;
import java.util.Optional;

public interface DecisionCreditService
{
    void SaveOrUpdateService(DecisionCredit decisionCredit);
    Optional<DecisionCredit> findByIdService(Long id);
    List<DecisionCredit> findAllService();
    List<DecisionCredit> findBySearchService(String searchService);
    void deleteByIdService(Long id);
    DecisionCredit makeDecisionCreditService(Client client);
}
