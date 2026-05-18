package edu.chdtu.web2411.poliakov.labs.first_part;
//  TODO: Варіант 7: Складський облік.
//   Сутності: Накладна, Товар, Склад, Осередок.
//   Завдання: Перевірити місткість осередку перед додаванням товару та розрахувати загальну вартість товарів на складі.

import edu.chdtu.web2411.poliakov.labs.first_part.controller.WarehouseController;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.product.ProductUnitOfMeasure;
import edu.chdtu.web2411.poliakov.labs.first_part.factory.QueueCellFactory;
import edu.chdtu.web2411.poliakov.labs.first_part.generator.JSONReportGenerator;
import edu.chdtu.web2411.poliakov.labs.first_part.impl.ReportGenerator;
import edu.chdtu.web2411.poliakov.labs.first_part.model.InvoiceReceipt;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Product;
import edu.chdtu.web2411.poliakov.labs.first_part.model.ReceiptItem;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;

import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        Warehouse warehouse = new Warehouse(new QueueCellFactory()); // QueueCellFactory or StackCellFactory
        ReportGenerator generator = new JSONReportGenerator(); // JSONReportGenerator or CsvReportGenerator
        WarehouseController controller = new WarehouseController(warehouse, new ConsoleWriter());

        Product apple = new Product("Яблоко", ProductUnitOfMeasure.KILOGRAM, 15.0, 30L);
        Product apple1 = new Product("Яблоко", ProductUnitOfMeasure.KILOGRAM, 12.0, 30L);
        Product milk  = new Product("Молоко", ProductUnitOfMeasure.LITER, 30.0, 7L);


        InvoiceReceipt incomingReceipt = controller.handleArrival("Test", "INV-№001", List.of(
                new ReceiptItem(apple, 30),
                new ReceiptItem(apple1, 20),
                new ReceiptItem(milk, 40)
        ));

        incomingReceipt.printInvoice();
        System.out.println(warehouse);

        InvoiceReceipt outcomingReceipt = controller.handleRelease("Test2", "INV-№002", List.of(
                new ReceiptItem(apple1, 20)
        ));

        outcomingReceipt.printInvoice();

        System.out.println(warehouse);

        generator.generate(warehouse, "test.json");

    }
}