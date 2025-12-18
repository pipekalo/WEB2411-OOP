package edu.chdtu.web2411.romanov.lab6_Romanov;

public class ProductFactory {
    private final ElectronicsFactory electronicsFactory = new ElectronicsFactory();

    public Product createProduct(int type, int subType, int id, String name, double price, String extra) {
        return switch (type) {
            case 1 -> electronicsFactory.create(subType, id, name, price, extra);
            case 2 -> new Literature(id, name, price, extra);
            default -> throw new IllegalArgumentException("Невірний тип");
        };
    }
}
