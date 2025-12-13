package edu.chdtu.web2411.romanov.lab5_Romanov;

public class Electronics extends Product {

    private String warranty;

    public Electronics(int id, String name, double price, String warranty) {
        super(id, name, price);
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return super.toString() + " [Електроніка | Гарантія: " + warranty + "]";
    }
}