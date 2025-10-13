package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.model.Project;
import edu.chdtu.web2411.poliakov.lab3.TaskManager;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.service.ProjectService;
import edu.chdtu.web2411.poliakov.lab3.service.TaskService;

import java.util.Optional;


public class ChoiceProjectCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private ProjectService projectService;
    private TaskService taskService;

    public ChoiceProjectCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
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

        TaskManager taskManager = new TaskManager(this.consoleWriter);
        taskManager.run();
        project.setTaskList(this.taskService.getAll());
    }
}
