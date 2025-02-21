package com.example.mvc.service.impl;

import com.example.mvc.models.Client;
import com.example.mvc.repository.impl.ClientRepositoryImpl;
import com.example.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService
{
    private final ClientRepositoryImpl clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepositoryImpl clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @Override
    public Optional<Client> findByIdService(Long id)
    {
        return clientRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Client> findAllService()
    {
         return clientRepository.findAll();
    }

    @Transactional
    @Override
    public List<Client> findBySearchService(String searchService)
    {
        return clientRepository.findBySearch(searchService);
    }

    @Transactional
    @Override
    public void deleteByIdService(Long id)
    {
        clientRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void SaveOrUpdateService(Client client)
    {
        clientRepository.SaveOrUpdate(client);
    }
}
