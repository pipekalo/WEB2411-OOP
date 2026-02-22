package edu.chdtu.web2411.poliakov.labs.first_part.exeptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
