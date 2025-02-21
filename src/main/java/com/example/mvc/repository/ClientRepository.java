package com.example.mvc.repository;

import com.example.mvc.models.Client;

import java.util.List;
import java.util.Optional;


public interface ClientRepository
{
    void SaveOrUpdate(Client client);
    Optional<Client> findById(Long id);
    List<Client> findAll();
    List<Client> findBySearch(String search);
    void deleteById(Long id);
}
