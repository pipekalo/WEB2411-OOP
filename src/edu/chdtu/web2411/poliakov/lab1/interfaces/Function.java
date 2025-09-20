package edu.chdtu.web2411.poliakov.lab1.interfaces;

@FunctionalInterface
public interface Function<T, R> {
    R execute(T value);
}
