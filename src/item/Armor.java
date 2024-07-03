package item;

public class Armor extends Item implements IArmor{

    //Attributes
    double damage;

    //Constructors
    public Armor(String name, double price, int level, double damage) {
        super(name, price, level);
        this.damage = damage;
    }


    //Getters and setters.
    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Armor{" +
                " name='" + name + '\'' +
                ", damage=" + damage +
                ", price=" + price +
                ", level=" + level +
                '}';
    }
}
