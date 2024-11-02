-- V1__Create_client_table.sql
CREATE TABLE IF NOT EXISTS Client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    numero_telephone VARCHAR(15),
    date_naissance DATE,
    sexe VARCHAR(10),
    is_active BOOLEAN DEFAULT TRUE
);
