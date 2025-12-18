package edu.chdtu.web2411.romanov.lab6_Romanov;

public class OrderCheckKeyFactory {
    public OrderCheck buildChain() {
        OrderCheck a = new CheckBasket();
        OrderCheck b = new CheckMin();
        OrderCheck c = new CheckQuantity();
        OrderCheck d = new CheckStock();
        OrderCheck e = new CheckPayment();

        a.setNext(b);
        b.setNext(c);
        c.setNext(d);
        d.setNext(e);

        return a;
    }
}
