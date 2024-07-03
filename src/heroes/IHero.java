package heroes;

import infos.ConsoleColors;
import item.IArmor;
import item.IWeapon;
import item.IItem;
import monster.IMonster;

import java.util.ArrayList;
import java.util.List;

public interface IHero {

    //Methodes

    //Abstract method, because every type of a hero has favorit skills.
    public abstract void levelUp();

    //Calculate the hero damage "Hero’s attack damage (with weapon) = (𝑠𝑡𝑟𝑒𝑛𝑔𝑡ℎ + 𝑤𝑒𝑎𝑝𝑜𝑛_𝑑𝑎𝑚𝑎𝑔𝑒) × 0.05"
    public double attackDamage();

    //Check if a hero is dead based on his health points.
    public boolean isDead();

    //Hero will receive damage from a Hero.
    public void receiveDamage(IMonster m);

    //Calculate a hero defence.
    public double calculateHeroDefence();

    //Calculate the dodge chance for hero
    public double calculateAgility();

    //Search for an item in hero inventory
    public IItem findItemByName(String nameItem);

    //Needed point for hero to level up.
    public int pointsToLevelUp();

    //Adding HP and MP to hero after a round, if not dead
    // "At the end of each round of a battle the heroes regain 10% of their HP and 10% of their
    //  MP. That is, 𝐻𝑃 = 𝐻𝑃 × 1.1 and 𝑀𝑃 = 𝑀𝑃 × 1.1"
    public void endRound();

    //Count the number of non dead monsters.
    public static int countNonDeadHeroes(List<IHero> listOfHeroes){
        int i = 0;
        for (IHero h : listOfHeroes){
            if(!h.isDead()){
                i++;
            }
        }
        return i;
    }

    //When battle is ended
    public void endBattle(int nbMonsters, int levelMonster);

    //Add item to hero inventory.
    public void addItem(IItem item);

    //Remove an item from hero inventory.
    public void removeItem(IItem item);

    //Check if hero can equip a Weapon
    public abstract boolean canEquipWeapon();

    //Check if hero can equip anArmor
    public abstract boolean canEquipArmor();

    //Getters and Setters
    public String getName();
    public void setName(String name);
    public int getLevel();
    public void setLevel(int level);
    public int getXp();
    public void setXp(int xp);
    public double getHP();
    public void setHP(double HP);
    public double getMP();
    public void setMP(double MP);
    public double getStrength();
    public void setStrength(double strength);
    public double getDexterity();
    public void setDexterity(double dexterity);
    public double getAgility();
    public void setAgility(double agility);
    public double getGold();
    public void setGold(double gold);
    public IItem getWeapon();
    public void setWeapon(IItem weapon);
    public IItem getArmor();
    public void setArmor(IItem armor);
    public List<IItem> getItemsList();
    public void setItemsList(List<IItem> itemsList);


}
