package item;

import heroes.IHero;
import monster.IMonster;

public abstract class Spell extends Item implements ISpell {

    //Attributes
    double damage;
    double manaCost;

    //Constructors
    public Spell(String name, double price, int level, double damage, double manaCost) {
        super(name, price, level);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public abstract boolean useSpellOnMonsterByHero(IHero hero, IMonster monster);

    //Getters and Setters
    public double getDamage() {
        return damage;
    }
    public double getManaCost() {
        return manaCost;
    }
}