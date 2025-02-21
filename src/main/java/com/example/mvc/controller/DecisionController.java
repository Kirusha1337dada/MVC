package com.example.mvc.controller;

import com.example.mvc.models.DecisionCredit;
import com.example.mvc.service.DecisionCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DecisionController
{
    private final DecisionCreditService decisionCreditService;

    @Autowired
    public DecisionController(DecisionCreditService decisionCreditService)
    {
        this.decisionCreditService = decisionCreditService;
    }

    @GetMapping("/decisionCredits")
    public String getAllDecisionCredits(@RequestParam(value="search",required=false) String search, Model model)
    {
        List<DecisionCredit> decisionCredits;

        decisionCredits=decisionCreditService.findAllService();

        decisionCredits = decisionCredits.stream()
                .filter(decisionCredit -> "Approved".equals(decisionCredit.getCreditStatus()))
                .collect(Collectors.toList());

        model.addAttribute("decisionCredits", decisionCredits);
        return "decisionCredits-list";
    }
}
