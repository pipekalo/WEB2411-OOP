package edu.chdtu.web2411.victoria.lab2.models;

import edu.chdtu.web2411.victoria.lab2.interfaces.IDisplayable;

public abstract class AbstractPerson implements IDisplayable {
    protected int id;
    protected String name;

    public AbstractPerson(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}