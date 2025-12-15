package edu.chdtu.web2411.victoria.lab3.models;

import java.io.Serializable;

public abstract class Person implements Serializable {
    protected int id;
    protected String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String toString() { return String.format("[%d] %s", id, name); }
}
