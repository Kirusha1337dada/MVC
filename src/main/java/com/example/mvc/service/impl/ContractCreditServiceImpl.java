package com.example.mvc.service.impl;

import com.example.mvc.models.Client;
import com.example.mvc.models.ContractCredit;
import com.example.mvc.models.DecisionCredit;
import com.example.mvc.repository.ContractCreditRepository;
import com.example.mvc.service.ContractCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContractCreditServiceImpl implements ContractCreditService
{
    private final ContractCreditRepository contractCreditRepository;

    @Autowired
    public ContractCreditServiceImpl(ContractCreditRepository contractCreditRepository)
    {
        this.contractCreditRepository = contractCreditRepository;
    }

    @Override
    public Optional<ContractCredit> findByIdService(Long id)
    {
        return contractCreditRepository.findById(id);
    }

    @Transactional
    @Override
    public List<ContractCredit> findByContractStatusService(String status)
    {
        return contractCreditRepository.findByContractStatus(status);
    }

    @Override
    public void SaveOrUpdateService(ContractCredit contractCredit)
    {
        contractCreditRepository.SaveOrUpdate(contractCredit);
    }

    @Transactional
    @Override
    public void createContractApprovedService(DecisionCredit decisionCredit, Client client)
    {
        contractCreditRepository.createContractApproved(decisionCredit, client);
    }

}
