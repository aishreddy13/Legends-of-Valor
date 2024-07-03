package item;

import heroes.Hero;
import monster.IMonster;

public interface IItem {

    //Getters and Setters
    public String getName();
    public void setName(String name);
    public double getPrice();
    public void setPrice(double price);
    public int getLevel();
    public void setLevel(int level);
}
