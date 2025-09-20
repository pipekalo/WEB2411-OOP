package edu.chdtu.web2411.poliakov.lab1.interfaces;

@FunctionalInterface
public interface Predicate<T> {
    boolean execute(T value);
}
