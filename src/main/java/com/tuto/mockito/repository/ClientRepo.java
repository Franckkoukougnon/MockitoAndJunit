package com.tuto.mockito.repository;

import com.tuto.mockito.entity.Client;
import com.tuto.mockito.enums.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    List<Client> findBySexe(Sexe sexe); // Renvoyer une liste de clients
}

