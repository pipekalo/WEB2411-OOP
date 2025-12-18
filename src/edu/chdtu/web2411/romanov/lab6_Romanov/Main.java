package edu.chdtu.web2411.romanov.lab6_Romanov;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = Storage.getInstance();
        Basket basket = new Basket();
        ShopService shop = new ShopService(storage, basket);

        System.out.println("=== ІНТЕРНЕТ-МАГАЗИН ===");

        while (true) {
            System.out.println("""
                    
                    Меню:
                    1. Каталог (сторінки)
                    2. Додати в кошик (ID)
                    3. Мій кошик
                    4. Купити (Ланцюжок перевірок)
                    5. Новий товар (Фабрика)
                    6. Створити набір (бокс)
                    7. Видалити товар
                    8. Редагувати товар
                    9. Зберегти і Вийти
                    """);
            System.out.print("-> ");

            try {
                switch (scanner.nextLine()) {
                    case "1" -> shop.showCatalog(scanner);
                    case "2" -> shop.addToBasket(scanner);
                    case "3" -> shop.showBasket();
                    case "4" -> shop.buy();
                    case "5" -> shop.createProduct(scanner);
                    case "6" -> shop.createBox(scanner);
                    case "7" -> shop.deleteProduct(scanner);
                    case "8" -> shop.editProduct(scanner);
                    case "9" -> { shop.saveAndExit(); return; }
                    default -> System.out.println("Невірний пункт меню");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}