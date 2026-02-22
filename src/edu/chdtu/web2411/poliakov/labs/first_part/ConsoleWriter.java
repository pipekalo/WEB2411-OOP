package edu.chdtu.web2411.poliakov.labs.first_part;



import edu.chdtu.web2411.poliakov.labs.first_part.exeptions.ConsoleException;

import java.io.*;
import java.util.Scanner;

public class ConsoleWriter {
    private Scanner scanner;
    private BufferedWriter writer;
    private boolean isBuffered;

    public ConsoleWriter() {
        this.scanner = new Scanner(System.in);
        this.isBuffered = false;
    }

    public ConsoleWriter(OutputStream output) {
        this.scanner = null;
        this.writer = new BufferedWriter(new OutputStreamWriter(output));
        this.isBuffered = true;
    }

    public ConsoleWriter(InputStream input, OutputStream output) {
        this.scanner = new Scanner(input);
        this.writer = new BufferedWriter(new OutputStreamWriter(output));
        this.isBuffered = true;
    }

    public String readLine(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.trim().isEmpty()) {
            System.out.println("Рядок не повинен бути порожнім");
            input = scanner.nextLine();
        }
        return input.trim();
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
        if (isBuffered) {
            try {
                writer.write(msg);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                throw new ConsoleException("Помилка виводу", e);
            }
        } else {
            System.out.println(msg);
        }
    }

    public void close() {
        try {
            if (this.writer != null && isBuffered) writer.close();
            if (this.scanner != null) System.exit(0);
        } catch (IOException e) {
            throw new ConsoleException("Помилка закриття", e);
        }
    }
}