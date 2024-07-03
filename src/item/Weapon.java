package item;

public class Weapon extends Item implements IWeapon {

    //Attributes
    double damage;
    int nbHands;

    //Constructors
    public Weapon(String name, double price, int level, double damage, int nbHands) {
        super(name, price, level);
        this.damage = damage;
        this.nbHands = nbHands;
    }

    //Getters and Setters
    @Override
    public double getDamage() {
        return damage;
    }
    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }
    @Override
    public int getNbHands() {
        return nbHands;
    }
    @Override
    public void setNbHands(int nbHands) {
        this.nbHands = nbHands;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                " name='" + name + '\'' +
                ", damage=" + damage +
                ", nbHands=" + nbHands +
                ", price=" + price +
                ", level=" + level +
                '}';
    }
}