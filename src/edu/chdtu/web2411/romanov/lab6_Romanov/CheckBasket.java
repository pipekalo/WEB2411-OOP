package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.util.List;

public class CheckBasket extends OrderCheck {
    @Override
    public void check(List<Product> products, double price) {
        if (products.isEmpty()) {
            System.out.println(" ПОМИЛКА: Кошик порожній! Додайте хоч щось.");
        } else {
            System.out.println(" Кошик перевірено (не пустий).");
            if (next != null) next.check(products, price);
        }
    }
}
