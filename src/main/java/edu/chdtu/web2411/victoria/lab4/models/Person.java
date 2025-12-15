package edu.chdtu.web2411.victoria.lab4.models;

import java.io.Serializable;

import edu.chdtu.web2411.victoria.lab4.interfaces.IPrototype;

public abstract class Person implements Serializable, IPrototype<Person> {

    protected int id;
    protected String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Абстрактний метод клонування
    @Override
    public abstract Person clone();

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