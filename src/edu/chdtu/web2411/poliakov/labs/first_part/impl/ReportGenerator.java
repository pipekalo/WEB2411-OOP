package edu.chdtu.web2411.poliakov.labs.first_part.impl;

import edu.chdtu.web2411.poliakov.labs.first_part.model.Warehouse;

import java.io.IOException;

public interface ReportGenerator {
    void generate(Warehouse warehouse, String fileName) throws IOException;
}
