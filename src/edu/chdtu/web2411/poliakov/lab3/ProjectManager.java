package edu.chdtu.web2411.poliakov.lab3;

import edu.chdtu.web2411.poliakov.lab3.commands.ExitCommand;
import edu.chdtu.web2411.poliakov.lab3.commands.project.ChoiceProjectCommand;
import edu.chdtu.web2411.poliakov.lab3.commands.project.CreateProjectCommand;
import edu.chdtu.web2411.poliakov.lab3.commands.project.GenerateReportCommand;
import edu.chdtu.web2411.poliakov.lab3.commands.project.ReadProjectCommand;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProjectManager {
    private Map<String, MenuCommand> operations;
    private ConsoleWriter consoleWriter;

    public ProjectManager() {
        this.operations = new HashMap<>();
        this.consoleWriter = new ConsoleWriter();

        initializeOperations();
    }

    private void initializeOperations() {
        operations.put("1", new CreateProjectCommand());
        operations.put("2", new ReadProjectCommand());
        operations.put("3", new ChoiceProjectCommand());
        operations.put("4", new GenerateReportCommand());
        operations.put("0", new ExitCommand());
    }

    private void showMenu() {
        this.consoleWriter.print("\n╔════════════════════════════════════╗");
        this.consoleWriter.print("║   PROJECT MANAGER - ГОЛОВНЕ МЕНЮ   ║");
        this.consoleWriter.print("╠════════════════════════════════════╣");
        this.consoleWriter.print("║ 1. Додати проект                   ║");
        this.consoleWriter.print("║ 2. Переглянути всі проекти         ║");
        this.consoleWriter.print("║ 3. Обрати проект                   ║");
        this.consoleWriter.print("║ 4. Формування звіту                ║");
        this.consoleWriter.print("║ 0. Вихід                           ║");
        this.consoleWriter.print("╚════════════════════════════════════╝");
    }

    public void run() {
        while (true) {
            try {
                showMenu();
                String choice = this.consoleWriter.readLine("Виберіть дію: ");

                MenuCommand operation = operations.get(choice);
                if(operation != null) {
                    operation.execute();
                } else {
                    this.consoleWriter.print("Невірний вибір. Спробуйте знову.");
                }
            } catch (IOException e) {
                this.consoleWriter.print("Помилка читання введення: " + e);
                break;
            }
        }
    }
}
