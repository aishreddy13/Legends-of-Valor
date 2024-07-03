package item;

import heroes.IHero;
import infos.ConsoleColors;
import monster.IMonster;

public class FireSpell extends Spell {

    //Constructors
    public FireSpell(String name, double price, int level, double damage, double manaCost) {
        super(name, price, level, damage, manaCost);
    }

    @Override
    public boolean useSpellOnMonsterByHero(IHero hero, IMonster monster) {
        if(hero.getLevel() >= this.getLevel() && hero.getMP() >= this.getManaCost()){

            //Fire spell: reduces the defense of the target
            double monsteNewDefence = monster.getDefense() - this.getDamage();  //Calculete amount to be reduced from monster defence
            monster.setDefense(monsteNewDefence > 0 ? monsteNewDefence : 0);    //Set new value to monster ability, if new value is negative, assign 0.

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
        return "FireSpell{" +
                "name='" + name +
                ", damage=" + damage +
                ", manaCost=" + manaCost +
                ", price=" + price +
                ", level=" + level +
                '}';
    }
}
