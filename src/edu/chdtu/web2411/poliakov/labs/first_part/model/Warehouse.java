package edu.chdtu.web2411.poliakov.labs.first_part.model;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceType;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.CellNoFreeAvailableException;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.InvoiceTypeException;

import java.util.*;

public class Warehouse {
    private final HashMap<String, Cell> cellHashMap;
    private final List<Invoice> history = new ArrayList<Invoice>();


    public Warehouse() {
        this.cellHashMap = new LinkedHashMap<>(1 * 2 * 5);
        generateCells(1, 2, 5);
    }

    public Warehouse(Integer rows, Integer racks, Integer shelf) {
        this.cellHashMap = new LinkedHashMap<String, Cell>(rows * racks * shelf);
        this.generateCells(rows, racks, shelf);
    }

    public void putInCell(String code, Product product, Integer quantity) {
        Cell foundedCell = findCellByCode(code);
        foundedCell.addInCell(product, quantity);
    }

    public void takeFromCell(String productName, Integer quantity) {
        int remaining = quantity;
        for(Cell cell : cellHashMap.values()) {
            if(remaining <= 0) break;
            int inCell = cell.getProductQuantity(productName);
            int toRemove = Math.min(remaining, inCell);
            cell.removeFromCell(productName, toRemove);
            remaining -= toRemove;
        }

        if (remaining > 0)
            throw new IllegalArgumentException("Недостаточно товара: " + productName);
    }

    public Optional<String> findByProductName(String name) {
        return this.cellHashMap.values().stream().filter(cell -> cell.hasProductByName(name)).findFirst().map(Cell::getCode);
    }

    private Cell findCellByCode(String code) {
        return this.cellHashMap.values().stream().filter(c -> c.getCode().equals(code)).findFirst().orElseThrow(() -> new CellNoFreeAvailableException("Свободная ячейка не найдена"));
    }

    public String findFreeCell(int quantity) {
        return this.cellHashMap.values().stream()
                .filter(c -> c.canFit(quantity))
                .findFirst()
                .orElseThrow(() -> new CellNoFreeAvailableException("Свободная ячейка не найдена")).getCode();
    }

    private void generateCells(int rows, int racks, int shelf) {
        for (int r = 1; r <= rows; r++) {
            for (int ra = 1; ra <= racks; ra++) {
                for (int s = 1; s <= shelf; s++) {
                    String code = r + "-" + ra + "-" + s;
                    this.cellHashMap.put(code, new Cell(code));
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
        ╔══════════════════════════════════════════╗
        ║              СКЛАД                       ║
        ╠══════════════════════════════════════════╣
        """);

        cellHashMap.forEach((code, cell) -> sb.append(cell + "\n"));

        sb.append("╚══════════════════════════════════════════╝");
        return sb.toString();
    }
}
