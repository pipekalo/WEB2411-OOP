package edu.chdtu.web2411.romanov.lab4_Romanov;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        Basket basket = new Basket();

        System.out.println("=== ІНТЕРНЕТ-МАГАЗИН ===");

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Каталог (сторінки)");
            System.out.println("2. Додати в кошик (ID)");
            System.out.println("3. Мій кошик");
            System.out.println("4. Купити");
            System.out.println("5. Новий товар (Фабрика)");
            System.out.println("6. Видалити товар");
            System.out.println("7. Редагувати товар");
            System.out.println("8. Зберегти і Вийти");
            System.out.print("-> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Сторінка: ");
                    try {
                        int page = Integer.parseInt(scanner.nextLine());
                        storage.showPage(page);
                    } catch (Exception e) { storage.showPage(1); }
                    break;

                case "2":
                    System.out.print("ID товару: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        Product p = storage.findProduct(id);
                        if (p != null) basket.add(p);
                        else System.out.println("Не знайдено.");
                    } catch (Exception e) { System.out.println("Помилка ID."); }
                    break;

                case "3":
                    basket.showBasket();
                    break;

                case "4":
                    basket.buy();
                    break;

                case "5":
                    System.out.println("\nОберіть тип:");
                    System.out.println("1. Електроніка");
                    System.out.println("2. Література");
                    int type = 1;
                    try { type = Integer.parseInt(scanner.nextLine()); } catch (Exception e) {}

                    System.out.print("Назва: ");
                    String name = scanner.nextLine();

                    System.out.print("Ціна: ");
                    double price = 0;
                    try {
                        String pStr = scanner.nextLine().replace(",", ".");
                        price = Double.parseDouble(pStr);
                    } catch (Exception e) {
                        System.out.println("Помилка ціни."); break;
                    }

                    String extra = "";
                    if (type == 1) {
                        System.out.print("Введіть гарантію: ");
                        extra = scanner.nextLine();
                    } else {
                        System.out.print("Введіть автора: ");
                        extra = scanner.nextLine();
                    }

                    storage.addProduct(type, name, price, extra);
                    break;

                case "6":
                    System.out.print("ID для видалення: ");
                    try {
                        storage.deleteProduct(Integer.parseInt(scanner.nextLine()));
                    } catch (Exception e) { System.out.println("Помилка."); }
                    break;

                case "7":
                    System.out.print("ID для зміни: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        Product p = storage.findProduct(id);
                        if (p != null) {
                            System.out.println("Змінюємо: " + p.getName());
                            System.out.println("Нова назва (Enter - пропустити): ");
                            String n = scanner.nextLine();
                            if (!n.isEmpty()) p.setName(n);

                            System.out.print("Нова ціна (Enter - пропустити): ");
                            String pr = scanner.nextLine();
                            if (!pr.isEmpty()) p.setPrice(Double.parseDouble(pr.replace(",", ".")));

                            System.out.println("Оновлено.");
                        } else { System.out.println("Не знайдено."); }
                    } catch (Exception e) { System.out.println("Помилка."); }
                    break;

                case "8":
                    storage.saveToFile();
                    System.out.println("До побачення!");
                    return;

                default:
                    System.out.println("Невідома команда.");
            }
        }
    }
}