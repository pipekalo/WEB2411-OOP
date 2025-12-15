package edu.chdtu.web2411.victoria.lab1;

import edu.chdtu.web2411.victoria.lab1.models.CorporateClient;
import edu.chdtu.web2411.victoria.lab1.models.IndividualClient;
import edu.chdtu.web2411.victoria.lab1.services.ClientBase;
import edu.chdtu.web2411.victoria.lab1.services.MathUtils;

public class Main {
    public static void main(String[] args) {
        // 1. Робота з ієрархією класів
        ClientBase database = new ClientBase();

        IndividualClient client1 = new IndividualClient(1, "Oleg Petrenko", 1500.00, "AB123456");
        CorporateClient client2 = new CorporateClient(2, "Tech Solutions Ltd", 50000.00, "TAX-998877");
        IndividualClient client3 = new IndividualClient(3, "Maria Ivanova", 3200.50, "CD654321");

        database.addClient(client1);
        database.addClient(client2);
        database.addClient(client3);

        // Виведення бази
        database.showAllClients();

        // 2. Математична задача (Сума масиву)
        System.out.println("\n--- Math Task: Sum of Array ---");
        int[] numbers = {10, 20, 5, 3, 2};
        int sum = MathUtils.sumArray(numbers);
        
        System.out.print("Array elements: ");
        for(int n : numbers) System.out.print(n + " ");
        System.out.println("\nCalculated Sum: " + sum);
    }
}