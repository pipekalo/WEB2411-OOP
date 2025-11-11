package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.Project;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.services.ProjectService;

import java.util.ArrayList;


public class CreateProjectCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private ProjectService projectService;

    public CreateProjectCommand() {
        this.consoleWriter = new ConsoleWriter();
        this.projectService = ProjectService.getInstance();
    }

    @Override
    public void execute() {
        String title = consoleWriter.readLine("Введіть заголовок для проекту: ");

        Project project = new Project(projectService.getAll().size() + 1, title, new ArrayList<>());

        if(projectService.add(project)) {
            consoleWriter.print("Проект створено. ID: " + project.getId());
        }
    }
}
