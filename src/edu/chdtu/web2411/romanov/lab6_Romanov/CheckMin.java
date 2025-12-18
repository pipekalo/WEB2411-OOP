package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.util.List;

public class CheckMin extends OrderCheck {
    @Override
    public void check(List<Product> products, double price) {
        if (price < 1000) {
            System.out.println(" ПОМИЛКА: Сума " + price + " грн замала. Магазин працює від 1000 грн.");
        } else {
            System.out.println(" Сума замовлення допустима.");
            if (next != null) next.check(products, price);
        }
    }
}
