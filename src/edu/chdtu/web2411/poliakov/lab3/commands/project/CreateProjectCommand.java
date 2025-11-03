package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.Project;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;

import java.util.List;

public class CreateProjectCommand implements MenuCommand {
    private ConsoleService consoleService;
    private List<Project> projectList;

    public CreateProjectCommand(List<Project> projectList) {
        this.projectList = projectList;
        this.consoleService = new ConsoleService();
    }

    @Override
    public void execute() {
        String title = consoleService.readLine("Введіть заголовок для проекту: ");

        Project project = new Project(projectList.size() + 1, title);
        projectList.add(project);
        consoleService.print("Проект створено. ID: " + project.getId());
    }
}
