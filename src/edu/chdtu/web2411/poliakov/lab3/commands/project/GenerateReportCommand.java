package edu.chdtu.web2411.poliakov.lab3.commands.project;

import edu.chdtu.web2411.poliakov.lab3.ConsoleWriter;
import edu.chdtu.web2411.poliakov.lab3.model.Project;
import edu.chdtu.web2411.poliakov.lab3.model.Task;
import edu.chdtu.web2411.poliakov.lab3.enums.TaskType;
import edu.chdtu.web2411.poliakov.lab3.impls.MenuCommand;
import edu.chdtu.web2411.poliakov.lab3.service.ProjectService;

import java.io.*;
import java.time.Instant;
import java.util.List;


public class GenerateReportCommand implements MenuCommand {
    private ConsoleWriter consoleWriter;
    private ProjectService projectService;

    private static String REPORTS_DIR = "reports";

    public GenerateReportCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.projectService = ProjectService.getInstance();
    }


    @Override
    public void execute() {
        List<Project> projectList = this.projectService.getAll();
        if (!projectList.isEmpty()) {
            try {
                File reportsDir = new File(REPORTS_DIR);
                if (!reportsDir.exists()) {
                    reportsDir.mkdirs();
                }

                String fileName = "all_projects_report_" + Instant.now().toString().substring(0, 19).replace(":", "-") + ".txt";
                String filePath = REPORTS_DIR + "/" + fileName;

                ConsoleWriter writer = new ConsoleWriter(new FileOutputStream(filePath));

                this.generateReports(writer, projectList);
                writer.close();
                System.out.println("✅ Загальний звіт успішно створено: " + filePath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.consoleWriter.print("Список порожній");
        }
    }

    private void generateReports(ConsoleWriter writer, List<Project> projectList) {
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            List<Task> tasks = projectList.get(i).getTaskList();

            long todoCount = 0, inProgressCount = 0, doneCount = 0;

            if (!tasks.isEmpty()) {
                todoCount = tasks.stream().filter(t -> t.getTaskType() == TaskType.TODO).count();
                inProgressCount = tasks.stream().filter(t -> t.getTaskType() == TaskType.IN_PROGRESS).count();
                doneCount = tasks.stream().filter(t -> t.getTaskType() == TaskType.DONE).count();
            }

            writer.print("\n╔═══════════════════════════════════════════════════════════════════════╗");
            writer.print("║                          ІНФОРМАЦІЯ ПРО ПРОЕКТ                        ║");
            writer.print("╠═══════════════════════════════════════════════════════════════════════╣");
            writer.print("║ ID Проекту:        " + project.getId());
            writer.print("║ Назва:             " + project.getInfo());
            writer.print("║ Всього задач:      " + tasks.size());
            writer.print("║ ├─ 📋 TODO:        " + todoCount);
            writer.print("║ ├─ ⚙️  В РОБОТІ:   " + inProgressCount);
            writer.print("║ └─ ✅ ВИКОНАНО:    " + doneCount);
            writer.print("╚═══════════════════════════════════════════════════════════════════════╝");
            writer.print("╔═══════════════════════════════════════════════════════════════════════╗");
            writer.print("║                           ЗАДАЧІ ПРОЕКТУ                              ║");
            writer.print("╚═══════════════════════════════════════════════════════════════════════╝");

            if (tasks.isEmpty()) {
                writer.print("У цьому проекті немає задач\n\n");
            } else {
                writer.print("╔════╦════════════════════════╦════════════╦══════════╦════════════════════╗");
                writer.print("║ ID ║ Назва                  ║ Статус     ║ Пріорітет║ Дедлайн            ║");
                writer.print("╠════╬════════════════════════╬════════════╬══════════╬════════════════════╣");
                for (Task task : tasks) {
                    int priority = task.getPriority();
                    String statusIcon = switch (task.getTaskType()) {
                        case TaskType.TODO -> "📋";
                        case TaskType.IN_PROGRESS -> "⚙️";
                        case TaskType.DONE -> "✅";
                    };
                    String priorityBar = "█".repeat(priority) + "░".repeat(5 - priority);
                    String title = task.getInfo().substring(task.getInfo().indexOf("Title: ") + 7, task.getInfo().indexOf(", Description:"));

                    writer.print(String.format("║ %-2d ║ %-22s ║ %-10s ║ %-8s ║ %-18s ║",
                            task.getId(),
                            title.length() <= 22 ? title : title.substring(0, 19) + "...",
                            statusIcon + " " + task.getTaskType(),
                            priorityBar,
                            task.getInfo().substring(task.getInfo().lastIndexOf("Deadline: ") + 10)
                    ));
                }


                writer.print("╚════╩════════════════════════╩════════════╩══════════╩════════════════════╝\n\n");
            }
        }
    }
}
