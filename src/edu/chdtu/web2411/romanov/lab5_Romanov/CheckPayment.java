package edu.chdtu.web2411.romanov.lab5_Romanov;

import java.util.List;

public class CheckPayment extends OrderCheck {
    @Override
    public void check(List<Product> products, double price) {
        System.out.println("\n Оплата " + price + " грн пройшла успішно.");
        System.out.println("Дякуємо за покупку!");
        products.clear();
    }
}
