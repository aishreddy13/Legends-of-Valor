package item;

import heroes.IHero;
import infos.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

public class Potion extends Item implements IPotion{

    //Attributes
    double effectAmount;
    List<String> attributeAffected = new ArrayList<>();

    //Constructors
    public Potion(String name, double price, int level, double effectAmount, List<String> attributeAffected) {
        super(name, price, level);
        this.effectAmount = effectAmount;
        this.attributeAffected = attributeAffected;
    }

    public boolean usePotionByHero(IHero hero){
        if(hero.getLevel() >= this.getLevel()){
            if(attributeAffected.contains("Health")){
                hero.setHP(hero.getHP() + this.effectAmount);
            }
            if(attributeAffected.contains("Mana")){
                hero.setMP(hero.getMP() + this.effectAmount);
            }
            if(attributeAffected.contains("Strength")){
                hero.setStrength(hero.getStrength() + this.effectAmount);
            }
            if(attributeAffected.contains("Dexterity")){
                hero.setDexterity(hero.getDexterity() + this.effectAmount);
            }
            if(attributeAffected.contains("Defense")){

            }
            if(attributeAffected.contains("Agility")){
                hero.setAgility(hero.getAgility() + this.effectAmount);
            }
            System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + hero.getName() + " has used " + this + ConsoleColors.ANSI_RESET);

            //Potion will be removed from hero's inventory
            hero.getItemsList().remove(this);
            return true;
        }else{
            System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + hero.getName() + " doesn't have required level to use " + this + ConsoleColors.ANSI_RESET);
            return false;
        }
    }

    //Getters and Setters
    public double getEffectAmount() {
        return effectAmount;
    }
    public void setEffectAmount(double effectAmount) {
        this.effectAmount = effectAmount;
    }
    public List<String> getAttributeAffected() {
        return attributeAffected;
    }
    public void setAttributeAffected(List<String> attributeAffected) {
        this.attributeAffected = attributeAffected;
    }

    @Override
    public String toString() {
        return "Potion{" +
                " name='" + name + '\'' +
                ", effectAmount=" + effectAmount +
                ", attributeAffected=" + attributeAffected +
                ", price=" + price +
                ", level=" + level +
                '}';
    }
}