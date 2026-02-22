package edu.chdtu.web2411.poliakov.labs.first_part.model;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.product.ProductUnitOfMeasure;

import java.time.LocalDate;

public class Product {
    private static int counter = 0;

    private String name;
    private String barcode;
    private ProductUnitOfMeasure unitOfMeasure;

    private Double price;
    private Integer currentStock;
    private LocalDate expiryDate;

    private String countryCode = "482";
    private String manufacturerCode = "0000";
    private String productCode = "00000";


    public Product(String name, ProductUnitOfMeasure unitOfMeasure, Double price, Long expiryDate)
    {
        this.name = name;
        this.barcode = this.generateBarcode();
        this.unitOfMeasure = unitOfMeasure;
        this.price = price;
        this.expiryDate = LocalDate.now().plusDays(expiryDate);
    }

    public String getBarcode() {
        return this.barcode;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public ProductUnitOfMeasure getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    private String generateBarcode() {
        counter++;
        String ean = this.countryCode + this.manufacturerCode + String.format("%05d", counter);
        int sum = 0;

        for (int i = 0; i < ean.length(); i++) {
            if(i % 2 == 1) {
                sum += +ean.charAt(i) * 3;
            } else {
                sum += +ean.charAt(i);
            }
        }
        return ean + ((10 - (sum % 10)) % 10);
    }
}
