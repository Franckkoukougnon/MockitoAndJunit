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

}
