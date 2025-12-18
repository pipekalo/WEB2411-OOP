package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.util.Scanner;

public class ShopService {
    private final Storage storage;
    private final Basket basket;

    public ShopService(Storage storage, Basket basket) {
        this.storage = storage;
        this.basket = basket;
    }

    public void showCatalog(Scanner sc) {
        System.out.print("Сторінка: ");
        try {
            storage.showPage(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            storage.showPage(1);
        }
    }

    public void addToBasket(Scanner sc) {
        System.out.print("ID: ");
        Product p = storage.findProduct(Integer.parseInt(sc.nextLine()));
        if (p != null) basket.add(p);
        else System.out.println("Не знайдено.");
    }

    public void showBasket() {
        basket.showBasket();
    }

    public void buy() {
        basket.buy();
    }

    public void createProduct(Scanner sc) {
        System.out.println("1. Електроніка\n2. Література");
        int type = Integer.parseInt(sc.nextLine());

        int subType = 0;
        if (type == 1) {
            System.out.println("Тип електроніки:");
            System.out.println("1. Мобільна");
            System.out.println("2. Для дому");
            System.out.println("3. Комп'ютерна");
            System.out.println("4. Аудіо");
            subType = Integer.parseInt(sc.nextLine());
        }

        System.out.print("Назва: ");
        String name = sc.nextLine();

        System.out.print("Ціна: ");
        double price = Double.parseDouble(sc.nextLine().replace(",", "."));

        if (type == 1) {
            System.out.print("Гарантія: ");
            String warranty = sc.nextLine();
            storage.addProduct(type, subType, name, price, warranty);
        } else if (type == 2) {
            System.out.print("Автор: ");
            String author = sc.nextLine();
            storage.addProduct(type, 0, name, price, author);
        } else {
            System.out.println("Невірний тип");
        }
    }

    public void createBox(Scanner sc) {
        System.out.print("Назва набору: ");
        String boxName = sc.nextLine();
        Box box = new Box(storage.getNextId(), boxName);

        System.out.println("Введіть ID товарів для набору (0 - стоп):");
        while (true) {
            System.out.print("ID товару: ");
            int pid = Integer.parseInt(sc.nextLine());
            if (pid == 0) break;

            Product item = storage.findProduct(pid);
            if (item != null) {
                box.add(item);
                System.out.println("-> Поклали: " + item.getName());
            } else {
                System.out.println("Немає такого товару.");
            }
        }
        storage.addManualProduct(box);
    }

    public void deleteProduct(Scanner sc) {
        System.out.print("ID: ");
        storage.deleteProduct(Integer.parseInt(sc.nextLine()));
    }

    public void editProduct(Scanner sc) {
        System.out.print("ID для зміни: ");
        Product pEdit = storage.findProduct(Integer.parseInt(sc.nextLine()));
        if (pEdit == null) {
            System.out.println("Не знайдено.");
            return;
        }

        System.out.print("Нова назва: ");
        String n = sc.nextLine();
        if (!n.isEmpty()) pEdit.setName(n);

        System.out.print("Нова ціна: ");
        String pr = sc.nextLine();
        if (!pr.isEmpty()) pEdit.setPrice(Double.parseDouble(pr.replace(",", ".")));

        System.out.println("Оновлено.");
    }

    public void saveAndExit() {
        storage.saveToFile();
    }
}
