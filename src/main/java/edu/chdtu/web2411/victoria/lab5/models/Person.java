package edu.chdtu.web2411.victoria.lab5.models;

import java.io.Serializable;

import edu.chdtu.web2411.victoria.lab5.interfaces.IPrototype;
import edu.chdtu.web2411.victoria.lab5.interfaces.IVisitable;
import edu.chdtu.web2411.victoria.lab5.interfaces.IVisitor;

public abstract class Person implements Serializable, IPrototype<Person>, IVisitable {

    protected int id;
    protected String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Абстрактний метод клонування
    @Override
    public abstract Person clone();

    public abstract void accept(IVisitor visitor);

    public int getId() {
        return id;
    }

    // Сеттер потрібен, щоб змінити ID для клона
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", id, name);
    }
}