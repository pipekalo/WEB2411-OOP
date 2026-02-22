package edu.chdtu.web2411.poliakov.labs.first_part.exeptions;

public class QuantityExceededOfCapacityCellException extends RuntimeException {
    public QuantityExceededOfCapacityCellException(String message) {
        super(message);
    }
}
