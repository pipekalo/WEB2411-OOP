package edu.chdtu.web2411.poliakov.labs.first_part.model;

public record ReceiptItem(Product product, Integer quantity) {
    public String getProductName() {
        return product.getName();
    }
}
