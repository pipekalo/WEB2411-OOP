package edu.chdtu.web2411.romanov.lab3_Romanov;

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
            System.out.println("5. Новий товар");
            System.out.println("6. Видалити товар");
            System.out.println("7. Редагувати товар");
            System.out.println("8. Зберегти і Вийти");
            System.out.print("-> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введіть сторінку: ");
                    try {
                        int page = Integer.parseInt(scanner.nextLine());
                        storage.showPage(page);
                    } catch (Exception e) {
                        System.out.println("Введіть число!");
                    }
                    break;

                case "2":
                    System.out.print("ID товару: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        Product p = storage.findProduct(id);
                        if (p != null) {
                            basket.add(p);
                        } else {
                            System.out.println("Не знайдено.");
                        }
                    } catch (Exception e) {
                        System.out.println("Помилка вводу.");
                    }
                    break;

                case "3":
                    basket.showBasket();
                    break;

                case "4":
                    basket.buy();
                    break;

                case "5":
                    System.out.print("Назва: ");
                    String name = scanner.nextLine();
                    System.out.print("Ціна: ");
                    try {
                        double price = Double.parseDouble(scanner.nextLine());
                        storage.addProduct(name, price);
                    } catch (Exception e) {
                        System.out.println("Ціна має бути числом.");
                    }
                    break;

                case "6":
                    System.out.print("ID для видалення: ");
                    try {
                        int delId = Integer.parseInt(scanner.nextLine());
                        storage.deleteProduct(delId);
                    } catch (Exception e) {
                        System.out.println("Помилка.");
                    }
                    break;

                case "7":
                    System.out.print("ID для редагування: ");
                    try {
                        int editId = Integer.parseInt(scanner.nextLine());
                        Product prodToEdit = storage.findProduct(editId);

                        if (prodToEdit != null) {
                            System.out.print("Нова назва: ");
                            prodToEdit.setName(scanner.nextLine());

                            System.out.print("Нова ціна: ");
                            prodToEdit.setPrice(Double.parseDouble(scanner.nextLine()));
                            System.out.println("Оновлено!");
                        } else {
                            System.out.println("Немає такого товару.");
                        }
                    } catch (Exception e) {
                        System.out.println("Помилка.");
                    }
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