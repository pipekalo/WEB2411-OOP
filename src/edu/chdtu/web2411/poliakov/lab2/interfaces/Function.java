package edu.chdtu.web2411.poliakov.lab2.interfaces;

@FunctionalInterface
public interface Function<T, R> {
    R execute(T value);
}
