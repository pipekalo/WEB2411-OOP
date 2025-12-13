package edu.chdtu.web2411.romanov.lab5_Romanov;

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
            System.out.println("4. Купити (Ланцюжок перевірок)");
            System.out.println("5. Новий товар (Фабрика)");
            System.out.println("6. Видалити товар");
            System.out.println("7. Редагувати товар");
            System.out.println("8. Зберегти і Вийти");
            System.out.print("-> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Сторінка: ");
                    try { storage.showPage(Integer.parseInt(scanner.nextLine())); }
                    catch (Exception e) { storage.showPage(1); }
                    break;

                case "2":
                    System.out.print("ID: ");
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
                    System.out.println("1. Електроніка\n2. Література");
                    int type = 1;
                    try { type = Integer.parseInt(scanner.nextLine()); } catch (Exception e) {}

                    System.out.print("Назва: ");
                    String name = scanner.nextLine();

                    System.out.print("Ціна: ");
                    double price = 0;
                    try {
                        price = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    } catch (Exception e) {
                        System.out.println("Ціна має бути числом!"); break;
                    }

                    String extra = "";
                    if (type == 1) { System.out.print("Гарантія: "); extra = scanner.nextLine(); }
                    else { System.out.print("Автор: "); extra = scanner.nextLine(); }

                    storage.addProduct(type, name, price, extra);
                    break;

                case "6":
                    System.out.print("ID: ");
                    try { storage.deleteProduct(Integer.parseInt(scanner.nextLine())); }
                    catch (Exception e) { System.out.println("Помилка."); }
                    break;

                case "7":
                    System.out.print("ID для зміни: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        Product p = storage.findProduct(id);
                        if (p != null) {
                            System.out.println("Редагуємо: " + p.getName());

                            System.out.print("Нова назва (Enter - пропустити): ");
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