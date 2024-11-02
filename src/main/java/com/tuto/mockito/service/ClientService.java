package com.tuto.mockito.service;


import com.tuto.mockito.entity.Client;
import com.tuto.mockito.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }
    public Client addClient(Client client) {

        return clientRepo.save(client);
    }

    public List<Client> getAllClient() {
        return clientRepo.findAll();
    }
}
