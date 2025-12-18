package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.util.List;

public class CheckQuantity extends OrderCheck {
    @Override
    public void check(List<Product> products, double price) {
        if (products.size() > 5) {
            System.out.println(" ПОМИЛКА: Ви обрали " + products.size() + " товарів. Максимум дозволено 5 шт.");
        } else {
            System.out.println(" Кількість товарів у нормі.");
            if (next != null) next.check(products, price);
        }
    }
}
