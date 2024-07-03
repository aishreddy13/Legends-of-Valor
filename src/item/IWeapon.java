package item;

public interface IWeapon extends IItem {

    public double getDamage();
    public void setDamage(double damage);
    public int getNbHands();
    public void setNbHands(int nbHands);

}
