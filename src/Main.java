import grid.Grid;
import grid.IGrid;
import heroes.IHero;
import heroes.Paladin;
import heroes.Sorcerer;
import heroes.Warrior;
import infos.ConsoleColors;
import item.*;
import market.IMarket;
import market.Market;
import monster.IMonster;
import monster.MonsterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws IOException {

        //Initialize the world
        IGrid world = new Grid(9);

        //Creating the market
        IMarket market = new Market();

        String filesDirectory = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "infos/";

        //Adding swords
        importItemsFromFile(market, filesDirectory, "Weaponry", "weapon");
        //Add Armors to market
        importItemsFromFile(market, filesDirectory, "Armory", "armor");
        //Add Potions
        importItemsFromFile(market, filesDirectory, "Potions", "potion");
        //Add Fire Spells
        importItemsFromFile(market, filesDirectory, "FireSpells", "fireSpell");
        //Add Ice Spells
        importItemsFromFile(market, filesDirectory, "IceSpells", "iceSpell");
        //Add Lightning Spells
        importItemsFromFile(market, filesDirectory, "LightningSpells", "lightningSpell");


        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to Legends: Monsters and Heroes!");
        System.out.println("Your legend awaits...");



        //Select number of heros
        int nbChars = -1;
        while (nbChars<0 || nbChars>3){
            System.out.println("How many heroes would you like in your party? (Max is 3)");
            nbChars = inputInt(sc);
        }
        //End Select number of heros

        //Choose heros
        List<IHero> listOfHeroes = new ArrayList<>();
        selectHeroes(listOfHeroes, filesDirectory, nbChars, sc);
        //End Choose heros

        //Party infos
        System.out.println();
        System.out.println(ConsoleColors.ANSI_YELLOW + "Your current party:" + ConsoleColors.ANSI_RESET);
        for (IHero h : listOfHeroes){
            System.out.println(ConsoleColors.ANSI_GREEN + h + ConsoleColors.ANSI_RESET);
        }

        System.out.println();
        System.out.println("Let the Adventure begin!!");
        System.out.println();
        world.showMap();


        List<IMonster> listOfMonsters = new ArrayList<>();

        char c = '0'; //For commands

        while(c!='q' && c!='Q'){
            System.out.println();
            if(listOfMonsters.isEmpty()){
                System.out.println("-------------------------");
                world.whereCanMove();
                System.out.println("Type I to show info.");
                if(world.isMarket()){
                    System.out.println("Type M to enter market.");
                }
                System.out.println("Type E to manage inventory.");
                System.out.println("Type Q to quit.");
                c = inputChar(sc);
                if(c=='W' || c=='w' || c=='S' || c=='s' || c=='a' || c=='A' || c=='d' || c=='D'){
                    if(world.move(c, listOfHeroes)){    //If heroes enter a battle
                        listOfMonsters = generateRandomMonsterByLevel(listOfHeroes);
                    }
                }else if(c=='i' || c=='I'){
                    for (IHero h : listOfHeroes){
                        System.out.println(h);
                    }
                    System.out.println();
                    world.showMap();
                    System.out.println();
                }else if(c=='e' || c=='E'){
                    //Inventory code
                    inventoryMenu(listOfHeroes, sc);
                    //End inventory code
                }else if(c=='m' || c=='M'){
                    //Market
                    marketInGame(listOfHeroes, world, market, sc);
                    //End Market
                }

                //Show infos after every turn, not in battles.
                if(c!='i' && c!='I'){
                    System.out.println();
                    world.showMap();
                    System.out.println();
                }
            }else{
                //Heroes in battle
                heroesInBattle(listOfHeroes, listOfMonsters, sc, c);
                //End heroes in battle.
            }
        }
    }

    public static List<IMonster> generateRandomMonsterByLevel(List<IHero> listOfHeroes){
        //List of monsters
        List<IMonster> listOfMonsters = new ArrayList<>();

        //Monster Factory
        MonsterFactory monsterFactory = new MonsterFactory();

        //Generate a monster with same level for every hero.
        for(IHero h : listOfHeroes){
            listOfMonsters.add(monsterFactory.createRandomMonster(h.getLevel()));   //Generate a monster
        }

        //Return list of monsters.
        return listOfMonsters;
    }

    public static IHero getRandomNonDeadHero(List<IHero> listOfHeroes){

        //check if there is non dead heroes
        if(IHero.countNonDeadHeroes(listOfHeroes) == 0){
            return null;
        }

        //If there is at least one alive hero.
        List<IHero> listOfNonDeadHeros = new ArrayList<>();
        for (IHero h : listOfHeroes){
            if(!h.isDead()){
                listOfNonDeadHeros.add(h);
            }
        }

        int rand = ThreadLocalRandom.current().nextInt(0, listOfNonDeadHeros.size());   //Generating a random number between 0-X

        return listOfNonDeadHeros.get(rand);
    }

    public static void inventoryMenu(List<IHero> listOfHeroes, Scanner sc){
        //Start inventory

        System.out.println("Please select a Hero to work with, Or type 0 to leave");
        int selectHero = -1;
        while (selectHero>listOfHeroes.size()
                || (selectHero!=1 && selectHero!=2 && selectHero!=3 && selectHero!=0)
                || selectHero<0){
            for (int i=0; i<listOfHeroes.size(); i++){
                System.out.println("("+(i+1)+") " + listOfHeroes.get(i));
            }

            selectHero = inputInt(sc);
        }
        if(selectHero != 0){
            IHero selectedHero = listOfHeroes.get(selectHero - 1);

            int selectedMenuNumber = -1;
            while (selectedMenuNumber!=1 && selectedMenuNumber!=2 && selectedMenuNumber!=3){
                System.out.println("What kind of item would you like to use ?");
                System.out.println("(1) Weapons");
                System.out.println("(2) Armor");
                System.out.println("(3) Potions");

                selectedMenuNumber = inputInt(sc);
            }

            //List of item with type chosed.
            List<IItem> chosedItems = new ArrayList<>();

            if(selectedMenuNumber == 1 || selectedMenuNumber == 2){
                int menuForWeaponOrArmor = selectedMenuNumber;

                selectedMenuNumber = -1;
                while (selectedMenuNumber!=1 && selectedMenuNumber!=2 && selectedMenuNumber!=0){
                    if(menuForWeaponOrArmor == 1){
                        System.out.println("Do you want to equip or unequip a weapon ?");
                    }else if(menuForWeaponOrArmor == 2){
                        System.out.println("Do you want to equip or unequip an armor ?");
                    }
                    System.out.println("(0) Leave");
                    System.out.println("(1) Equip");

                    if(menuForWeaponOrArmor == 1){
                        if(!selectedHero.canEquipWeapon()){
                            System.out.println("(2) Unequip " + selectedHero.getWeapon().getName());
                        }
                    }else if(menuForWeaponOrArmor == 2){
                        if(!selectedHero.canEquipArmor()){
                            System.out.println("(2) Unequip " + selectedHero.getArmor().getName());
                        }
                    }
                    selectedMenuNumber = inputInt(sc);
                }
                if(selectedMenuNumber == 2){
                    //Hero will remove his armor or weapon.
                    if(menuForWeaponOrArmor == 1){
                        selectedHero.setWeapon(null);
                    }else if(menuForWeaponOrArmor == 2){
                        selectedHero.setArmor(null);
                    }

                }else if( selectedMenuNumber == 1){
                    //Start equip a weapon or an armor.
                    int i = 1;
                    for(IItem item : selectedHero.getItemsList()){
                        //Select only weapons from heros inventory
                        if(menuForWeaponOrArmor == 1){
                            if(item instanceof Weapon){
                                System.out.println(i + "-Equip : " + item);
                                chosedItems.add(item);
                                i++;
                            }

                        //Select only armor from hero inventory.
                        }else if(menuForWeaponOrArmor == 2){
                            if(item instanceof Armor){
                                System.out.println(i + "-Equip : " + item);
                                chosedItems.add(item);
                                i++;
                            }
                        }
                    }
                    if(chosedItems.size() > 0){

                        int numWeaponOrArmor = -1;
                        IItem itemChosed = null;

                        while(itemChosed == null && numWeaponOrArmor!=0){
                            System.out.println("Type number of Weapon, or 0 to leave");
                            numWeaponOrArmor = inputInt(sc);
                            if(!(numWeaponOrArmor>chosedItems.size() || numWeaponOrArmor<=0)){
                                itemChosed = chosedItems.get(numWeaponOrArmor-1);
                            }
                        }

                        if(numWeaponOrArmor !=0){

                            if(menuForWeaponOrArmor == 1){
                                if(itemChosed != null){
                                    selectedHero.setWeapon(itemChosed);
                                }
                            }else if(menuForWeaponOrArmor == 2){
                                if(itemChosed != null){
                                    selectedHero.setArmor(itemChosed);
                                }
                            }

                        }

                    }else{
                        if(menuForWeaponOrArmor == 1){
                            System.out.println("Hero " + selectedHero.getName() + " has no weapons in inventory.");
                        }else if(menuForWeaponOrArmor == 2){
                            System.out.println("Hero " + selectedHero.getName() + " has no armors in inventory.");
                        }
                    }
                    //End equip a weapon or armor
                }

            }else if(selectedMenuNumber == 3){
                //Start hero select Potion to use.
                heroUsesAPotion(selectedHero, sc);
                //End hero select Potion to use.
            }
        }
        //End inventory
    }

    public static void heroesInBattle(List<IHero> listOfHeroes, List<IMonster> listOfMonsters,Scanner sc, char c){
        //heros enter a combat
        c = 'x';

        System.out.println("-------------------------");
        System.out.println(ConsoleColors.ANSI_RED + "You're currently in a battle:" + ConsoleColors.ANSI_RESET);

        //Every hero will do his own action.
        for(IHero h : listOfHeroes){
            if(!h.isDead()){ //Check if hero is dead or not.
                boolean heroPlayedHisTurn = false;  //variable to check if a hero has played his turn or not.
                while (!heroPlayedHisTurn && IMonster.countNonDeadMonsters(listOfMonsters)>0){
                    System.out.println();
                    System.out.println(ConsoleColors.ANSI_GREEN + "Hero's turn: " + h + ConsoleColors.ANSI_RESET);

                    while (c!='i' && c!='I' && c!='C' && c!='c' && c!='P' && c!='p' && c!='S' && c!='s'){
                        System.out.println();
                        System.out.println(ConsoleColors.ANSI_RED + "Type C to attack a monster." + ConsoleColors.ANSI_RESET);
                        System.out.println(ConsoleColors.ANSI_BLUE + "Type P to use a Potion." + ConsoleColors.ANSI_RESET);
                        System.out.println(ConsoleColors.ANSI_YELLOW + "Type S to use a Spell." + ConsoleColors.ANSI_RESET);
                        System.out.println("Type I to show info.");
                        c = inputChar(sc);
                    }

                    //User selected a good command.
                    if(c=='i' || c=='I'){
                        c = 'x';
                        //Show heroes stats in battle
                        System.out.println();
                        System.out.println("Heroes stats: ");
                        for (IHero hero : listOfHeroes){
                            System.out.println(ConsoleColors.ANSI_GREEN + hero + ConsoleColors.ANSI_RESET);
                        }
                        System.out.println();
                        //Show monsters stats in battle
                        System.out.println("Monsters stats: ");
                        for (IMonster m : listOfMonsters){
                            System.out.println(ConsoleColors.ANSI_RED + m + ConsoleColors.ANSI_RESET);
                        }
                    }else if(c=='c' || c=='C'){
                        c = 'x';
                        System.out.println();
                        System.out.println("Choose a monster to attack");
                        for(int i=0; i<listOfMonsters.size(); i++){
                            if(!listOfMonsters.get(i).isDead()){
                                System.out.println("("+(i+1)+")" + listOfMonsters.get(i));
                            }
                        }
                        int monsterAttacked = -1;
                        while(monsterAttacked>listOfMonsters.size()
                                || (monsterAttacked!=1 && monsterAttacked!=2 && monsterAttacked!=3)
                                || listOfMonsters.get(monsterAttacked-1).isDead() ){
                            System.out.println();
                            System.out.println("Type number of monster to attack");

                            monsterAttacked = inputInt(sc);
                        }
                        //Hero attack monster.
                        listOfMonsters.get(monsterAttacked-1).receiveDamage(h);
                        heroPlayedHisTurn = true;

                    }else if(c=='p' || c=='P'){
                        c = 'x';

                        //Hero select a potion to use, if he used it, end of round for him.
                        heroPlayedHisTurn = heroUsesAPotion(h, sc);

                    }else if(c=='s' || c=='S'){
                        c = 'x';
                        int numTypeOfPotion = 0;
                        while (numTypeOfPotion!=1 && numTypeOfPotion!=2 && numTypeOfPotion!=3){
                            System.out.println("What kind of Spells would you like?");
                            System.out.println("(1) Fire spells");
                            System.out.println("(2) Ice spells");
                            System.out.println("(3) Lightning Spells");

                            numTypeOfPotion = inputInt(sc);
                        }
                        List<IItem> listOfSpells = new ArrayList<>();
                        for(IItem item : h.getItemsList()){
                            if(numTypeOfPotion == 1){
                                if(item instanceof FireSpell){
                                    listOfSpells.add(item);
                                }
                            }else if(numTypeOfPotion == 2){
                                if(item instanceof IceSpell){
                                    listOfSpells.add(item);
                                }
                            }else if(numTypeOfPotion == 3){
                                if(item instanceof LightningSpell){
                                    listOfSpells.add(item);
                                }
                            }
                        }
                        if(listOfSpells.isEmpty()){    //Hero has no Spells
                            System.out.println();
                            System.out.println(ConsoleColors.ANSI_YELLOW + h.getName() + " has no Spells in inventory." + ConsoleColors.ANSI_RESET);
                        }else {
                            int i = 1;
                            System.out.println("Choose a spell to use.");
                            for(IItem item : listOfSpells){
                                System.out.println(i + "-" + item);
                                i++;
                            }
                            int spellNumber = -1;
                            IItem spellChosed = null;
                            while(spellChosed == null){
                                System.out.println("Type number of spell.");

                                spellNumber = inputInt(sc);
                                if(!(spellNumber>listOfSpells.size() || spellNumber<=0)){
                                    spellChosed = listOfSpells.get(spellNumber-1);
                                }
                            }

                            //
                            System.out.println("Choose a monster to use spell on");
                            for(i=0; i<listOfMonsters.size(); i++){
                                if(!listOfMonsters.get(i).isDead()){
                                    System.out.println("("+(i+1)+")" + listOfMonsters.get(i));
                                }
                            }
                            int monsterAttacked = -1;
                            while(monsterAttacked>listOfMonsters.size()
                                    || (monsterAttacked!=1 && monsterAttacked!=2 && monsterAttacked!=3)
                                    || listOfMonsters.get(monsterAttacked-1).isDead() ){
                                System.out.println("Type number of monster to attack");

                                monsterAttacked = inputInt(sc);
                            }
                            //
                            heroPlayedHisTurn = ((ISpell)spellChosed).useSpellOnMonsterByHero(h, listOfMonsters.get(monsterAttacked-1));
                        }
                    }
                }
            }
        }

        //Monsters will attack heroes randomly.
        System.out.println();
        System.out.println("Monsters turn to attack heroes");
        for (IMonster m : listOfMonsters){
            if(!m.isDead()){
                if(IHero.countNonDeadHeroes(listOfHeroes) != 0){     //Check if there is non dead heros to be attacked.
                    IHero hero = getRandomNonDeadHero(listOfHeroes);
                    if(hero != null){
                        hero.receiveDamage(m);
                    }
                }
            }
        }
        //All monsters or heroes are dead
        if(IMonster.countNonDeadMonsters(listOfMonsters) == 0 || IHero.countNonDeadHeroes(listOfHeroes) == 0){
            for(IHero h : listOfHeroes){
                h.endBattle(listOfMonsters.size(), IMonster.maxLevelMonster(listOfMonsters));
            }

            if(IMonster.countNonDeadMonsters(listOfMonsters) == 0){
                System.out.println("Battle finished! All monsters are dead");
            }else{
                System.out.println("Battle finished! All heroes are dead");
                c = 'q';
            }
            listOfMonsters.clear();

        }else{  // Round ended
            for(IHero h : listOfHeroes){
                h.endRound();
            }
        }
    }

    public static void marketInGame(List<IHero> listOfHeroes, IGrid world, IMarket market, Scanner sc){
        //Market
        int num = -1;

        if(world.isMarket()){
            //Start market
            while(num != 2){
                System.out.println("You've arrived at a Market!");
                System.out.println("What would you like to do?");
                num = -1;
                while(num!=1 && num!=2){
                    System.out.println("(1) Enter the shop");
                    System.out.println("(2) Move along");

                    num = inputInt(sc);
                }
                if(num==1){
                    System.out.println("Welcome to the shop!");
                    System.out.println();
                    System.out.println("Please select a Hero to work with, Or type 0 to leave");
                    num = -1;
                    while (num!=1 && num!=2 && num!=3 && num!=0){
                        for (int i=0; i<listOfHeroes.size(); i++){
                            System.out.println("("+(i+1)+")" + listOfHeroes.get(i));
                        }

                        num = inputInt(sc);
                    }
                    if(num==1 || num==2 || num==3){
                        IHero selectHero = listOfHeroes.get(num-1);
                        System.out.println("Hero " + selectHero + "'s inventory");
                        for(IItem item : selectHero.getItemsList()){
                            System.out.println(item);
                        }
                        System.out.println();
                        num = -1;
                        while (num!=1 && num!=2 && num!=0){
                            System.out.println("What would you like to do?");
                            System.out.println("(1) Shop");
                            System.out.println("(2) Sell");
                            System.out.println("(0) Move along");

                            num = inputInt(sc);
                        }
                        if(num==1){
                            num = -1;
                            while (num!=1 && num!=2 && num!=3 && num!=4 &&num!=0){
                                System.out.println("What kind of item would you like?");
                                System.out.println("(1) Weapons");
                                System.out.println("(2) Armor");
                                System.out.println("(3) Potions");
                                System.out.println("(4) Spells");
                                System.out.println("(0) Move along");

                                num = inputInt(sc);
                            }

                            List<IItem> listProposedItems= new ArrayList<>();
                            if(num==1){
                                int i=1;
                                for (IItem item : market.getItems()){
                                    if(item instanceof Weapon){
                                        System.out.println(i + "-" + item);
                                        listProposedItems.add(item);
                                        i++;
                                    }
                                }

                            }else if(num==2){     //Selected to buy an armor.
                                int i=1;
                                for (IItem item : market.getItems()){
                                    if(item instanceof Armor){
                                        System.out.println(i + "-" + item);
                                        listProposedItems.add(item);
                                        i++;
                                    }
                                }

                            }else if(num==3){
                                int i=1;
                                for (IItem item : market.getItems()){
                                    if(item instanceof Potion){
                                        System.out.println(i + "-" + item);
                                        listProposedItems.add(item);
                                        i++;
                                    }
                                }

                            }else if(num==4){
                                num = -1;
                                while (num!=1 && num!=2 && num!=3 && num!=0){
                                    System.out.println("What kind of Spells would you like?");
                                    System.out.println("(1) Fire spells");
                                    System.out.println("(2) Ice spells");
                                    System.out.println("(3) Lightning Spells");
                                    System.out.println("(0) Move along");

                                    num = inputInt(sc);
                                }
                                if(num==1){
                                    int i=1;
                                    for (IItem item : market.getItems()){
                                        if(item instanceof FireSpell){
                                            System.out.println(i + "-" + item);
                                            listProposedItems.add(item);
                                            i++;
                                        }
                                    }
                                }else if(num==2){
                                    int i=1;
                                    for (IItem item : market.getItems()){
                                        if(item instanceof IceSpell){
                                            System.out.println(i + "-" + item);
                                            listProposedItems.add(item);
                                            i++;
                                        }
                                    }
                                }else if(num==3){
                                    int i=1;
                                    for (IItem item : market.getItems()){
                                        if(item instanceof LightningSpell){
                                            System.out.println(i + "-" + item);
                                            listProposedItems.add(item);
                                            i++;
                                        }
                                    }
                                }
                            }
                            if(num != 0){
                                int itemNumber = -1;
                                IItem itemChosed = null;
                                while(itemChosed == null && itemNumber!=0){
                                    System.out.println("Type number of the item, 0 to leave");

                                    itemNumber = inputInt(sc);
                                    if(!(itemNumber>listProposedItems.size() || itemNumber<=0)){
                                        itemChosed = listProposedItems.get(itemNumber-1);
                                    }
                                }
                                if(itemNumber != 0){
                                    market.heroBuyItem(itemChosed, selectHero);
                                    System.out.println();
                                }
                            }

                        }else if (num==2){
                            System.out.println("What item would you sell?");
                            for(IItem item : selectHero.getItemsList()){
                                System.out.println(item);
                            }
                            String itemName = "";
                            while(market.findItemByName(itemName) == null){
                                System.out.println("Type name of the item");
                                try{
                                    itemName = sc.next();
                                }catch (Exception e){
                                    System.out.println("Please type a valid number.");
                                }
                            }
                            market.heroSellItem(market.findItemByName(itemName), selectHero);
                            System.out.println();
                        }
                    }
                    num=-1; //Make sure heroes are still in market
                }
            }
            //End market
        }else{
            System.out.println("You're not at a Market!");
        }
        //End Market
    }

    public static void importItemsFromFile(IMarket market, String filesDirectory, String fileName, String type) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filesDirectory + fileName + ".txt"));
        String line = in.readLine();
        line = in.readLine(); //skip first line and read second line
        String item[];
        while (line != null && !line.isEmpty()){
            item = line.trim().replaceAll("\\s+"," ").split(" ");
            if(type.equalsIgnoreCase("weapon")){
                market.addItem(new Weapon(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]), Double.parseDouble(item[3]), Integer.parseInt(item[4])));
            }else if(type.equalsIgnoreCase("armor")){
                market.addItem(new Armor(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]), Double.parseDouble(item[3])));
            }else if(type.equalsIgnoreCase("potion")){
                List<String> attributeAffected = Arrays.asList(item[item.length-1].split("/"));
                market.addItem(new Potion(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]), Double.parseDouble(item[3]), attributeAffected));
            }else if(type.equalsIgnoreCase("fireSpell")){
                market.addItem(new FireSpell(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]), Double.parseDouble(item[3]), Double.parseDouble(item[4])));
            }else if(type.equalsIgnoreCase("iceSpell")){
                market.addItem(new IceSpell(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]), Double.parseDouble(item[3]), Double.parseDouble(item[4])));
            }else if(type.equalsIgnoreCase("lightningSpell")){
                market.addItem(new LightningSpell(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]), Double.parseDouble(item[3]), Double.parseDouble(item[4])));
            }
            line = in.readLine();
        }
    }

    public static void showHeroes(String filesDirectory, String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filesDirectory + fileName + ".txt"));
        String line = in.readLine();
        line = in.readLine(); //skip first line and read second line
        String item[];
        int i=1;
        String prettier = "|%-25s|%10s |%10s |%10s |%10s |%19s |%19s |%n";
        System.out.format(prettier, "Name", "Mana", "Strength", "Agility", "Dexterity", "Starting Money", "Starting Experience");
        while (line != null && !line.isEmpty()){
            item = line.trim().replaceAll("\\s+"," ").split(" ");
            System.out.format(prettier, i+"-"+item[0], item[1], item[2], item[3], item[4], item[5], item[6]);
            line = in.readLine();
            i++;
        }
    }

    public static void showHeroeByNumber(String filesDirectory, String fileName, int heroNumber) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filesDirectory + fileName + ".txt"));
        String line = in.readLine();
        line = in.readLine(); //skip first line and read second line
        String item[];
        int i=1;
        String prettier = "|%-25s|%10s |%10s |%10s |%10s |%19s |%19s |%n";
        System.out.format(prettier, "Name", "Mana", "Strength", "Agility", "Dexterity", "Starting Money", "Starting Experience");
        while (line != null && !line.isEmpty()){
            item = line.trim().replaceAll("\\s+"," ").split(" ");
            if(heroNumber == i){
                System.out.format(prettier, i+"-"+item[0], item[1], item[2], item[3], item[4], item[5], item[6]);
                break;
            }
            line = in.readLine();
            i++;
        }
    }

    public static void selectHeroes(List<IHero> listOfHeroes, String filesDirectory, int nbChars, Scanner sc) throws IOException {
        for (int i=0; i<nbChars; i++){
            int num = -1;

            while(num!=1 && num!=2 && num!=3){
                System.out.println("What type of hero would you like?");
                System.out.println("Warrior = 1, Sorcerer = 2, Paladin = 3");
                num = inputInt(sc);
                if(num==1 || num==2 || num==3){
                    System.out.println("YOUR CHOICE IS " + num);
                }
            }
            if(num==1){
                num = -1;
                while(num!=1 && num!=2 && num!=3 && num!=4 && num!=5 && num!=6){
                    System.out.println("Please select a Warrior with the corresponding number");
                    System.out.println();
                    showHeroes(filesDirectory, "Warriors");
                    System.out.println();
                    num = inputInt(sc);
                }
                if(num==1){
                    listOfHeroes.add(new Warrior("Gaerdal_Ironhand", 100, 700, 500, 600, 1354, 7));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 1);
                }else if(num==2){
                    listOfHeroes.add(new Warrior("Sehanine_Monnbow", 600, 700, 800, 500, 2500, 8));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 2);
                }else if(num==3){
                    listOfHeroes.add(new Warrior("Muamman_Duathall", 300, 900, 500, 750, 2546, 6));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 3);
                }else if(num==4){
                    listOfHeroes.add(new Warrior("Flandal_Steelskin", 200, 750, 650, 700, 2500, 7));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 4);
                }else if(num==5){
                    listOfHeroes.add(new Warrior("Undefeated_Yoj", 400, 800, 400, 700, 2500, 7));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 5);
                }else if(num==6){
                    listOfHeroes.add(new Warrior("Eunoia_Cyn", 400, 700, 800, 600, 2500, 6));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 6);
                }

            }else if(num==2){
                num = -1;
                while(num!=1 && num!=2 && num!=3 && num!=4 && num!=5 && num!=6){
                    System.out.println("Please select a Sorcerer with the corresponding number");
                    System.out.println();
                    showHeroes(filesDirectory, "Sorcerers");
                    System.out.println();
                    num = inputInt(sc);
                }
                if(num==1){
                    listOfHeroes.add(new Sorcerer("Rillifane_Rallathil", 1300, 750, 450, 500, 2500, 9));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 1);
                }else if(num==2){
                    listOfHeroes.add(new Sorcerer("Segojan_Earthcaller", 900, 800, 500, 650, 2500, 5));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 2);
                }else if(num==3){
                    listOfHeroes.add(new Sorcerer("Reign_Havoc", 800, 800, 800, 800, 2500, 8));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 3);
                }else if(num==4){
                    listOfHeroes.add(new Sorcerer("Reverie_Ashels", 900, 800, 700, 400, 2500, 7));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 4);
                }else if(num==5){
                    listOfHeroes.add(new Sorcerer("Kalabar", 800, 850, 400, 600, 2500, 6));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 5);
                }else if(num==6){
                    listOfHeroes.add(new Sorcerer("Skye_Soar", 1000, 700, 400, 500, 2500, 5));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 6);
                }

            }else if(num==3){
                num = -1;
                while(num!=1 && num!=2 && num!=3 && num!=4 && num!=5 && num!=6){
                    System.out.println("Please select a Paladin with the corresponding number");
                    System.out.println();
                    showHeroes(filesDirectory, "Paladins");
                    System.out.println();
                    num = inputInt(sc);
                }
                if(num==1){
                    listOfHeroes.add(new Paladin("Parzival", 300, 750, 650, 700, 2500, 7));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 1);
                }else if(num==2){
                    listOfHeroes.add(new Paladin("Sehanine_Moonbow", 300, 750, 700, 700, 2500, 7));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 2);
                }else if(num==3){
                    listOfHeroes.add(new Paladin("Skoraeus_Stonebones", 250, 650, 600, 350, 2500, 4));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 3);
                }else if(num==4){
                    listOfHeroes.add(new Paladin("Garl_Glittergold", 100, 600, 500, 400, 2500, 5));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 4);
                }else if(num==5){
                    listOfHeroes.add(new Paladin("Amaryllis_Astra", 500, 500, 500, 500, 2500, 5));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 5);
                }else if(num==6){
                    listOfHeroes.add(new Paladin("Caliber_Heist", 400, 400, 400, 400, 2500, 8));
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 6);
                }

            }
        }
    }

    public static int inputInt(Scanner sc){
        int num = -1;
        try{
            num = sc.nextInt();
        }catch (Exception e){
            System.out.println("Please type a valid number.");
            sc.next();
        }
        return num;
    }

    public static char inputChar(Scanner sc){
        char ch = 'x';
        try{
            ch = sc.next().charAt(0);
        }catch (Exception e){
            System.out.println("Please type a valid number.");
            sc.next();
        }
        return ch;
    }

    public static boolean heroUsesAPotion(IHero hero, Scanner sc){
        //Start hero use potion
        List<IItem> listOfPotions = new ArrayList<>();
        for(IItem item : hero.getItemsList()){
            if(item instanceof Potion){
                listOfPotions.add(item);
            }
        }
        if(listOfPotions.isEmpty()){    //Hero has no Potions
            System.out.println();
            System.out.println(ConsoleColors.ANSI_YELLOW + hero.getName() + " has no Potions in inventory." + ConsoleColors.ANSI_RESET);
            return false;
        }else {
            int i = 1;
            System.out.println("Choose a potion to use.");
            for(IItem item : listOfPotions){
                System.out.println(i + "-" + item);
                i++;
            }
            System.out.println("0- Leave");
            int potionNumber = -1;
            IItem potionChosed = null;
            while(potionChosed == null || potionNumber==0){
                System.out.println("Type number of potion");

                potionNumber = inputInt(sc);
                if(!(potionNumber>listOfPotions.size() || potionNumber<=0)){
                    potionChosed = listOfPotions.get(potionNumber-1);
                }
            }
            if(potionChosed == null){
                return false;
            }
            ((IPotion)potionChosed).usePotionByHero(hero);
            return true;
        }
        //End hero use potion
    }
}