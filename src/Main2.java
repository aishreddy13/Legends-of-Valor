import grid.IGrid;
import grid.IValorMap;
import grid.ValorMap;
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

public class Main2 {
    public static void main(String[] args) throws IOException {

        //Initialize the world
        IValorMap world = new ValorMap(8);

        world.showMap();

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


        System.out.println("Welcome to Legends of Valor!");
        System.out.println("Your legend awaits...");

        //Choose heros
        selectHeroes(filesDirectory, world, 3, sc);

        world.showMap();

        char c = 'x'; //For user commands

        int numRounds = 1;

        while(c!='q' && c!='Q'){

            System.out.println();
            System.out.println("-------------------------");
            System.out.println("Type P to start round.");
            if(!world.heroesNextMarket().isEmpty()){
                System.out.println("Type M to enter Market.");
            }
            System.out.println("Type I to show info.");
            System.out.println("Type Q to quit.");

            c = inputChar(sc); // User will type a character to choose what to do.

            //User choosed to show informations about map, heroes and monsters.
            if(c=='i' || c=='I'){
                System.out.println();
                world.showMap();
                System.out.println();
                System.out.println("List of heroes:");
                for (IHero h : world.getHeroList()){
                    System.out.println(h);
                }
                System.out.println();
                System.out.println("List of monsters:");
                for (IMonster m : world.getMonsterList()){
                    System.out.println(m);
                }
                System.out.println();

            }else if(c=='m' || c=='M'){
                //Market
                List<IHero> heroesCanEnterMarket = world.heroesNextMarket();
                if(!heroesCanEnterMarket.isEmpty()){
                    marketInGame(heroesCanEnterMarket, market, sc);
                }else {
                    System.out.println("No hero is near his nexus.");
                }
                //End Market
            }else if(c=='p' || c=='P'){
                //heroesFightMonsters
                if(heroesFightMonsters(world, sc, c, numRounds)){
                    c = 'q'; // Game ends.
                }

                //All heroes played their turn
                numRounds++; // Increment round number.
            }

            if(numRounds%8 == 0){
                //New monsters will be generated on the monsters nexus
                world.putMonstersNextToNexus(generateRandomMonsterByLevel(world.getHeroList().get(0).getLevel()), 'T');
                world.putMonstersNextToNexus(generateRandomMonsterByLevel(world.getHeroList().get(1).getLevel()), 'M');
                world.putMonstersNextToNexus(generateRandomMonsterByLevel(world.getHeroList().get(2).getLevel()), 'B');
            }
        }

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

    public static void selectHeroes(String filesDirectory, IValorMap world, int nbChars, Scanner sc) throws IOException {
        for (int i=0; i<nbChars; i++){

            //Chosed hero
            IHero hero = null;

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
                    hero = new Warrior("Gaerdal_Ironhand", 100, 700, 500, 600, 1354, 7);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 1);
                }else if(num==2){
                    hero = new Warrior("Sehanine_Monnbow", 600, 700, 800, 500, 2500, 8);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 2);
                }else if(num==3){
                    hero = new Warrior("Muamman_Duathall", 300, 900, 500, 750, 2546, 6);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 3);
                }else if(num==4){
                    hero = new Warrior("Flandal_Steelskin", 200, 750, 650, 700, 2500, 7);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 4);
                }else if(num==5){
                    hero = new Warrior("Undefeated_Yoj", 400, 800, 400, 700, 2500, 7);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Warriors", 5);
                }else if(num==6){
                    hero = new Warrior("Eunoia_Cyn", 400, 700, 800, 600, 2500, 6);
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
                    hero = new Sorcerer("Rillifane_Rallathil", 1300, 750, 450, 500, 2500, 9);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 1);
                }else if(num==2){
                    hero = new Sorcerer("Segojan_Earthcaller", 900, 800, 500, 650, 2500, 5);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 2);
                }else if(num==3){
                    hero = new Sorcerer("Reign_Havoc", 800, 800, 800, 800, 2500, 8);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 3);
                }else if(num==4){
                    hero = new Sorcerer("Reverie_Ashels", 900, 800, 700, 400, 2500, 7);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 4);
                }else if(num==5){
                    hero = new Sorcerer("Kalabar", 800, 850, 400, 600, 2500, 6);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Sorcerers", 5);
                }else if(num==6){
                    hero = new Sorcerer("Skye_Soar", 1000, 700, 400, 500, 2500, 5);
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
                    hero = new Paladin("Parzival", 300, 750, 650, 700, 2500, 7);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 1);
                }else if(num==2){
                    hero = new Paladin("Sehanine_Moonbow", 300, 750, 700, 700, 2500, 7);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 2);
                }else if(num==3){
                    hero = new Paladin("Skoraeus_Stonebones", 250, 650, 600, 350, 2500, 4);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 3);
                }else if(num==4){
                    hero = new Paladin("Garl_Glittergold", 100, 600, 500, 400, 2500, 5);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 4);
                }else if(num==5){
                    hero = new Paladin("Amaryllis_Astra", 500, 500, 500, 500, 2500, 5);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 5);
                }else if(num==6){
                    hero = new Paladin("Caliber_Heist", 400, 400, 400, 400, 2500, 8);
                    System.out.println("You have chosen:");
                    showHeroeByNumber(filesDirectory, "Paladins", 6);
                }
            }

            //Choose lane of hero
            num = -1;
            while(num!=1 && num!=2 && num!=3){
                System.out.println("Which lane of map hero will be ?");
                if(world.isThereHero(7, 0) == null){
                    System.out.println("Type 1 for Top Lane");
                }
                if(world.isThereHero(7, 3) == null){
                    System.out.println("Type 2 for Mid Lane");
                }
                if(world.isThereHero(7, 6) == null){
                    System.out.println("Type 3 for Bot Lane");
                }
                num = inputInt(sc);
                if(num==1){
                    System.out.println("YOUR CHOICE IS Top Lane");
                }else if(num==2){
                    System.out.println("YOUR CHOICE IS Mid Lane");
                }else if(num==3){
                    System.out.println("YOUR CHOICE IS Bot Lane");
                }

                if(num==1 && world.isThereHero(7, 0)!=null){
                    System.out.println("You can't choose top lane, already full.");
                    num = -1;
                }else if(num==2 && world.isThereHero(7, 3)!=null){
                    System.out.println("You can't choose mid lane, already full.");
                    num = -1;
                }else if(num==3 && world.isThereHero(7, 6)!=null){
                    System.out.println("You can't choose bot lane, already full.");
                    num = -1;
                }
            }

            if(num == 1){
                //Added heroes to its mid nexus.
                world.putHerosNextToNexus(hero, 'T');
                //Generate random monster at mid nexus
                world.putMonstersNextToNexus(generateRandomMonsterByLevel(hero.getLevel()), 'T');
            }else if(num == 2){
                //Added heroes to its mid nexus.
                world.putHerosNextToNexus(hero, 'M');
                //Generate random monster at mid nexus
                world.putMonstersNextToNexus(generateRandomMonsterByLevel(hero.getLevel()), 'M');
            }else if(num == 3){
                //Added heroes to its mid nexus.
                world.putHerosNextToNexus(hero, 'B');
                //Generate random monster at mid nexus
                world.putMonstersNextToNexus(generateRandomMonsterByLevel(hero.getLevel()), 'B');
            }
        }
    }

    public static IMonster generateRandomMonsterByLevel(int monsterLevel){
        //monster to be generated
        IMonster monster;

        //Monster Factory
        MonsterFactory monsterFactory = new MonsterFactory();

        //Generate a monster with same level as hero.
        monster = monsterFactory.createRandomMonster(monsterLevel);   //Generate a monster


        //Return monster generated.
        return monster;
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

    public static void marketInGame(List<IHero> listOfHeroes, IMarket market, Scanner sc){
        //Market
        int num = -1;

        if(!listOfHeroes.isEmpty()){
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

    public static boolean heroesFightMonsters(IValorMap world, Scanner sc, char c, int numRounds){
        //heroes enter a combat
        System.out.println("-------------------------");
        System.out.println(ConsoleColors.ANSI_RED + "You're in round number: " + numRounds + ConsoleColors.ANSI_RESET);

        //Every hero will do his own action.
        for(IHero h : world.getHeroList()){
            if(!h.isDead()) { //Check if hero is dead or not.
                boolean heroPlayedHisTurn = false;  //variable to check if a hero has played his turn or not.

                while (!heroPlayedHisTurn){
                    System.out.println();
                    System.out.println(ConsoleColors.ANSI_GREEN + "Hero's turn: " + h + ConsoleColors.ANSI_RESET);

                    c = 'x';

                    while (c!='i' && c!='I' && c!='C' && c!='c' && c!='P' && c!='p' && c!='l' && c!='L'
                            && c!='T' && c!='t' && c!='R' && c!='r'
                            && c!='n' && c!='N' && c!='w' && c!='W' && c!='e' && c!='E' && c!='s' && c!='S'){
                        System.out.println();
                        world.whereCanMove(h);
                        if(!world.findNearestMonsters(h).isEmpty()){
                            System.out.println(ConsoleColors.ANSI_RED + "Type C to attack a monster." + ConsoleColors.ANSI_RESET);
                        }
                        System.out.println(ConsoleColors.ANSI_BLUE + "Type P to use a Potion." + ConsoleColors.ANSI_RESET);
                        System.out.println(ConsoleColors.ANSI_YELLOW + "Type L to use a Spell." + ConsoleColors.ANSI_RESET);
                        System.out.println("Type T to teleport.");
                        System.out.println("Type R to recall.");
                        System.out.println("Type I to show info.");
                        c = inputChar(sc);

                    }

                    if((c=='C' || c=='c') && world.findNearestMonsters(h).isEmpty()){
                        System.out.println("No monster to attack.");

                    }else if(c=='i' || c=='I'){
                        System.out.println();
                        world.showMap();
                        System.out.println();
                        System.out.println("List of heroes:");
                        for (IHero hero : world.getHeroList()){
                            System.out.println(hero);
                        }
                        System.out.println();
                        System.out.println("List of monsters:");
                        for (IMonster m : world.getMonsterList()){
                            System.out.println(m);
                        }
                        System.out.println();

                    }else if(c=='n' || c=='N' || c=='w' || c=='W' || c=='e' || c=='E' || c=='s' || c=='S'){
                        if(world.move(c, h)){
                            heroPlayedHisTurn = true;
                        }else{
                            System.out.println("Hero can't move in that direction, try again.");
                        }

                    }else if(c=='t' || c=='T'){
                        int num = -1;

                        //We ask user to input number of a hero
                        while(num!=0 && num!=1 && num!=2 && num!=3){
                            System.out.println("Select the hero to be teleported next");
                            for(int i=0; i<world.getHeroList().size(); i++){
                                if(i != world.getHeroList().indexOf(h)){
                                    System.out.println((i+1) + "- " + world.getHeroList().get(i));
                                }
                            }
                            num = inputInt(sc);
                        }

                        //If user inputs a number of a hero
                        if(num != 0){
                            if(world.heroTeleport(h, num-1)){
                                heroPlayedHisTurn = true;
                            }
                        }

                    }else if(c=='r' || c=='R'){
                        if(world.heroRecall(h)){
                            heroPlayedHisTurn = true;
                        }

                    }else if((c=='C' || c=='c') && !world.findNearestMonsters(h).isEmpty()){
                        List<IMonster> listOfMonsters = world.findNearestMonsters(h);

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
                        //Hero select a potion to use, if he used it, end of round for him.
                        heroPlayedHisTurn = heroUsesAPotion(h, sc);

                    }else if(c=='l' || c=='L'){
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

                            List<IMonster> listOfMonsters = world.findNearestMonsters(h);
                            if(listOfMonsters.size()>0){
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
                            }else {
                                System.out.println("There is no near monsters to attack.");
                            }
                        }
                    }

                }
                //If this hero is on the monsters nexus, the game ends.
                if(world.heroInMonstersNexus(h)){
                    return true;
                }
            }
        }

        //Monsters turn to play.
        for (IMonster m : world.getMonsterList()){
            if(!m.isDead()){
                world.monsterTurn(m);

                //If this monster is on the heroes nexus, the game ends.
                if(world.monsterInHeroesNexus(m)){
                    return true;
                }
            }
        }

        //Print map after every round
        world.showMap();

        return false;
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
