package heroes;

public class Paladin extends Hero {

    //Constructors
    public Paladin(String name, double MP, double strength, double agility, double dexterity, double gold, int xp) {
        super(name, MP, strength, agility, dexterity, gold, xp);
    }

    //Hero of type paladin levels up.
    @Override
    public void levelUp(){
        this.setLevel(this.getLevel()+1);   // increase hero level
        this.setHP(this.getLevel() * 100);  // re-calculate hero HP
        this.setMP(this.getMP() * 1.1); // re-calculate hero MP

        //When a hero levels up all of their skills increase by 5% and their favored skills increase
        //by an extra 5%.
        this.setStrength(this.getStrength()* (1.05));
        this.setDexterity(this.getDexterity()* (1.05));
        this.setAgility(this.getAgility()* (1.05));

        //Favorite skills
        this.setStrength(this.getStrength()* (1.05));
        this.setDexterity(this.getDexterity()* (1.05));
    }
}
