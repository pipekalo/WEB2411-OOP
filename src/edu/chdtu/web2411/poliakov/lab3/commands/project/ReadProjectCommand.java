package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.Project;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;

import java.util.List;

public class ReadProjectCommand implements MenuCommand {
    private List<Project> projectList;
    private ConsoleService consoleService;

    public ReadProjectCommand(List<Project> projectList) {
        this.projectList = projectList;
        this.consoleService = new ConsoleService();
    }

    @Override
    public void execute() {
        while (true) {
            consoleService.print("\n╔════════════════════════════════════════════════════════╗");
            consoleService.print("║                    ВСІ ПРОЕКТИ                           ║");
            consoleService.print("╚════════════════════════════════════════════════════════╝\n");


            for (int i = 0; i < projectList.size(); i++) {
                consoleService.print((i + 1) + ". " + projectList.get(i).getInfo());
            }

            consoleService.print("\nВсього проектів: " + projectList.size());

            consoleService.print("\n[Q] - Вихід");
            String input = consoleService.readLine("Введіть команду: ");
            if (input.equals("Q")) {
                break;
            } else {
                consoleService.print("Введено неправильну команду!");
            }
        }
    }


}
