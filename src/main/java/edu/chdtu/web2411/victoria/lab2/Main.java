package edu.chdtu.web2411.victoria.lab2;

import edu.chdtu.web2411.victoria.lab2.models.IndividualClient;
import edu.chdtu.web2411.victoria.lab2.services.Manager;

public class Main {
    public static void main(String[] args) {
        // 1. Створення клієнтів (Демонстрація КОМПОЗИЦІЇ: Адреса створюється при new IndividualClient)
        IndividualClient client1 = new IndividualClient(
            1, "Oleg Petrenko", 1500.00, "Cherkasy", "Blagovisna 10", "AB123456"
        );
        
        IndividualClient client2 = new IndividualClient(
            2, "Maria Ivanova", 3200.50, "Kyiv", "Shevchenka 5", "CD654321"
        );

        // 2. Робота Менеджера (Демонстрація АГРЕГАЦІЇ)
        Manager salesManager = new Manager("Sales Dept");

        // Менеджер бере на обслуговування вже існуючих клієнтів
        salesManager.assignClient(client1);
        salesManager.assignClient(client2);

        // Виведення звіту менеджера
        salesManager.showReport();
    }
}