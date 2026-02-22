package edu.chdtu.web2411.poliakov.labs.first_part.model;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.cell.CellStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.cell.CellType;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.CellCapacityExceededException;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Double maxCapacity = 100.0;
    private Double currentLoad;

    private CellType type;
    private CellStatus status;

    private String code;


    private List<CellItem> items = new ArrayList<>();


    public Cell(String code) {
        this.code = code;
        this.status = CellStatus.FREE;
        this.currentLoad = 0.0;
    }

    public String getCode() {
        return this.code;
    }

    public int getProductQuantity(String name) {
        return this.items.stream()
                .filter(i -> i.product().getName().equals(name))
                .mapToInt(CellItem::quantity)
                .sum();
    }

    public Double getFreeSpace() {
        return this.maxCapacity - this.currentLoad;
    }

    public boolean canFit(Integer quantity) {
        return this.getFreeSpace() >= quantity;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public List<CellItem> getItems() {
        return this.items;
    }

    public CellStatus getStatus() {
        return this.status;
    }

    public List<CellItem> addInCell(Product product, Integer quantity) {
        if(!this.canFit(quantity)) {
            throw new CellCapacityExceededException(quantity, this.getFreeSpace());
        }

        if(this.hasProduct(product)) {
            this.updateCell(product.getName(), quantity);
            return this.items;
        }

        CellItem  cellItem = new CellItem(product, quantity);
        items.add(cellItem);
        this.currentLoad += quantity;
        this.status = CellStatus.OCCUPIED;

        return this.items;
    }

    public void updateCell(String name, Integer quantity) {
        CellItem cellItem = this.items.stream().filter(c -> c.product().getName().equals(name)).findFirst().orElseThrow();

        this.items.remove(cellItem);
        this.items.add(cellItem.addQuantity(quantity));
        this.currentLoad += quantity;
    }

    public boolean hasProduct(Product product) {
        return this.items.stream().anyMatch(i -> i.product().equals(product));
    }

    public boolean hasProductByName(String name) {
        return this.items.stream().anyMatch(cellItem -> cellItem.product.getName().equals(name));
    }

    public void removeFromCell(String productName, Integer quantity) {
        int remaining = quantity;

        List<CellItem> matches = this.items.stream().filter(cellItem -> cellItem.product().getName().equals(productName)).toList();

        for (CellItem item : matches) {
            if (remaining <= 0) break;

            if(item.quantity() <= remaining) {
                remaining -= item.quantity();
                this.items.remove(item);
            } else {
                this.items.remove(item);
                this.items.add(item.addQuantity(remaining * (-1)));
                remaining = 0;
            }
        }

        this.currentLoad -= quantity;
    }



    @Override
    public String toString() {
        return "║ ЯЧЕЙКА: " + this.code + " | items: " + this.items.size() + " | quantity: " + this.items.stream().mapToDouble(CellItem::quantity).sum();
    }

    public record CellItem(Product product, Integer quantity) {
        public CellItem addQuantity(Integer amount) {
            return new CellItem(this.product, this.quantity + amount);
        }
    }
}
