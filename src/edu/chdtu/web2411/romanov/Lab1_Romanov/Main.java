package edu.chdtu.web2411.romanov.Lab1_Romanov;

public class Main {
    public static void main(String[] args) {

        System.out.println("Створюємо студентів");

        Student Zadorozhny = new Student("Денис", "Задорожній");
        Student Melnik = new Student("Анна", "Мельник");

        GroupLeader Romanov = new GroupLeader("Максим", "Романов");

        Subject oopLab1 = new LaboratoryWork("Програмування", 1);
        Subject dbCourseWork = new CourseWork("Бази Даних", "Сайт");
        Subject mathLab1 = new LaboratoryWork("Мат. Аналіз", 1);
        Subject oopLab2 = new LaboratoryWork("Програмування", 2);

        oopLab1.submit();
        oopLab1.setGrade(90);

        dbCourseWork.submit();
        dbCourseWork.setGrade(75);

        mathLab1.submit();

        oopLab2.submit();
        oopLab2.setGrade(95);

        Zadorozhny.addWork(oopLab1);
        Melnik.addWork(dbCourseWork);
        Melnik.addWork(mathLab1);
        Romanov.addWork(oopLab2);

        StudentGroup Web2411 = new StudentGroup("Web-2411");

        Web2411.addStudent(Zadorozhny);
        Web2411.addStudent(Melnik);
        Web2411.addStudent(Romanov);

        Web2411.setGroupLeader(Romanov);

        Web2411.displayGroupInfo();

        System.out.println("Розрахунок середнього балу");
        double averageResult = Web2411.calculateAverageGrade();

        System.out.println("Середній бал групи = " + averageResult);

        Romanov.addBonus();
    }
}