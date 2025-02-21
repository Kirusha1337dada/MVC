package com.example.mvc.controller;

import com.example.mvc.models.Client;
import com.example.mvc.models.DecisionCredit;
import com.example.mvc.service.ClientService;
import com.example.mvc.service.ContractCreditService;
import com.example.mvc.service.DecisionCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController
{
    private final ClientService clientService;
    private final DecisionCreditService decisionCreditService;
    private final ContractCreditService contractCreditService;


    @Autowired
    public ClientController(ClientService clientService, DecisionCreditService decisionCreditService, ContractCreditService contractCreditService)
    {
        this.clientService = clientService;
        this.decisionCreditService = decisionCreditService;
        this.contractCreditService = contractCreditService;
    }

    @GetMapping("/clients")
    public String getAllClients(@RequestParam(value="search", required = false) String search, Model model)
    {
        List<Client> clients;

        if(search != null && !search.isEmpty())
        {
            clients=clientService.findBySearchService(search);
        }
        else
        {
            clients = clientService.findAllService();
        }

        model.addAttribute("clients", clients);
        return "clients-list";
    }

    @GetMapping("/clients/new")
    public String createClientForm(Model model)
    {
        Client client = new Client();
        model.addAttribute("client", client);
        return "clients-create";
    }

    @PostMapping("/clients/new")
    public String saveClients(@ModelAttribute("client") Client client)
    {
        clientService.SaveOrUpdateService(client);

        DecisionCredit newDecision = decisionCreditService.makeDecisionCreditService(client);

        if("Approved".equals(newDecision.getCreditStatus()))
        {
            contractCreditService.createContractApprovedService(newDecision, client);
        }

        return "redirect:/clients";
    }

    @PostMapping("/clients/{clientId}/delete")
    public String deleteClients(@PathVariable("clientId") Long clientId)
    {
        clientService.deleteByIdService(clientId);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{clientId}/edit")
    public String editClientsForm(@PathVariable("clientId") Long clientId, Model model)
    {
        Optional<Client> client = clientService.findByIdService(clientId);
        model.addAttribute("client",client.orElse(null));
        return "clients-edit";
    }

    @PostMapping("/clients/{clientId}/edit")
    public String updateClients(@PathVariable("clientId") Long clientId, @ModelAttribute("client") Client client )
    {
        client.setId(clientId);
        clientService.SaveOrUpdateService(client);
        return "redirect:/clients";
    }

}
