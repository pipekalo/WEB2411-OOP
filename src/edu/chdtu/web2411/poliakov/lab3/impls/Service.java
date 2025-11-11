package edu.chdtu.web2411.poliakov.lab3.impls;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Service<T> {
    boolean add(T item) throws IOException;
    List<T> getAll();
    Optional<T> getById(int id);
    boolean remove(int id);
    boolean save();
    void load();
}
