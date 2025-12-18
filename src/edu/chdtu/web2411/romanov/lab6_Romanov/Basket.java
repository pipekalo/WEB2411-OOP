package edu.chdtu.web2411.romanov.lab6_Romanov;

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
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }

        System.out.println("\n--- ПОЧАТОК ОФОРМЛЕННЯ ЗАМОВЛЕННЯ ---");

        OrderCheck chain = new OrderCheckKeyFactory().buildChain();
        chain.check(items, total);

        System.out.println("----------------------------------");
    }

}