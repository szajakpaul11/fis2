package com.company.project.model.service;

import com.company.project.model.Client;
import com.company.project.model.Order;
import com.company.project.model.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return (Client) clientRepository.findById(id).orElse(null);
    }

    public Client addClient(Client client) {
        String clientName = client.getName();
        if (clientName == null || clientName.isEmpty()) {
            throw new IllegalArgumentException("Invalid client data");
        }
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = (Client) clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            String updatedName = updatedClient.getName();
            if (updatedName != null && !updatedName.isEmpty()) {
                existingClient.setName(updatedName);
            }
            // Alte actualizări de câmpuri după nevoie
            return clientRepository.save(existingClient);
        }
        return null;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public void addOrderToClient(Long clientId, Order order) {
        Client client = (Client) clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            client.addOrder(order); // Adăugăm comanda la lista de comenzi a clientului
            clientRepository.save(client); // Salvăm clientul actualizat în baza de date
        }
    }

    public void updateClientBalance(Long clientId, double amount) {
        Client client = (Client) clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            double currentBalance = client.getBalance();
            client.setBalance(currentBalance + amount); // Actualizăm soldul clientului cu suma specificată
            clientRepository.save(client); // Salvăm clientul actualizat în baza de date
        }
    }

    // Alte metode specifice pentru gestionarea clienților pot fi adăugate aici, dacă este nevoie
}
