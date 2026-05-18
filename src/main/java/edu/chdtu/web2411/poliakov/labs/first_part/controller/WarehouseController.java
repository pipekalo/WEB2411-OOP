package edu.chdtu.web2411.poliakov.labs.first_part.controller;

import edu.chdtu.web2411.poliakov.labs.first_part.ConsoleWriter;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceType;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.ControllerArrivalException;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Invoice;
import edu.chdtu.web2411.poliakov.labs.first_part.model.InvoiceReceipt;
import edu.chdtu.web2411.poliakov.labs.first_part.model.ReceiptItem;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;

import java.util.List;
import java.util.Optional;

public class WarehouseController {
    private final Warehouse warehouse;
    private final ConsoleWriter consoleWriter;

    public WarehouseController(Warehouse warehouse, ConsoleWriter consoleWriter) {
        this.warehouse = warehouse;
        this.consoleWriter = consoleWriter;
    }


    public InvoiceReceipt handleArrival(String supplier, String invoiceId, List<ReceiptItem> receiptItemList) {

        validateInput(supplier, invoiceId, receiptItemList);

        Invoice invoice = new Invoice(invoiceId, InvoiceType.INCOMING);

        for (ReceiptItem item : receiptItemList) {
            if (item.quantity() <= 0)
                throw new ControllerArrivalException("Кол-во товара не может быть равно нулю или меньше этого значения");
            Optional<String> code = warehouse.findByProductName(item.getProductName());
            if(code.isPresent()) {
                warehouse.putInCell(code.get(), item.product(), item.quantity());
            } else {
                String freeCode = warehouse.findFreeCell(item.quantity());
                warehouse.putInCell(freeCode, item.product(), item.quantity());
            }
            invoice.addItem(item.product(), item.quantity());
        }
        invoice.complete();

        return new InvoiceReceipt(invoice, supplier, consoleWriter);
    }

    public InvoiceReceipt handleRelease(String customer, String invoiceId, List<ReceiptItem> receiptItemList) {
        validateInput(customer, invoiceId, receiptItemList);

        Invoice invoice = new Invoice(invoiceId, InvoiceType.OUTGOING);

        for (ReceiptItem item : receiptItemList) {
            if (item.quantity() <= 0)
                throw new ControllerArrivalException("Кол-во товара не может быть равно нулю или меньше этого значения");
            Optional<String> code = warehouse.findByProductName(item.getProductName());
            if (!code.isPresent())
                throw new ControllerArrivalException("Такого товара нет на складе");

            warehouse.takeFromCell(item.getProductName(), item.quantity());

            invoice.addItem(item.product(), item.quantity());
        }

        invoice.complete();

        return new InvoiceReceipt(invoice, customer, consoleWriter);
    }

    private void validateInput(String counterparty, String invoiceId, List<ReceiptItem> items) {
        if(counterparty == null || counterparty.isEmpty())
            throw new ControllerArrivalException("Наименование контрагента не может быть пустым");

        if(invoiceId  == null || invoiceId.isEmpty())
            throw new ControllerArrivalException("Индификатор накладной не может быть пустым");


        if (items == null || items.isEmpty())
            throw new ControllerArrivalException("Список с товарами не может быть пустым");
    }
}
