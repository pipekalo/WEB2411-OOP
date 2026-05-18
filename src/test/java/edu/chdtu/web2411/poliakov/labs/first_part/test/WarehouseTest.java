package edu.chdtu.web2411.poliakov.labs.first_part.test;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceType;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.product.ProductUnitOfMeasure;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.CellNoFreeAvailableException;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.InvoiceQuantityValidateException;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Cell;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Invoice;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Product;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WarehouseTest {

    private Product apple;
    private Product milk;

    @BeforeEach
    void setUp() {
        apple = new Product("Apple", ProductUnitOfMeasure.KILOGRAM, 25.0, 30L);
        milk  = new Product("Milk",  ProductUnitOfMeasure.LITER, 40.0, 7L);
    }

    @Test
    void invoice_calculateCost_returnsCorrectSum() {
        Invoice invoice = new Invoice("INV-001", InvoiceType.INCOMING);
        invoice.addItem(apple, 3);
        invoice.addItem(milk, 2);

        assertEquals(155.0, invoice.calculateCost(), 0.001);
    }

    @Test
    void invoice_addSameProductTwice_quantitySummed() {
        Invoice invoice = new Invoice("INV-002", InvoiceType.INCOMING);
        invoice.addItem(apple, 3);
        invoice.addItem(apple, 2);

        assertEquals(1, invoice.getItems().size());
        assertEquals(5, invoice.getItems().get(0).quantity());
    }

    @Test
    void invoice_addItemWithZeroQuantity_throwsException() {
        Invoice invoice = new Invoice("INV-003", InvoiceType.INCOMING);

        assertThrows(InvoiceQuantityValidateException.class,
                () -> invoice.addItem(apple, 0));
    }

    @Test
    void invoice_complete_statusIsCompleted() {
        Invoice invoice = new Invoice("INV-004", InvoiceType.OUTGOING);
        invoice.complete();

        assertEquals(InvoiceStatus.COMPLETED, invoice.getStatus());
    }

    @Test
    void cell_addBeyondCapacity_throwsException() {
        Cell cell = new Cell("1-1-1");

        assertThrows(Exception.class,
                () -> cell.addInCell(apple, 150));
    }

    @Test
    void warehouse_findFreeCell_throwsWhenNoSpace() {
        Warehouse warehouse = new Warehouse(1, 1, 1, code -> new Cell(code));

        warehouse.putInCell("1-1-1", apple, 100);

        assertThrows(CellNoFreeAvailableException.class,
                () -> warehouse.findFreeCell(1));
    }

    @Test
    void warehouse_takeFromCell_throwsWhenNotEnough() {
        Warehouse warehouse = new Warehouse(1, 1, 1, code -> new Cell(code));
        warehouse.putInCell("1-1-1", apple, 5);

        assertThrows(IllegalArgumentException.class,
                () -> warehouse.takeFromCell("Apple", 10));
    }
}