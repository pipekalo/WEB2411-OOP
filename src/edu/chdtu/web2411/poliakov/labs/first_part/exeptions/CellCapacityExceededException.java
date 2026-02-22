package edu.chdtu.web2411.poliakov.labs.first_part.exeptions;

public class CellCapacityExceededException extends RuntimeException {
    public CellCapacityExceededException(Integer requested, Double freeSpace) {
        super(String.format("Cannot fit %d units into cell. Free space: %.0f", requested, freeSpace));
    }
}
