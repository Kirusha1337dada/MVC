package com.example.mvc.controller;

import com.example.mvc.models.ContractCredit;
import com.example.mvc.service.ContractCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContractController
{
    private final ContractCreditService contractCreditService;

    @Autowired
    public ContractController(ContractCreditService contractCreditService)
    {
        this.contractCreditService = contractCreditService;
    }

    @GetMapping("/contractCredits")
    public String getAllContracts(Model model)
    {
        List<ContractCredit> contractCredits=contractCreditService.findByContractStatusService("Signed");
        model.addAttribute("contractCredits",contractCredits);

        return "contracts-list";
    }

}
