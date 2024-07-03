package monster;

import heroes.IHero;
import infos.ConsoleColors;

import java.util.List;

public abstract class Monster implements IMonster {

    //Attributes
    String name;
    int level;
    double HP;
    double damage;
    double defense;
    double ability;

    //Constructors
    public Monster(String name, int level, double damage, double defense, double ability) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.ability = ability;
        this.HP = this.level*100;
    }
    public Monster() {
    }

    //Methodes

    //Check if monster is dead based on his HP.
    public boolean isDead(){
        return this.getHP()<=0;
    }

    //Attack damage the monster can do.
    public double attackDamage(){
        return this.getDamage()*0.15;
    }

    //Monster will receive damage from a hero.
    public void receiveDamage(IHero h){
        double result = (Math.random() * 100); // random number from 0 to 100;
        double dodgePercent = this.calculateAgility();  // We get the dodge change number.
        if(dodgePercent < result){  // if the dodge chance number is less than the random number, monster did not dodge the attack
            double damageTaken = (h.attackDamage() - this.calculateMonsterDefence());    // We caclculate the attack from the hero
            this.setHP(this.getHP() - ((damageTaken > 0) ? damageTaken : 0));   // If the damage is positive, it is taken from monster health.
            if(this.getHP() > 0){   // If monster still alive
                System.out.println(ConsoleColors.ANSI_GREEN + h.getName() + " attacked " + this.name + " for " + ((damageTaken>0) ? damageTaken : 0) + " damage!" + ConsoleColors.ANSI_RESET);
            }else{  // If monster is dead
                System.out.println(ConsoleColors.ANSI_GREEN + h.getName() + " killed " + this.name + " for " + ((damageTaken>0) ? damageTaken : 0) + " damage!" + ConsoleColors.ANSI_RESET);
            }
        }else { //If monster has dodged the attack.
            System.out.println(ConsoleColors.ANSI_GREEN + h.getName() + this.name + " dodged " + h.getName() + "'s  attack!" + ConsoleColors.ANSI_RESET);
        }
    }

    //Agility of a monster "Monster’s dodge chance = 𝑑𝑜𝑑𝑔𝑒_𝑐ℎ𝑎𝑛𝑐𝑒 × .01"
    public double calculateAgility(){
        return this.getAbility()*0.01;
    }

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
    public double calculateMonsterDefence(){
        return this.getDefense()*0.08;
    }

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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public double getHP() {
        return HP;
    }
    public void setHP(double HP) {
        this.HP = HP;
    }
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDefense() {
        return defense;
    }
    public void setDefense(double defense) {
        this.defense = defense;
    }
    public double getAbility() {
        return ability;
    }
    public void setAbility(double ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", HP=" + HP +
                ", damage=" + damage +
                ", defense=" + defense +
                ", ability=" + ability +
                '}';
    }
}