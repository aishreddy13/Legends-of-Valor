package monster;

import heroes.IHero;

import java.util.List;

public interface IMonster {

    //Methodes

    //Check if monster is dead based on his HP.
    public boolean isDead();

    //Attack damage the monster can do.
    public double attackDamage();

    //Monster will receive damage from a hero.
    public void receiveDamage(IHero h);

    //Agility of a monster "Monster’s dodge chance = 𝑑𝑜𝑑𝑔𝑒_𝑐ℎ𝑎𝑛𝑐𝑒 × .01"
    public double calculateAgility();

    //We count the maximum level of all monsters.
    public static int maxLevelMonster(List<IMonster> listMonsters){
        int i = 0;
        for (IMonster m : listMonsters){
            if(m.getLevel() > i){
                i = m.getLevel();
            }
        }
        return i;
    }

    //Calculate a monster defence.
    public double calculateMonsterDefence();

    //We will count the number of non dead monsters.
    public static int countNonDeadMonsters(List<IMonster> listMonsters){
        int i = 0;
        for (IMonster m : listMonsters){
            if(!m.isDead()){
                i++;
            }
        }
        return i;
    }

    //Getters and Setters
    public String getName();
    public void setName(String name);
    public int getLevel();
    public void setLevel(int level);
    public double getHP();
    public void setHP(double HP);
    public double getDamage();
    public void setDamage(double damage);
    public double getDefense();
    public void setDefense(double defense);
    public double getAbility();
    public void setAbility(double ability);

}
