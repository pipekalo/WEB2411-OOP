package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.Project;
import edu.chdtu.web2411.poliakov.lab3.TaskManager;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.services.ProjectService;
import edu.chdtu.web2411.poliakov.lab3.services.TaskService;

import java.util.Optional;


public class ChoiceProjectCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private ProjectService projectService;
    private TaskService taskService;

    public ChoiceProjectCommand() {
        this.consoleWriter = new ConsoleWriter();
        this.projectService = ProjectService.getInstance();
        this.taskService = TaskService.getInstance();
    }


    @Override
    public void execute() {
        int id = consoleWriter.readInt("Введіть ID проекту:");

        Optional<Project> projectOptional = this.projectService.getById(id);

        if(projectOptional.isEmpty()) {
            consoleWriter.print("Проект не знайдено");
            return;
        }

        Project project = projectOptional.get();

        TaskManager taskManager = new TaskManager();
        taskManager.run();
        project.setTaskList(this.taskService.getAll());
    }
}
