package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.util.List;

public class CheckStock extends OrderCheck {
    @Override
    public void check(List<Product> products, double price) {
        System.out.println(" Склад: Товари зарезервовано.");
        if (next != null) next.check(products, price);
    }
}