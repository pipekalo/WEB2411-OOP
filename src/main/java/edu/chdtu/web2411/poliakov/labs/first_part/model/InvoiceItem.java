package edu.chdtu.web2411.poliakov.labs.first_part.model;

public record InvoiceItem(Integer id, Product product, Integer quantity) {
    public String getProductName() {
        return product.getName();
    }
    public Double getItemTotal() {
        return product.getPrice() * quantity;
    }
}
