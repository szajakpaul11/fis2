package com.company.project.model.repository;

import com.company.project.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUsername(String username);
}

