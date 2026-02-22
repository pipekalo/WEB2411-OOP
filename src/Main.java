
//  TODO: Варіант 7: Складський облік.
//   Сутності: Накладна, Товар, Склад, Осередок.
//   Завдання: Перевірити місткість осередку перед додаванням товару та розрахувати загальну вартість товарів на складі.

import edu.chdtu.web2411.poliakov.labs.first_part.ConsoleWriter;
import edu.chdtu.web2411.poliakov.labs.first_part.controller.WarehouseController;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.product.ProductUnitOfMeasure;
import edu.chdtu.web2411.poliakov.labs.first_part.model.*;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        WarehouseController controller = new WarehouseController(warehouse, new ConsoleWriter());

        Product apple = new Product("Яблоко", ProductUnitOfMeasure.KILOGRAM, 15.0, 30L);
        Product apple1 = new Product("Яблоко", ProductUnitOfMeasure.KILOGRAM, 15.0, 30L);
        Product milk  = new Product("Молоко", ProductUnitOfMeasure.LITER, 30.0, 7L);


        InvoiceReceipt incomingReceipt = controller.handleArrival("Test", "INV-№001", List.of(
                new ReceiptItem(apple, 10),
                new ReceiptItem(apple1, 20),
                new ReceiptItem(milk, 40)
        ));

        incomingReceipt.printInvoice();

        System.out.println(warehouse);

        InvoiceReceipt outcomingReceipt = controller.handleRelease("Test2", "INV-№002", List.of(
                new ReceiptItem(apple, 50)
        ));

        outcomingReceipt.printInvoice();

        System.out.println(warehouse);
    }
}