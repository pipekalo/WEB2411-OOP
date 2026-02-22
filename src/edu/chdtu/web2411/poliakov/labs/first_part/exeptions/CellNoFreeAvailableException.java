package edu.chdtu.web2411.poliakov.labs.first_part.exeptions;

public class CellNoFreeAvailableException extends RuntimeException {
    public CellNoFreeAvailableException(String message) {
        super(message);
    }
}
