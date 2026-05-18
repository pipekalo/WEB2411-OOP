package edu.chdtu.web2411.poliakov.labs.first_part.generator;

import edu.chdtu.web2411.poliakov.labs.first_part.impl.ReportGenerator;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Cell;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;

import java.io.FileWriter;
import java.io.IOException;

public class CsvReportGenerator implements ReportGenerator {
    @Override
    public void generate(Warehouse warehouse, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter("data/" + fileName)) {
            for (Cell cell : warehouse.getCells()) {
                for (Cell.CellItem cellItem : cell.getItems()) {
                    writer.write(cell.getCode() + "," + cellItem.getName() + "," + cellItem.quantity() + "," + cellItem.getTotalCost() + "\n");
                }
            }
        }
    }
}
