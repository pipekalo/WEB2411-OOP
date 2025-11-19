package edu.chdtu.web2411.romanov.Lab1_Romanov;

public class GroupLeader extends Student {
    public GroupLeader(String firstName, String lastName, String studentID) {
        super(firstName, lastName, studentID);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("-> (Це староста групи)");
    }
}
// додати автоматично 1 бал до усіх оцінок
