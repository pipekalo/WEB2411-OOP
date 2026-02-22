package edu.chdtu.web2411.poliakov.labs.first_part.model;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceType;
import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.InvoiceQuantityValidateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Invoice {
    private String id;
    private LocalDate createdAt;
    private InvoiceStatus status;
    private InvoiceType type;
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();;

    public Invoice(String id, InvoiceType type) {
        this.id = id;
        this.type = type;
        this.createdAt = LocalDate.now();
        this.status = InvoiceStatus.CREATED;
    }

    public void addItem(Product product, Integer quantity) {
        if(quantity <= 0) throw new InvoiceQuantityValidateException("Количество должно быть > 0");
        Optional<InvoiceItem> existing = this.findExistingItem(product);
        if(existing.isPresent()) {
            System.out.println(existing.get().quantity());
            this.invoiceItemList.remove(existing.get());
            this.invoiceItemList.add(new InvoiceItem(existing.get().id(), product, existing.get().quantity() + quantity));
        } else {
            this.invoiceItemList.add(new InvoiceItem(
                    this.invoiceItemList.size() + 1, product, quantity));
        }
    }

    private Optional<InvoiceItem> findExistingItem(Product product) {
        return this.invoiceItemList.stream().filter(invoiceItem -> invoiceItem.product().getName().equals(product.getName()) && Double.compare(product.getPrice(), invoiceItem.product().getPrice()) == 0).findFirst();
    }


    public List<InvoiceItem> getItems() {
        return this.invoiceItemList;
    }

    public double calculateCost() {
        return this.invoiceItemList.stream().mapToDouble(c -> c.quantity() * c.product().getPrice()).sum();
    }

    public void complete() {
        this.status = InvoiceStatus.COMPLETED;
    }
    public void cancelled() {
        this.status = InvoiceStatus.CANCELLED;
    }
    public InvoiceType getType() {
        return this.type;
    }
    public String getId() {
        return this.id;
    }
    public InvoiceStatus getStatus() {
        return this.status;
    }
    public LocalDate getCreatedAt() {
        return this.createdAt;
    }
}