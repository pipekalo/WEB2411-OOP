package edu.chdtu.web2411.poliakov.lab2.interfaces;

@FunctionalInterface
public interface Predicate<T> {
    boolean execute(T value);
}
