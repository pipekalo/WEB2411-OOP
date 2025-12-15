package edu.chdtu.web2411.victoria.lab2.services;

import java.util.ArrayList;
import java.util.List;

import edu.chdtu.web2411.victoria.lab2.models.Client;

public class ClientBase {
    private List<Client> clients;

    public ClientBase() {
        this.clients = new ArrayList<>();
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void showAllClients() {
        System.out.println("\n--- Client Database ---");
        for (Client client : clients) {
            client.displayDetails();
        }
    }
}