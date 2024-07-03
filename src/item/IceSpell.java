package item;

import heroes.IHero;
import infos.ConsoleColors;
import monster.IMonster;

public class IceSpell extends Spell {

    //Constructors
    public IceSpell(String name, double price, int level, double damage, double manaCost) {
        super(name, price, level, damage, manaCost);
    }

    public boolean useSpellOnMonsterByHero(IHero hero, IMonster monster) {
        if(hero.getLevel() >= this.getLevel() && hero.getMP() >= this.getManaCost()){

            //Ice spell: reduces the damage of the target
            double monsteNewDamage = monster.getDamage() - this.getDamage();  //Calculate amount to be reduced from monster damage
            monster.setDamage(monsteNewDamage > 0 ? monsteNewDamage : 0);    //Set new value to monster ability, if new value is negative, assign 0.

            System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + hero.getName() + " has used " + this.getName() + " on monster " + monster.getName() + ConsoleColors.ANSI_RESET);

            //Spell will be removed from hero's inventory
            hero.getItemsList().remove(this);

            //Hero mana will be reduced.
            hero.setMP(hero.getMP() - this.getManaCost());

            return true;
        }else if(hero.getLevel() < this.getLevel()){
            System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + hero.getName() + " doesn't have required level to use " + this + ConsoleColors.ANSI_RESET);
        }else if(hero.getMP() < this.getManaCost()){
            System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + hero.getName() + " doesn't have enough Mana to use " + this + ConsoleColors.ANSI_RESET);
        }
        return false;
    }

    @Override
    public String toString() {
        return "IceSpell{" +
                "name='" + name +
                ", damage=" + damage +
                ", manaCost=" + manaCost +
                ", price=" + price +
                ", level=" + level +
                '}';
    }
}
