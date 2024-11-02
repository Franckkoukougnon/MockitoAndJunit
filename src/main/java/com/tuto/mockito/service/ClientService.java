package com.tuto.mockito.service;


import com.tuto.mockito.entity.Client;
import com.tuto.mockito.enums.Sexe;
import com.tuto.mockito.exception.ClientNotFoundException;
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

    public Client getClientById(Long id) {
        return clientRepo.findById(id).orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found" ));
    }

    public Client getClientByEmail(String email) {
        return clientRepo.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException("Client with email " + email + " not found"));
    }

    public List<Client> getClientBySexe(String sexe) {
        try {
            Sexe sexeEnum = Sexe.valueOf(sexe.toUpperCase());
            List<Client> clients = clientRepo.findBySexe(sexeEnum);
            if (clients.isEmpty()) {
                throw new ClientNotFoundException("No clients found with sexe: " + sexe);
            }
            return clients;
        } catch (IllegalArgumentException e) {
            throw new ClientNotFoundException("Invalid sexe value: " + sexe);
        }
    }

    public void deleteClient(Long id) {

        if(!clientRepo.existsById(id)){
            throw new ClientNotFoundException("Client with id " + id + " not found");
        }
        clientRepo.deleteById(id);
    }

    public Client descactiverClient(Long id) {
        Client client = clientRepo.findById(id).orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found"));
        client.set_active(false);
        return clientRepo.save(client);
    }

    public Client updateClient(Client updatedClientData, Long id) {
        Client client = clientRepo.findById(id).orElse(null);
        if (client != null) {
            client.setEmail(updatedClientData.getEmail());
            client.setNom(updatedClientData.getNom());
            client.setPrenom(updatedClientData.getPrenom());
            client.setNumero_telephone(updatedClientData.getNumero_telephone());
            client.setDate_naissance(updatedClientData.getDate_naissance());
            client.setSexe(updatedClientData.getSexe());
            return clientRepo.save(client);
        }
        return null;
    }


}
