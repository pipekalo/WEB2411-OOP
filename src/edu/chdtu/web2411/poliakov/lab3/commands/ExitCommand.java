package edu.chdtu.web2411.poliakov.lab3.commands;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.services.ProjectService;
import edu.chdtu.web2411.poliakov.lab3.services.TaskService;

public class ExitCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private ProjectService projectService;
    private TaskService taskService;

    public ExitCommand() {
        this.consoleWriter = new ConsoleWriter();
        this.projectService = ProjectService.getInstance();
        this.taskService = TaskService.getInstance();
    }

    @Override
    public void execute() {
        if(this.taskService.save() && this.projectService.save()) {
            this.consoleWriter.print("Всі файли збережені");
        } else {
            this.consoleWriter.print("Помилка при збереженні файлів");
        }
        this.consoleWriter.close();
    }
}
