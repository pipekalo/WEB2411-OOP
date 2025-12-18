package edu.chdtu.web2411.romanov.lab6_Romanov;

public class MobileElectronics extends Electronics {
    public MobileElectronics(int id, String name, double price, String warranty) {
        super(id, name, price, warranty);
    }
    @Override public String getCategory() { return "Мобільна"; }
}
