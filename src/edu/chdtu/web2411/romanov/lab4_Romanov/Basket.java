package edu.chdtu.web2411.romanov.lab4_Romanov;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Product> items = new ArrayList<>();

    public void add(Product product) {
        items.add(product);
        System.out.println("Товар '" + product.getName() + "' додано в кошик.");
    }

    public void showBasket() {
        if (items.isEmpty()) {
            System.out.println("Кошик порожній.");
        } else {
            System.out.println("\n--- ВАШ КОШИК ---");
            for (Product p : items) {
                System.out.println(p);
            }
        }
    }

    public void buy() {
        if (items.isEmpty()) {
            System.out.println("Кошик порожній.");
            return;
        }
        double total = 0;
        System.out.println("\n--- ЧЕК ---");
        for (Product p : items) {
            System.out.println(p.getName() + " ... " + p.getPrice() + " грн");
            total += p.getPrice();
        }
        System.out.println("----------------");
        System.out.println("РАЗОМ: " + total + " грн");
        System.out.println("Дякуємо за покупку!");
        items.clear();
    }
}