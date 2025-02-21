package com.example.mvc.service.impl;

import com.example.mvc.models.Client;
import com.example.mvc.models.DecisionCredit;
import com.example.mvc.repository.DecisionCreditRepository;
import com.example.mvc.service.DecisionCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DecisionCreditServiceImpl implements DecisionCreditService
{
    private final DecisionCreditRepository decisionCreditRepository;

    @Autowired
    public DecisionCreditServiceImpl(DecisionCreditRepository decisionCreditRepository)
    {
        this.decisionCreditRepository = decisionCreditRepository;
    }

    @Override
    public void SaveOrUpdateService(DecisionCredit decisionCredit)
    {
        decisionCreditRepository.SaveOrUpdate(decisionCredit);
    }

    @Override
    public Optional<DecisionCredit> findByIdService(Long id)
    {
        return decisionCreditRepository.findById(id);
    }

    @Transactional
    @Override
    public List<DecisionCredit> findAllService()
    {
        return decisionCreditRepository.findAll();
    }

    @Override
    public List<DecisionCredit> findBySearchService(String searchService)
    {
        return decisionCreditRepository.findByCreditStatus(searchService);
    }

    @Override
    public void deleteByIdService(Long id)
    {
        decisionCreditRepository.deleteById(id);
    }

    @Transactional
    @Override
    public DecisionCredit makeDecisionCreditService(Client client)
    {
       return decisionCreditRepository.makeDecisionCredit(client);
    }
}
