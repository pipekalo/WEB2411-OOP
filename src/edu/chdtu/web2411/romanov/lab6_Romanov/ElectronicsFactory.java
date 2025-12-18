package edu.chdtu.web2411.romanov.lab6_Romanov;

public class ElectronicsFactory {
    public Electronics create(int subType, int id, String name, double price, String warranty) {
        return switch (subType) {
            case 1 -> new MobileElectronics(id, name, price, warranty);
            case 2 -> new HomeElectronics(id, name, price, warranty);
            case 3 -> new ComputerElectronics(id, name, price, warranty);
            case 4 -> new AudioElectronics(id, name, price, warranty);
            default -> throw new IllegalArgumentException("Невірний тип електроніки: " + subType);
        };
    }
}
