package edu.chdtu.web2411.romanov.lab6_Romanov;

public class Literature extends Product {

    private String author;

    public Literature(int id, String name, double price, String author) {
        super(id, name, price);
        this.author = author;
    }

    @Override
    public String toString() {
        return super.toString() + " [Література | Автор: " + author + "]";
    }
}