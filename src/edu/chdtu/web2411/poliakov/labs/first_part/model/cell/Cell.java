package edu.chdtu.web2411.poliakov.labs.first_part.model.cell;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.cell.CellStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.cell.CellType;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.CellCapacityExceededException;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Product;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Double maxCapacity;
    private Double currentLoad;

    private CellType type;
    private CellStatus status;

    private String code;
    private String row;
    private Integer rack;
    private Integer shelf;

    private Warehouse warehouse;

    private List<CellItem> items = new ArrayList<>();


    public Cell(String row, Integer rack, Integer shelf, Double maxCapacity, Warehouse warehouse) {
        this.row = row;
        this.rack = rack;
        this.shelf = shelf;
        this.code = this.generateCode();
        this.maxCapacity = maxCapacity;
        this.warehouse = warehouse;
        this.status = CellStatus.FREE;
        this.currentLoad = 0.0;
    }

    private String generateCode() {
        return  this.row + "-" + this.rack + "-" + this.shelf;
    }

    public Double getFreeSpace() {
        return this.maxCapacity - this.currentLoad;
    }

    public boolean canFit(Integer quantity) {
        return this.getFreeSpace() >= quantity;
    }

    public CellStatus getStatus() {
        return this.status;
    }

    public CellItem addInCell(Product product, Integer quantity) {
        if(!this.canFit(quantity)) {
            throw new CellCapacityExceededException(quantity, this.getFreeSpace());
        }

        CellItem  cellItem = new CellItem(this.items.size() + 1, product, quantity);
        items.add(cellItem);
        this.currentLoad += quantity;
        this.status = CellStatus.OCCUPIED;
        return cellItem;
    }
}
