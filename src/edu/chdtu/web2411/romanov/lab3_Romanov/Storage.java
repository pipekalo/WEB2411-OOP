package edu.chdtu.web2411.romanov.lab3_Romanov;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private List<Product> products = new ArrayList<>();
    private String fileName = "storage_data.ser";

    public Storage() {
        loadFromFile();
    }

    public void addProduct(String name, double price) {
        int id = products.size() + 1;
        products.add(new Product(id, name, price));
        System.out.println("Товар додано на склад. ID: " + id);
    }

    public Product findProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void deleteProduct(int id) {
        Product p = findProduct(id);
        if (p != null) {
            products.remove(p);
            System.out.println("Товар видалено зі складу.");
        } else {
            System.out.println("Товар не знайдено.");
        }
    }

    public void showPage(int pageNumber) {
        int pageSize = 3;
        int start = (pageNumber - 1) * pageSize;
        int end = start + pageSize;

        if (end > products.size()) end = products.size();

        if (start >= products.size() || start < 0) {
            System.out.println("Ця сторінка порожня.");
            return;
        }

        System.out.println("\n--- СКЛАД: СТОРІНКА " + pageNumber + " ---");
        for (int i = start; i < end; i++) {
            System.out.println(products.get(i));
        }
        System.out.println("-------------------------");
    }

    public void saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(products);
            oos.close();
            System.out.println("Дані збережено у файл!");
        } catch (Exception e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
                products = (List<Product>) ois.readObject();
                ois.close();
                System.out.println("Склад завантажено (" + products.size() + " товарів).");
            } catch (Exception e) {
                System.out.println("Не вдалося прочитати файл.");
            }
        }
    }
}
