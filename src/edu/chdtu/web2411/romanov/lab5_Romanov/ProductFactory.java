package edu.chdtu.web2411.romanov.lab5_Romanov;

public class ProductFactory {

    public Product createProduct(int type, int id, String name, double price, String extraParam) {
        if (type == 1) {
            return new Electronics(id, name, price, extraParam);
        } else if (type == 2) {
            return new Literature(id, name, price, extraParam);
        } else {
            return new Product(id, name, price);
        }
    }
}