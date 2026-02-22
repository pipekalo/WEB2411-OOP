package edu.chdtu.web2411.poliakov.labs.first_part.model;

import edu.chdtu.web2411.poliakov.labs.first_part.ConsoleWriter;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceType;

public class InvoiceReceipt {
    private Invoice invoice;
    private String counterparty;
    private ConsoleWriter consoleWriter;

    public InvoiceReceipt(Invoice invoice, String counterparty, ConsoleWriter consoleWriter) {
        this.invoice = invoice;
        this.counterparty = counterparty;
        this.consoleWriter = consoleWriter;
    }

    public void printInvoice() {
        String typeLabel = invoice.getType() == InvoiceType.INCOMING
                ? "ПРИХОДНАЯ" : "РАСХОДНАЯ";

        consoleWriter.print("\n┌─────────────────────────────────────────┐");
        consoleWriter.print( "│  "+ typeLabel + " НАКЛАДНАЯ " + invoice.getId());
        consoleWriter.print( "│  Дата: "+ invoice.getCreatedAt());
        consoleWriter.print( "│  Контрагент: "+ counterparty);
        consoleWriter.print("├────────────────────┬─────────┬───────────┤");
        consoleWriter.print("│ Товар              │ Кол-во  │ Сумма     │");
        consoleWriter.print("├────────────────────┼─────────┼───────────┤");

        for (InvoiceItem item : invoice.getItems()) {
            consoleWriter.print("│ "+ item.getProductName() +"            │ "+ item.quantity() + "  │ "+ item.getItemTotal() +" │");
        }

        consoleWriter.print("├────────────────────┴─────────┼───────────┤");
        consoleWriter.print( "│ Итого:                        │ " + invoice.calculateCost() + " │");
        consoleWriter.print( "│ Статус: "+ invoice.getStatus());
        consoleWriter.print("└───────────────────────────────┴───────────┘\n");
    }
}
