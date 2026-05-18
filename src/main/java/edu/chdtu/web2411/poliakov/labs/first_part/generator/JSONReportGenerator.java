package edu.chdtu.web2411.poliakov.labs.first_part.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.chdtu.web2411.poliakov.labs.first_part.impl.ReportGenerator;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Cell;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONReportGenerator implements ReportGenerator {
    @Override
    public void generate(Warehouse warehouse, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> report = new ArrayList<>();

        for (Cell cell: warehouse.getCells()) {
            if (cell.getItems().isEmpty()) continue;
            Map<String, Object> data = new HashMap<>();
            data.put("cellCode", cell.getCode());

            List<Map<String, Object>> items = new ArrayList<>();
            for (Cell.CellItem cellItem : cell.getItems()) {
                Map<String, Object> itemData = new HashMap<>();
                itemData.put("productName", cellItem.getName());
                itemData.put("quantity", cellItem.quantity());
                itemData.put("totalCost", cellItem.getTotalCost());

                items.add(itemData);
            }
            data.put("items", items);

            report.add(data);
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/" + fileName), report);
    }
}
