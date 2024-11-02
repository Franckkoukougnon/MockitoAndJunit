package com.tuto.mockito.controller;


import com.tuto.mockito.entity.Client;
import com.tuto.mockito.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Tag(name = "Client", description = "API pour les opérations sur les clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping("/add")
    @Operation(summary = "Add a new client")
    public ResponseEntity<Client>  addClient(@Valid @RequestBody Client client) {
        Client clientSaved =  clientService.addClient(client);
        return new ResponseEntity<>(clientSaved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Afficher la liste de client")
    public ResponseEntity<List<Client>> listOfClient(){
      return   ResponseEntity.ok(clientService.getAllClient());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Afficher un client par son id")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Afficher un client par son email")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email){
        return ResponseEntity.ok(clientService.getClientByEmail(email));
    }

    @GetMapping("/sexe/{sexe}")
    @Operation(summary = "Afficher les clients par sexe")
    public ResponseEntity<List<Client>> getClientsBySexe(@PathVariable String sexe) {
        return ResponseEntity.ok(clientService.getClientBySexe(sexe));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer un client par son id")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/desactiver/{id}")
    @Operation(summary = "Désactiver un client par son id")
    public ResponseEntity<Client> desactiverClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.descactiverClient(id));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Mettre à jour un client")
    public ResponseEntity<Client> updateClient(@RequestBody Client updatedClientData, @PathVariable Long id) {
        Client updatedClient = clientService.updateClient(updatedClientData, id);
        return updatedClient != null ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
    }


}
