package edu.chdtu.web2411.poliakov.labs.first_part.model.invoice;

import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceStatus;
import edu.chdtu.web2411.poliakov.labs.first_part.enums.invoice.InvoiceType;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;

import java.util.List;

public class Invoice {
    private Integer id;
    private Integer number;

    private InvoiceType type;
    private InvoiceStatus status;

    private List<InvoiceItem> invoiceItemList;

    private Warehouse warehouseFrom;
    private  Warehouse warehouseTo;

    private String responsiblePerson;


}

//- id
//- номер накладной       // "НК-0042"
//- дата создания
//- тип                   // ПРИХОД / РАСХОД / ПЕРЕМЕЩЕНИЕ
//- статус                // НОВАЯ / ПРОВЕДЕНА / ОТМЕНЕНА
//- список позиций        // какие товары и в каком количестве
//- склад-отправитель
//- склад-получатель
//- ответственное лицо