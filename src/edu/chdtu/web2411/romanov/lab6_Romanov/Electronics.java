package edu.chdtu.web2411.romanov.lab6_Romanov;

public abstract class Electronics extends Product {
    private String warranty;

    public Electronics(int id, String name, double price, String warranty) {
        super(id, name, price);
        this.warranty = warranty;
    }

    public String getWarranty() { return warranty; }

    public abstract String getCategory();

    @Override
    public String toString() {
        return super.toString()
                + " [Електроніка | " + getCategory()
                + " | Гарантія: " + warranty + "]";
    }
}