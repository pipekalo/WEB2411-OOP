package edu.chdtu.web2411.poliakov.labs.first_part.factory;

import edu.chdtu.web2411.poliakov.labs.first_part.impl.CellFactory;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Cell;
import edu.chdtu.web2411.poliakov.labs.first_part.model.QueueCell;

public class QueueCellFactory implements CellFactory {
    @Override
    public Cell create(String code) {
        return new QueueCell(code);
    }
}
