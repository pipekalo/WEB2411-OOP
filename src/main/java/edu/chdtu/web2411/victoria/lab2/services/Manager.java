package edu.chdtu.web2411.victoria.lab2.services;

import java.util.ArrayList;
import java.util.List;

import edu.chdtu.web2411.victoria.lab2.models.Client;

public class Manager {
    private String department;
    private List<Client> assignedClients; // Список для Агрегації

    public Manager(String department) {
        this.department = department;
        this.assignedClients = new ArrayList<>();
    }

    // Метод АГРЕГАЦІЇ: додаємо існуючого клієнта
    public void assignClient(Client client) {
        assignedClients.add(client);
    }

    public void showReport() {
        System.out.println("\n--- Manager Report (" + department + ") ---");
        for (Client client : assignedClients) {
            // Використовуємо метод з інтерфейсу IDisplayable (через спадкування)
            client.displayDetails();
        }
    }
}