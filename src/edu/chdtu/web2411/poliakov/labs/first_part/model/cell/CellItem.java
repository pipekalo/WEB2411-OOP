package edu.chdtu.web2411.poliakov.labs.first_part.model.cell;

import edu.chdtu.web2411.poliakov.labs.first_part.model.Product;

public class CellItem {
    private Integer id;
    private Product product;
    private Integer quantity;

    public CellItem(Integer id, Product product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
}
