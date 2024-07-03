package heroes;

import infos.ConsoleColors;
import item.IArmor;
import item.IWeapon;
import item.IItem;
import monster.IMonster;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero implements IHero {

    //Attributes
    String name;
    int level;
    int xp;
    double HP;
    double MP;
    double strength;
    double dexterity;
    double agility;
    double gold;

    IItem weapon;

    IItem armor;
    List<IItem> itemsList = new ArrayList<>();

    //Constructors
    public Hero(String name, double MP, double strength, double agility, double dexterity, double gold, int xp) {
        this.name = name;
        this.MP = MP;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = gold;
        this.xp = xp;
        this.level = 1;
        this.HP = this.level*100;
    }

    //Methodes

    //Abstract method, because every type of a hero has favorit skills.
    public abstract void levelUp();

    //Calculate the hero damage "Hero’s attack damage (with weapon) = (𝑠𝑡𝑟𝑒𝑛𝑔𝑡ℎ + 𝑤𝑒𝑎𝑝𝑜𝑛_𝑑𝑎𝑚𝑎𝑔𝑒) × 0.05"
    public double attackDamage(){
        return (this.getStrength() + ((this.getWeapon() != null) ? ((IWeapon)this.getWeapon()).getDamage() : 0)) * 0.05;
    }

    //Check if a hero is dead based on his health points.
    public boolean isDead(){
        return this.getHP()<=0;
    }

    //Hero will receive damage from a Hero.
    public void receiveDamage(IMonster m){
        double result = (Math.random() * 100); // random number from 0 to 100;
        double dodgePercent = this.calculateAgility(); // We get the dodge change number.

        if(dodgePercent < result){ // if the dodge chance number is less than the random number, hero did not dodge the attack
            double damageTaken = (m.attackDamage() - this.calculateHeroDefence()); // we calculate the attack damage from the monster.
            this.setHP(this.getHP() - ((damageTaken>0) ? damageTaken : 0)); // If the damage is positive, it is taken from hero health.
            if(this.getHP() > 0){ // If hero still alive
                System.out.println(ConsoleColors.ANSI_RED + m.getName() + " attacked " + this.name + " for " + ((damageTaken>0) ? damageTaken : 0) + " damage!" + ConsoleColors.ANSI_RESET);

            }else{  // If hero is dead
                System.out.println(ConsoleColors.ANSI_RED + m.getName() + " killed " + this.name + " for " + ((damageTaken>0) ? damageTaken : 0) + " damage!" + ConsoleColors.ANSI_RESET);

            }
        }else{ // hero has dodged the attack
            System.out.println(ConsoleColors.ANSI_RED + this.name + " dodged " + m.getName() + "'s  attack!" + ConsoleColors.ANSI_RESET);
        }
    }

    //Calculate a hero defence.
    public double calculateHeroDefence(){
        return ((this.getArmor() != null) ? ((IArmor)this.getArmor()).getDamage() : 0);
    }

    //Calculate the dodge chance for hero
    public double calculateAgility(){
        return this.getAgility()*0.002;
    }

    //Search for an item in hero inventory
    public IItem findItemByName(String nameItem){
        for (IItem item : this.getItemsList()){
            if(item.getName().toLowerCase().equals(nameItem.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    //Needed point for hero to level up.
    public int pointsToLevelUp() {
        return this.getLevel()*10;
    }

    //Adding HP and MP to hero after a round, if not dead
    // "At the end of each round of a battle the heroes regain 10% of their HP and 10% of their
    //  MP. That is, 𝐻𝑃 = 𝐻𝑃 × 1.1 and 𝑀𝑃 = 𝑀𝑃 × 1.1"
    public void endRound(){
        if(this.getHP() > 0){
            this.setHP(this.getHP() * 1.1);
            this.setMP(this.getMP() * 1.1);
        }
    }

    //When battle is ended
    public void endBattle(int nbMonsters, int levelMonster){
        if(this.getHP() > 0){   // if hero not dead, gain some gold, based on highest monster level.
            this.setGold(this.getGold() + 100*levelMonster);
        }
        this.setXp(this.getXp() + nbMonsters*2);    // hero get XP

        //If hero has needed xp to level up
        while(this.getXp() >= this.pointsToLevelUp()){  // while hero has enough xp to level up, hero will increase his level.
            this.setXp(this.getXp() - this.pointsToLevelUp());    // ce decrease his xp by the amount needed to level up
            this.levelUp(); //Hero levels up.
        }
    }

    //Add item to hero inventory.
    public void addItem(IItem item){
       this.getItemsList().add(item);
    }

    //Remove an item from hero inventory.
    public void removeItem(IItem item){
        this.getItemsList().remove(item);
    }

    //Check if hero can equip a Weapon
    public boolean canEquipWeapon(){
        return this.getWeapon() == null;
    }

    //Check if hero can equip anArmor
    public boolean canEquipArmor(){
        return this.getArmor() == null;
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
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public double getHP() {
        return HP;
    }
    public void setHP(double HP) {
        this.HP = HP;
    }
    public double getMP() {
        return MP;
    }
    public void setMP(double MP) {
        this.MP = MP;
    }
    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
    public double getDexterity() {
        return dexterity;
    }
    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }
    public double getAgility() {
        return agility;
    }
    public void setAgility(double agility) {
        this.agility = agility;
    }
    public double getGold() {
        return gold;
    }
    public void setGold(double gold) {
        this.gold = gold;
    }
    public IItem getWeapon() {
        return weapon;
    }
    public void setWeapon(IItem weapon) {
        if(weapon == null && this.getWeapon() != null){ // will remove his weapon.
            System.out.println(this.getName() + " has unequiped weapon:" + this.getWeapon().getName());
            this.weapon = weapon;

        }else if(weapon == null && this.getWeapon() == null){ // want to remove weapon but has no weapon already
            System.out.println(this.getName() + " has no weapon to unequip.");

        }else if(weapon.getLevel() > this.getLevel()){ // want to equip weapon with higher level
            System.out.println("Weapon can't be equipped, " + weapon.getName() + "'s level is greater than " + this.getName() + "'s level.");

        }else{  // hero equip weapon
            System.out.println(this.getName() + " has equiped weapon:" + weapon.getName());
            this.weapon = weapon;
        }
    }
    public IItem getArmor() {
        return armor;
    }
    public void setArmor(IItem armor) {
        if(armor == null && this.getArmor() != null){ // will remove his armor.
            System.out.println(this.getName() + " has unequiped armor:" + this.getArmor().getName());
            this.armor = armor;

        }else if(armor == null && this.getArmor() == null){ // want to remove armor but has no armor already
            System.out.println(this.getName() + " has no armor to unequip.");

        }else if(armor.getLevel() > this.getLevel()){ // want to equip armor with higher level
            System.out.println("Armor can't be equipped, " + armor.getName() + "'s level is greater than " + this.getName() + "'s level.");

        }else{  // hero equip armor
            System.out.println(this.getName() + " has equiped armor:" + armor.getName());
            this.armor = armor;
        }
    }
    public List<IItem> getItemsList() {
        return itemsList;
    }
    public void setItemsList(List<IItem> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", xp=" + xp +
                ", HP=" + HP +
                ", MP=" + MP +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", agility=" + agility +
                ", gold=" + gold +
                ", weapon=" + weapon +
                ", armor=" + armor +
                '}';
    }
}