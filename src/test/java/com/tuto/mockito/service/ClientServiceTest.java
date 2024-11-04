package com.tuto.mockito.service;

import com.tuto.mockito.entity.Client;
import com.tuto.mockito.enums.Sexe;
import com.tuto.mockito.repository.ClientRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ClientServiceTest {

    @Mock
    private ClientRepo clientRepo;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach // Avant chaque test
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId(1L);
        client.setNom("Test");
        client.setPrenom("Test");
        client.set_active(true);
        client.setNumero_telephone("1234567890");
        client.setEmail("test@example.com");
        client.setSexe(Sexe.FEMME);
    }



    private Client client;

    @Test
    void addClient() {
        when(clientRepo.save(client)).thenReturn(client);  // On simule l'ajout d'un client

        Client clientAdded = clientService.addClient(client); // On ajoute le client

        assertEquals(client, clientAdded); // On vérifie que le client ajouté est bien celui qu'on a ajouté

        verify(clientRepo, times(1)).save(client); // On vérifie que la méthode save() a été appelée une fois


    }

    @Test
    void getAllClient() {
        // On simule la récupération de tous les clients
        when(clientRepo.findAll()).thenReturn(List.of(client));

        // On récupère la liste des clients
        List<Client> clients = clientService.getAllClient();

        // On vérifie que la liste contient bien le client qu'on a ajouté
        assertEquals(List.of(client), clients);

        // On vérifie que la méthode findAll() a été appelée une fois
        verify(clientRepo, times(1)).findAll();
    }

    @Test
    void getClientById() {

        // On simule la récupération d'un client par son id
        when(clientRepo.findById(1L)).thenReturn(java.util.Optional.of(client));

        // On récupère le client par son id
        Client clientFound = clientService.getClientById(1L);

        // On vérifie que le client récupéré est bien celui qu'on a ajouté
        assertEquals(client, clientFound);

        // On vérifie que la méthode findById() a été appelée une fois
        verify(clientRepo, times(1)).findById(1L);
    }

    @Test
    void getClientByEmail() {

        // On simule la récupération d'un client par son email
        when(clientRepo.findByEmail("test@gmail.com")).thenReturn(java.util.Optional.of(client));

        // On récupère le client par son email
        Client clientFoundByEmail = clientService.getClientByEmail("test@gmail.com");

        // On vérifie que le client récupéré est bien celui qu'on a ajouté
        assertEquals(client, clientFoundByEmail);

        // On vérifie que la méthode findByEmail() a été appelée une fois
        verify(clientRepo, times(1)).findByEmail("test@gmail.com");


    }

    @Test
    void getClientBySexe() {
        // On simule la récupération des clients par sexe
        when(clientRepo.findBySexe(Sexe.FEMME)).thenReturn(List.of(client));

        // On récupère la liste des clients par sexe
        List<Client> clientsBySexe = clientService.getClientBySexe("FEMME");

        // On vérifie que la liste contient bien le client qu'on a ajouté
        assertEquals(List.of(client), clientsBySexe);

        // On vérifie que la méthode findBySexe() a été appelée une fois
        verify(clientRepo, times(1)).findBySexe(Sexe.FEMME);
    }

    @Test
    void deleteClient() {
        // On simule la suppression d'un client
        when(clientRepo.existsById(1L)).thenReturn(true);

        // On supprime le client
        clientService.deleteClient(1L);

        // On vérifie que la méthode existsById() a été appelée une fois
        verify(clientRepo, times(1)).existsById(1L);

            // On vérifie que la méthode deleteById() a été appelée une fois
            verify(clientRepo, times(1)).deleteById(1L);
    }

    @Test
    void descactiverClient() {
        // On simule la désactivation d'un client
        when(clientRepo.findById(1L)).thenReturn(java.util.Optional.of(client));
        when(clientRepo.save(client)).thenReturn(client);

        // On désactive le client
        Client clientDesactived = clientService.descactiverClient(1L);

        // On vérifie que le client désactivé est bien celui qu'on a ajouté
        assertEquals(client, clientDesactived);

        // On vérifie que la méthode findById() a été appelée une fois
        verify(clientRepo, times(1)).findById(1L);

        // On vérifie que la méthode save() a été appelée une fois
        verify(clientRepo, times(1)).save(client);
    }

    @Test
    void updateClient() {
        // On simule la mise à jour d'un client
        when(clientRepo.findById(1L)).thenReturn(java.util.Optional.of(client));
        when(clientRepo.save(client)).thenReturn(client);

        // On met à jour le client
        Client clientUpdated = clientService.updateClient(client, 1L);

        // On vérifie que le client mis à jour est bien celui qu'on a ajouté
        assertEquals(client, clientUpdated);

        // On vérifie que la méthode findById() a été appelée une fois
        verify(clientRepo, times(1)).findById(1L);

        // On vérifie que la méthode save() a été appelée une fois
        verify(clientRepo, times(1)).save(client);
    }
}