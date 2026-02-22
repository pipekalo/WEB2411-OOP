package edu.chdtu.web2411.poliakov.labs.first_part.model;


import edu.chdtu.web2411.poliakov.labs.first_part.enums.cell.CellStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private List<Cell> cellList = new ArrayList<>();

    public Cell addCell(String row, Integer rack, Integer shelf, Double maxCapacity) {
        Cell cell = new Cell(row, rack, shelf, maxCapacity, this);
        this.cellList.add(cell);
        return  cell;
    }

    public List<Cell> getFreeCells() {
        return this.cellList.stream().filter(c -> c.getStatus() == CellStatus.FREE).toList();
    }

    public boolean hasFreeSpace() {
        return !this.getFreeCells().isEmpty();
    }
}
