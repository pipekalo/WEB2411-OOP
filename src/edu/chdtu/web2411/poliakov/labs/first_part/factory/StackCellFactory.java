package edu.chdtu.web2411.poliakov.labs.first_part.factory;

import edu.chdtu.web2411.poliakov.labs.first_part.impl.CellFactory;
import edu.chdtu.web2411.poliakov.labs.first_part.model.Cell;
import edu.chdtu.web2411.poliakov.labs.first_part.model.StackCell;

public class StackCellFactory implements CellFactory {
    @Override
    public Cell create(String code) {
        return new StackCell(code);
    }
}
