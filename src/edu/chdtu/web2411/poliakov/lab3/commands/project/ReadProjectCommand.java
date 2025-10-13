package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.model.Project;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.service.ProjectService;

import java.util.List;


public class ReadProjectCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private ProjectService projectService;

    public ReadProjectCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.projectService = ProjectService.getInstance();
    }

    @Override
    public void execute() {
        while (true) {
            List<Project> projectList = this.projectService.getAll();
            consoleWriter.print("\n╔════════════════════════════════════════════════════════╗");
            consoleWriter.print("║                    ВСІ ПРОЕКТИ                           ║");
            consoleWriter.print("╚════════════════════════════════════════════════════════╝\n");


            for (int i = 0; i < projectList.size(); i++) {
                consoleWriter.print((i + 1) + ". " + projectList.get(i).getInfo());
            }

            consoleWriter.print("\nВсього проектів: " + projectList.size());

            consoleWriter.print("\n[Q] - Вихід");
            String input = consoleWriter.readLine("Введіть команду: ");
            if (input.equals("Q")) {
                break;
            } else {
                consoleWriter.print("Введено неправильну команду!");
            }
        }
    }


}
