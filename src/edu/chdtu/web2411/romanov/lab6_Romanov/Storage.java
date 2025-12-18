package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage instance;
    private List<Product> products = new ArrayList<>();
    private String fileName = "storage_lab6.ser";
    private ProductFactory factory = new ProductFactory();

    private Storage() { loadFromFile(); }

    public static synchronized Storage getInstance() {
        if (instance == null) instance = new Storage();
        return instance;
    }

    public void addProduct(int type, int subType, String name, double price, String extraParam) {
        int id = getNextId();
        Product newProduct = factory.createProduct(type, subType, id, name, price, extraParam);
        products.add(newProduct);
        System.out.println("Товар створено: " + newProduct);
    }

    public void addManualProduct(Product p) {
        products.add(p);
        System.out.println("Набір збережено! ID: " + p.getId());
    }

    public int getNextId() {
        int max = 0;
        for (Product p : products) {
            if (p.getId() > max) max = p.getId();
        }
        return max + 1;
    }

    public Product findProduct(int id) {
        for (Product p : products) if (p.getId() == id) return p;
        return null;
    }

    public void deleteProduct(int id) {
        Product p = findProduct(id);
        if (p != null) { products.remove(p); System.out.println("Видалено."); }
        else System.out.println("Не знайдено.");
    }

    public void showPage(int pageNumber) {
        int pageSize = 3;
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, products.size());

        if (start >= products.size() || start < 0) {
            System.out.println("Сторінка порожня."); return;
        }
        System.out.println("\n--- СКЛАД (Стор. " + pageNumber + ") ---");
        for (int i = start; i < end; i++) System.out.println(products.get(i));
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(products);
        } catch (Exception e) {}
    }

    public void loadFromFile() {
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                products = (List<Product>) ois.readObject();
            } catch (Exception e) {}
        }
    }
}