package monster;

import java.util.Arrays;
import java.util.List;

public class MonsterFactory {

    public IMonster createRandomMonster(int levelMonster){
        List<String> monstersTypes = Arrays.asList("Dragon", "Exoskeleton", "Spirit");

        IMonster iMonster = null;

        int rand = (int)(Math.random() * monstersTypes.size());   //Generating a random number between 0 till (numbersOfChoices-1)

        if(monstersTypes.get(rand).equals("Dragon")){
            iMonster = generateDragonMonsterByLevel(levelMonster);
        } else if(monstersTypes.get(rand).equals("Exoskeleton")){
            iMonster = generateExoskeletonMonsterByLevel(levelMonster);
        }else if(monstersTypes.get(rand).equals("Spirit")){
            iMonster = generateSpiritMonsterByLevel(levelMonster);
        }

        return iMonster;
    }

    //Methode to generate random Dragon Monster based on a level wanted.
    public static IMonster generateDragonMonsterByLevel(int levelMonster) {
        IMonster genDragon = null;

        if (levelMonster == 1){
            genDragon  = new Dragon("Natsunomeryu", 1, 100, 200, 10);
        }else if(levelMonster == 2){
            genDragon  = new Dragon("Chrysophylax", 2, 200, 500, 20);
        }else if(levelMonster == 3){
            genDragon  = new Dragon("Desghidorrah", 3, 300, 400, 35);
        }else if(levelMonster == 4){
            genDragon  = new Dragon("BunsenBurner", 4, 400, 500, 45);
        }else if(levelMonster == 5){
            genDragon  = new Dragon("Kas-Ethelinh", 5, 600, 500, 60);
        }else if(levelMonster == 6){
            int rand = (int)(Math.random() * 2) + 1;   //Generating a random number between 1-2
            if(rand%2 == 0){
                genDragon = new Dragon("Igneel", 6, 600, 400, 60);
            }else {
                genDragon = new Dragon("Phaarthurnax", 6, 600, 700, 60);
            }
        }else if(levelMonster == 7){
            genDragon = new Dragon("Desghidorrah", 7, 700, 600, 75);
        }else if(levelMonster == 8){
            genDragon = new Dragon("TheWeatherbe", 8, 800, 900, 80);
        }else if(levelMonster == 9){
            int rand = (int)(Math.random() * 2) + 1;   //Generating a random number between 1-2
            if(rand%2 == 0){
                genDragon = new Dragon("D-Maleficent", 9, 900, 950, 85);
            }else {
                genDragon = new Dragon("BlueEyesWhite", 9, 900, 600, 75);
            }
        }else if(levelMonster == 10){
            genDragon = new Dragon("Alexstraszan", 10, 1000, 9000, 55);
        }
        return genDragon;
    }

    //Methode to generate random Exoskeleton Monster based on a level wanted.
    public static IMonster generateExoskeletonMonsterByLevel(int levelMonster) {
        IMonster genExoskeleton = null;

        if (levelMonster == 1){
            genExoskeleton  = new Exoskeleton("BigBad-Wolf", 1, 150, 250 , 15);
        }else if(levelMonster == 2){
            genExoskeleton  = new Exoskeleton("WickedWitch", 2, 250, 350 , 25);
        }else if(levelMonster == 3){
            genExoskeleton  = new Exoskeleton("Brandobaris", 3, 350, 450 , 30);
        }else if(levelMonster == 4){
            genExoskeleton  = new Exoskeleton("Aasterinian", 4, 400, 500 , 45);
        }else if(levelMonster == 5){
            genExoskeleton  = new Exoskeleton("St-Shargaas", 5, 550, 650 , 55);
        }else if(levelMonster == 6){
            int rand = (int)(Math.random() * 2) + 1;   //Generating a random number between 1-2
            if(rand%2 == 0){
                genExoskeleton = new Exoskeleton("Chronepsish", 6, 650, 750 , 60);
            }else {
                genExoskeleton = new Exoskeleton("DocOck", 6, 600, 600 , 55);
            }
        }else if(levelMonster == 7){
            genExoskeleton = new Exoskeleton("Cyrrollalee", 7, 700, 800 , 75);
        }else if(levelMonster == 8){
            genExoskeleton = new Exoskeleton("Kiaransalee", 8, 850, 950 , 85);
        }else if(levelMonster == 9){
            genExoskeleton = new Exoskeleton("St-Yeenoghu", 9, 950, 850 , 90);
        }else if(levelMonster == 10){
            int rand = (int)(Math.random() * 2) + 1;   //Generating a random number between 1-2
            if(rand%2 == 0){
                genExoskeleton = new Exoskeleton("Merrshaullk", 10, 1000, 900 , 55);
            }else {
                genExoskeleton = new Exoskeleton("Exodia", 10, 1000, 1000 , 50);
            }
        }

        return genExoskeleton;
    }

    //Methode to generate random Spirit Monster based on a level wanted.
    public static IMonster generateSpiritMonsterByLevel(int levelMonster) {
        IMonster genSpirit = null;

        if (levelMonster == 1){
            int rand = (int)(Math.random() * 2) + 1;   //Generating a random number between 1-2
            if(rand%2 == 0){
                genSpirit = new Spirit("Casper", 1, 100, 100, 50);
            }else {
                genSpirit = new Spirit("Blinky", 1, 450, 350, 35);
            }
        }else if(levelMonster == 2){
            genSpirit  = new Spirit("Andrealphus", 2, 600, 500, 40);
        }else if(levelMonster == 3){
            genSpirit  = new Spirit("Andromalius", 3, 550, 450, 25);
        }else if(levelMonster == 4){
            genSpirit  = new Spirit("Chiang-shih", 4, 700, 600, 40);
        }else if(levelMonster == 5){
            genSpirit  = new Spirit("FallenAngel", 5, 800, 700, 50);
        }else if(levelMonster == 6){
            genSpirit = new Spirit("Ereshkigall", 6, 950, 450, 35);
        }else if(levelMonster == 7){
            genSpirit = new Spirit("Melchiresas", 7, 350, 150, 75);
        }else if(levelMonster == 8){
            genSpirit = new Spirit("Jormunngand", 8, 600, 900, 20);
        }else if(levelMonster == 9){
            genSpirit = new Spirit("Rakkshasass", 9, 550, 600, 35);
        }else if(levelMonster == 10){
            genSpirit = new Spirit("Taltecuhtli", 10, 200, 500, 50);
        }

        return genSpirit;
    }
}
