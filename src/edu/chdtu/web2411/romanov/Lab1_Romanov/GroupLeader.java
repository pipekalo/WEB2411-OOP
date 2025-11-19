package edu.chdtu.web2411.romanov.Lab1_Romanov;

public class GroupLeader extends Student {
    public GroupLeader(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("-> (Це староста групи)");
    }

public void addBonus() {

    for (Subject work : getWorks()) {
        if (work.getGrade() > 0) {
            int grade = work.getGrade();
            work.setGrade(grade + 1);
            System.out.println("Бонус старості: Оцінка з " + work.subjectName + " піднята до " + (grade + 1));
        }
    }
}

}