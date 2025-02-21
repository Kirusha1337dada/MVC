package com.example.mvc.service;

import com.example.mvc.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService
{
    Optional<Client> findByIdService(Long id);
    List<Client> findAllService();
    List<Client> findBySearchService(String searchService);
    void deleteByIdService(Long id);
    void SaveOrUpdateService(Client client);
}
