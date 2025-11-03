package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.Project;
import edu.chdtu.web2411.poliakov.lab3.TaskManager;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.ConsoleService;

import java.util.List;

public class ChoiceProjectCommand implements MenuCommand {
    private List<Project> projectList;
    private ConsoleService consoleService;

    public ChoiceProjectCommand(List<Project> projectList) {
        this.projectList = projectList;
        this.consoleService = new ConsoleService();
    }


    @Override
    public void execute() {
        int id = consoleService.readInt("Введіть ID проекту:");
        Project project = projectList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if(project == null) {
            consoleService.print("Проект не знайдено");
            return;
        }

        TaskManager taskManager = new TaskManager(project.getTaskList());
        taskManager.run();
        project.setTaskList(taskManager.getTaskList());
    }
}
