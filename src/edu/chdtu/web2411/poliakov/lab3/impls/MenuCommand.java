package edu.chdtu.web2411.poliakov.lab3.impls;

import edu.chdtu.web2411.poliakov.lab3.exception.ServiceException;

import java.io.IOException;

public interface MenuCommand {
    void execute() throws IOException, ServiceException;
}
