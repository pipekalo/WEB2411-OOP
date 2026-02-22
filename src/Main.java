
//  TODO: Варіант 7: Складський облік.
//   Сутності: Накладна, Товар, Склад, Осередок.
//   Завдання: Перевірити місткість осередку перед додаванням товару та розрахувати загальну вартість товарів на складі.

import edu.chdtu.web2411.poliakov.labs.first_part.enums.product.ProductUnitOfMeasure;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Product;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;
import edu.chdtu.web2411.poliakov.labs.first_part.model.cell.Cell;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Cell cell = warehouse.addCell("A", 3,7, 100.0);

        Product milk = new Product("Milk", ProductUnitOfMeasure.LITER, 20.0, 20, LocalDate.now());
        Product milk2 = new Product("Milk", ProductUnitOfMeasure.LITER, 20.0, 20, LocalDate.now());

        cell.addInCell(milk, 50);
        cell.addInCell(milk, 51);
        System.out.println(cell.getFreeSpace());
        System.out.println(cell.canFit(30));

    }
}