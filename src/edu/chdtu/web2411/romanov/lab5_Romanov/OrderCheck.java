package edu.chdtu.web2411.romanov.lab5_Romanov;

import java.util.List;

public abstract class OrderCheck {
    protected OrderCheck next;

    public void setNext(OrderCheck next) {
        this.next = next;
    }

    public abstract void check(List<Product> products, double price);
}