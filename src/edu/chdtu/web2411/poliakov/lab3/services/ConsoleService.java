package edu.chdtu.web2411.poliakov.lab3.services;

import java.util.Scanner;

public class ConsoleService {
    private Scanner scanner;

    public ConsoleService() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Неправильне введення. " + prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public void print(String msg) {
        System.out.println(msg);
    }

}
