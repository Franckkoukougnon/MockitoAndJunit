package com.tuto.mockito.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Client")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String numero_telephone;
    private LocalDate date_naissance;

    @Enumerated(EnumType.STRING)
    private Sexe sexe; // Énumération gérée dans le code

    @Column(nullable = false)
    private boolean is_active = true; // Actif par défaut
}

