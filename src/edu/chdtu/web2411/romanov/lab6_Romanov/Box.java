package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.util.ArrayList;
import java.util.List;

public class Box extends Product {
    private List<Product> items = new ArrayList<>();


    public Box(int id, String name) {
        super(id, name, 0);
    }

    public void add(Product p) {
        items.add(p);
    }

    public void remove(Product p) {
        items.remove(p);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return " НАБІР '" + getName() + "' (Всередині: " + items.size() + " шт.) — Ціна: " + getPrice() + " грн";
    }
}