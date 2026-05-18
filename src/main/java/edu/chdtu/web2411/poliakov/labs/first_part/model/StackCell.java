package edu.chdtu.web2411.poliakov.labs.first_part.model;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.cell.CellStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.CellCapacityExceededException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//LIFO
public class StackCell extends Cell {

    public StackCell(String code) {
        super(code);
        items = new LinkedList<>();
    }

    @Override
    public void removeFromCell(String productName, Integer quantity) {
        int remaining = quantity;

        List<CellItem> matches = items.stream().filter(c -> c.product().getName().equals(productName)).sorted((a, b) -> b.addedAt().compareTo(a.addedAt())).toList();

        for (CellItem item : matches) {
            if (remaining <= 0) break;

            if(item.quantity() <= remaining) {
                remaining -= item.quantity();
                items.remove(item);
            } else {
                items.remove(item);
                items.addFirst(item.addQuantity(remaining * (-1)));
                remaining = 0;
            }
        }

        this.currentLoad -= quantity;
    }

    @Override
    public List<CellItem> addInCell(Product product, Integer quantity) {
        if(!this.canFit(quantity)) {
            throw new CellCapacityExceededException(quantity, this.getFreeSpace());
        }

        CellItem  cellItem = new CellItem(product, quantity, LocalDate.now());
        items.addLast(cellItem);
        this.currentLoad += quantity;
        this.status = CellStatus.OCCUPIED;

        return new ArrayList<>(items);
    }
}
