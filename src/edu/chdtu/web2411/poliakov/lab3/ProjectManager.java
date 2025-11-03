package edu.chdtu.web2411.poliakov.lab3;

import edu.chdtu.web2411.poliakov.lab3.commands.project.ChoiceProjectCommand;
import edu.chdtu.web2411.poliakov.lab3.commands.project.CreateProjectCommand;
import edu.chdtu.web2411.poliakov.lab3.commands.project.ReadProjectCommand;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectManager {
    private List<Project> projectList;
    private Map<String, MenuCommand> operations;
    private ConsoleService consoleService;

    public ProjectManager() {
        this.projectList = new ArrayList<>();
        this.operations = new HashMap<>();
        this.consoleService = new ConsoleService();

        initializeOperations();
    }

    private void initializeOperations() {
        operations.put("1", new CreateProjectCommand(projectList));
        operations.put("2", new ReadProjectCommand(projectList));
        operations.put("3", new ChoiceProjectCommand(projectList));
    }

    private void showMenu() {
        consoleService.print("\n╔════════════════════════════════════╗");
        consoleService.print("║   PROJECT MANAGER - ГОЛОВНЕ МЕНЮ   ║");
        consoleService.print("╠════════════════════════════════════╣");
        consoleService.print("║ 1. Додати проект                   ║");
        consoleService.print("║ 2. Переглянути всі проекти         ║");
        consoleService.print("║ 3. Обрати проект                   ║");
        consoleService.print("║ 0. Вихід                           ║");
        consoleService.print("╚════════════════════════════════════╝");
    }

    public void run() {
        while (true) {
            showMenu();
            String choice = consoleService.readLine("Виберіть дію: ");

            MenuCommand operation = operations.get(choice);
            if(operation != null) {
                operation.execute();
            } else if (choice.equals("0")) {
                break;
            } else {
                consoleService.print("Невірний вибір. Спробуйте знову.");
            }
        }
    }
}
