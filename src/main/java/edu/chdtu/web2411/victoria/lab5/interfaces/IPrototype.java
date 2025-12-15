package edu.chdtu.web2411.victoria.lab5.interfaces;

public interface IPrototype<T> {
    /**
     * Creates and returns a copy of this object.
     * 
     * @return A deep copy of the object.
     */
    T clone();
}