package com.company.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    private double balance; // Soldul clientului

    // Constructor implicit
    public Client() {
        // Constructor implicit necesar pentru JPA
    }

    // Metodele getters și setters pentru câmpurile clasei

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Metoda pentru adăugarea unei comenzi la lista de comenzi ale clientului
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Order getOrder() {
        return null;
    }

    // Alte metode specifice pentru gestionarea contului clientului pot fi adăugate aici, dacă este nevoie
}
