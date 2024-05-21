package com.company.project.model.service;

import com.company.project.model.Client;
import com.company.project.model.Order;
import com.company.project.model.Product;
import com.company.project.model.ProductType;
import com.company.project.model.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return (Order) orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        // Verifică dacă produsele din comandă sunt disponibile în stoc sau dacă clientul are suficiente fonduri
        for (Product product : order.getProducts()) {
            if (!checkProductAvailability(product)) {
                throw new RuntimeException("Product " + product.getName() + " is not available in stock");
            }
        }
        if (!checkClientFunds(order.getClient())) {
            throw new RuntimeException("Client does not have sufficient funds to place the order");
        }

        // Salvează comanda în baza de date
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = (Order) orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            // Actualizează starea comenzii sau alte detalii specifice comenzii
            existingOrder.setStatus(updatedOrder.getStatus());
            existingOrder.setDescription(updatedOrder.getDescription());
            // Completează cu alte actualizări necesare
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private boolean checkProductAvailability(Product product) {
        // Verificăm disponibilitatea produsului în stoc
        int availableQuantity = product.getQuantity(); // Presupunem că avem o metodă getQuantity() în clasa Product care returnează cantitatea disponibilă în stoc
        return availableQuantity > 0; // Returnăm true dacă există cantitate disponibilă în stoc, altfel returnăm false
    }

    private boolean checkClientFunds(Client client) {
        // Verificăm dacă clientul are suficiente fonduri pentru a plăti comanda
        double orderTotal = calculateOrderTotal(client.getOrder()); // Calculăm totalul comenzii
        return client.getBalance() >= orderTotal; // Returnăm true dacă soldul clientului este mai mare sau egal cu totalul comenzii, altfel returnăm false
    }

    private double calculateOrderTotal(Order order) {
        // Calculăm totalul comenzii
        double total = 0;
        for (Product product : order.getProducts()) {
            total += product.getPrice(); // Adăugăm prețul fiecărui produs la totalul comenzii

            // Convertem tipul produsului la ProductType și verificăm dacă este un sistem pre-asamblat
            ProductType type = getProductType(product);
            if (type == ProductType.PRE_ASSEMBLED_SYSTEM) {
                total += 100; // Adăugăm taxa suplimentară pentru sistemele pre-asamblate
            }
        }
        return total;
    }

    // Metodă pentru a obține tipul produsului ca enum ProductType
    private ProductType getProductType(Product product) {
        // Implementăm logica pentru a obține tipul produsului
        // În acest exemplu, presupunem că avem o metodă getTip() în clasa Product care returnează tipul produsului sub formă de string
        // Vom folosi această metodă pentru a obține tipul și apoi îl vom converti la enum ProductType
        String typeAsString = String.valueOf(product.getType()); // Presupunem că avem o metodă getType() care returnează tipul sub formă de string
        return ProductType.valueOf(typeAsString); // Convertem string-ul la enum ProductType
    }
}
