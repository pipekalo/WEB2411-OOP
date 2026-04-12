package edu.chdtu.web2411.poliakov.labs.first_part.model;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.cell.CellStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.CellCapacityExceededException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QueueCell extends  Cell{

    public QueueCell(String code) {
        super(code);
        items = new LinkedList<>();
    }

    @Override
    public void removeFromCell(String productName, Integer quantity) {
        int remaining = quantity;

        List<CellItem> matches = items.stream().filter(c -> c.product().getName().equals(productName)).sorted((a, b) -> a.addedAt().compareTo(b.addedAt())).toList();

        for (CellItem item : matches) {
            if (remaining <= 0) break;

            if(item.quantity() <= remaining) {
                remaining -= item.quantity();
                items.remove(item);
            } else {
                items.remove(item);
                items.add(item.addQuantity(remaining * (-1)));
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
        items.add(cellItem);
        this.currentLoad += quantity;
        this.status = CellStatus.OCCUPIED;

        return new ArrayList<>(items);
    }
}
