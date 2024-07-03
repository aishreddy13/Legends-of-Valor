package grid;

import heroes.IHero;
import monster.IMonster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ValorMap implements IValorMap {

    int length;
    char[][] map;



    int[][] herosPlaces;
    List<IHero> heroList;
    int[][] monsterPlaces;
    List<IMonster> monsterList;

    public ValorMap(int length) {
        this.length = length;
        this.map = new char[length][length];

        this.herosPlaces = new int[][]{{-1, -1}, {-1, -1}, {-1, -1}};
        heroList = Arrays.asList(null, null, null);

        this.monsterPlaces = new int[][]{{-1, -1}, {-1, -1}, {-1, -1}};
        monsterList = Arrays.asList(null, null, null);

        int nbBushSpaces = (int) ((length-2)*(length-2)*0.2);
        int nbCaveSpaces = (int) ((length-2)*(length-2)*0.2) + nbBushSpaces;
        int nbKoulouSpaces = (int) ((length-2)*(length-2)*0.2) + nbCaveSpaces;

        //We will generate random number
        List<List<Integer>> listOfPlaces = new ArrayList<>();
        for(int i=1; i<length-1; i++){
            listOfPlaces.add(Arrays.asList(i, 0));
            listOfPlaces.add(Arrays.asList(i, 1));
            listOfPlaces.add(Arrays.asList(i, 3));
            listOfPlaces.add(Arrays.asList(i, 4));
            listOfPlaces.add(Arrays.asList(i, 6));
            listOfPlaces.add(Arrays.asList(i, 7));
        }
        Collections.shuffle(listOfPlaces);

        //We assign random places for the map
        for(int i=0; i<listOfPlaces.size(); i++){
            int x = listOfPlaces.get(i).get(0);
            int y = listOfPlaces.get(i).get(1);

            if(i<=nbBushSpaces){
                this.map[x][y] = 'B';
            }else if(i<=nbCaveSpaces){
                this.map[x][y] = 'C';
            }else if(i<=nbKoulouSpaces){
                this.map[x][y] = 'K';
            }else{
                this.map[x][y] = 'P';
            }
        }

        //We assign Nexus places on the map
        this.map[0][0] = 'N';   this.map[7][0] = 'N';
        this.map[0][1] = 'N';   this.map[7][1] = 'N';
        this.map[0][3] = 'N';   this.map[7][3] = 'N';
        this.map[0][4] = 'N';   this.map[7][4] = 'N';
        this.map[0][6] = 'N';   this.map[7][6] = 'N';
        this.map[0][7] = 'N';   this.map[7][7] = 'N';

        //We assign Inaccessible places on the map
        for(int i=0; i<length; i++){
            this.map[i][2] = 'I';   this.map[i][5] = 'I';
        }
    }


    @Override
    public void showMap() {
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                System.out.print(map[i][j] + " - " + map[i][j] + " - " + map[i][j]);
                if(j!=length-1){
                    System.out.print("  ");
                }
            }
            System.out.println();
            for(int j=0; j<length; j++){
                if(map[i][j] == 'I'){
                    System.out.print("| X X X |");
                }else {
                    System.out.print("| ");

                    if(isThereHero(i, j) != null && !isThereHero(i, j).isDead()){
                        System.out.print("H" + (getHeroIndex(isThereHero(i, j)) + 1));
                    }else{
                        System.out.print("  ");
                    }

                    System.out.print(" ");

                    if(isThereMonster(i, j)  != null  && !isThereMonster(i, j).isDead()){
                        System.out.print("M" + (getMonsterIndex(isThereMonster(i, j)) + 1));
                    }else{
                        System.out.print("  ");
                    }
                    System.out.print(" |");
                }
                if(j!=length-1){
                    System.out.print("  ");
                }
            }
            System.out.println();
            for(int j=0; j<length; j++){
                System.out.print(map[i][j] + " - " + map[i][j] + " - " + map[i][j]);
                if(j!=length-1){
                    System.out.print("  ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    @Override
    public List<IHero> heroesNextMarket(){
        List<IHero> listHeroes = new ArrayList<>();
        for(int i=0; i<3; i++){
            if(isMarket(i)){
                listHeroes.add(this.heroList.get(i));
            }
        }
        return listHeroes;
    }

    @Override
    public boolean isMarket(int herosIndex){
        if(map[this.herosPlaces[herosIndex][0]][this.herosPlaces[herosIndex][1]] == 'N'){
            return true;
        }
        return false;
    }

    @Override
    public void whereCanMove(IHero hero) {
        int herosIndex = heroList.indexOf(hero);

        int heroX = herosPlaces[herosIndex][0];
        int heroY = herosPlaces[herosIndex][1];

        if(isThereMonster(heroX, heroY)==null && isThereHero(heroX-1, heroY)==null && heroX>0){
            System.out.println("Type N to move North.");
        }
        if(heroY>0 && isThereHero(heroX, heroY-1)==null && map[heroX][heroY-1]!='I'){
            System.out.println("Type W to move West.");
        }
        if(heroY<length-1 && isThereHero(heroX, heroY+1)==null && map[heroX][heroY+1]!='I'){
            System.out.println("Type E to move East.");
        }
        if(heroX<length-1 && isThereHero(heroX+1, heroY)==null){
            System.out.println("Type S to move South.");
        }
    }

    public void heroGetBonusOnSpace(IHero hero, int x, int y){
        if(map[x][y] == 'B'){
            hero.setDexterity(hero.getDexterity() * (1.1));
        }else if(map[x][y] == 'C'){
            hero.setAgility(hero.getAgility() * (1.1));
        }else if(map[x][y] == 'K'){
            hero.setStrength(hero.getStrength() * (1.1));
        }
    }

    public void heroRemoveBonusOnSpace(IHero hero, int x, int y){
        if(map[x][y] == 'B'){
            hero.setDexterity(hero.getDexterity() / (1.1));
        }else if(map[x][y] == 'C'){
            hero.setAgility(hero.getAgility() / (1.1));
        }else if(map[x][y] == 'K'){
            hero.setStrength(hero.getStrength() / (1.1));
        }
    }

    @Override
    public boolean move(char c, IHero hero) {
        int herosIndex = heroList.indexOf(hero);

        int heroX = herosPlaces[herosIndex][0];
        int heroY = herosPlaces[herosIndex][1];

        if(Character.toUpperCase(c)=='N' && isThereHero(heroX-1, heroY)==null && isThereMonster(heroX, heroY)==null && heroX>0){
            heroRemoveBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]); //This will remove the effect getted by hero on the current space.
            herosPlaces[herosIndex][0]--;
            heroGetBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]);  // Hero will receive bonus on new map place.
            return true;
        }
        if(Character.toUpperCase(c)=='W' && isThereHero(heroX, heroY-1)==null && heroY>0 && map[heroX][heroY-1]!='I'){
            heroRemoveBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]); //This will remove the effect getted by hero on the current space.
            herosPlaces[herosIndex][1]--;
            heroGetBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]);  // Hero will receive bonus on new map place.
            return true;
        }
        if(Character.toUpperCase(c)=='E' && isThereHero(heroX, heroY+1)==null && heroY<length-1 && map[heroX][heroY+1]!='I'){
            heroRemoveBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]); //This will remove the effect getted by hero on the current space.
            herosPlaces[herosIndex][1]++;
            heroGetBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]);  // Hero will receive bonus on new map place.
            return true;
        }
        if(Character.toUpperCase(c)=='S' && isThereHero(heroX+1, heroY)==null && heroX<length-1){
            heroRemoveBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]); //This will remove the effect getted by hero on the current space.
            herosPlaces[herosIndex][0]++;
            heroGetBonusOnSpace(hero, herosPlaces[herosIndex][0], herosPlaces[herosIndex][1]);  // Hero will receive bonus on new map place.
            return true;
        }

        return false;
    }

    @Override
    public void monsterMove(IMonster monster){
        int monsterIndex = monsterList.indexOf(monster);

        int monsterX = monsterPlaces[monsterIndex][0];
        int monsterY = monsterPlaces[monsterIndex][1];

        if(monsterX<length-1 && isThereMonster(monsterX-1, monsterY)==null){
            monsterPlaces[monsterIndex][0]++;
        }
    }

    public int getHeroIndex(IHero hero){
        return this.heroList.indexOf(hero);
    }

    public int getMonsterIndex(IMonster monster){
        return this.monsterList.indexOf(monster);
    }

    public IMonster isThereMonster(int x, int y){

        for(int i=0; i<monsterList.size(); i++){
            if(monsterPlaces[i][0] == x && monsterPlaces[i][1] == y){
                return monsterList.get(i);
            }
        }
        return null;
    }

    public IHero isThereHero(int x, int y){
        if(herosPlaces[0][0] == x && herosPlaces[0][1] == y){
            return heroList.get(0);
        }
        if(herosPlaces[1][0] == x && herosPlaces[1][1] == y){
            return heroList.get(1);
        }
        if(herosPlaces[2][0] == x && herosPlaces[2][1] == y){
            return heroList.get(2);
        }
        return null;
    }

    public void putHerosNextToNexus(IHero hero, char c){
        if(Character.toUpperCase(c)=='T'){
            herosPlaces[0][0] = 7;
            herosPlaces[0][1] = 0;
            heroList.set(0, hero);
        }else if(Character.toUpperCase(c)=='M'){
            herosPlaces[1][0] = 7;
            herosPlaces[1][1] = 3;
            heroList.set(1, hero);
        }else if(Character.toUpperCase(c)=='B'){
            herosPlaces[2][0] = 7;
            herosPlaces[2][1] = 6;
            heroList.set(2, hero);
        }
    }

    public void monsterTurn(IMonster monster){
        List<IHero> listHeroes = findNearestHeroes(monster);

        if(listHeroes.isEmpty()){
            monsterMove(monster);
        }else {
            int rand = ThreadLocalRandom.current().nextInt(0, listHeroes.size());   //Generating a random number between 0-X
            listHeroes.get(rand).receiveDamage(monster);
        }
    }

    public boolean heroRecall(IHero hero){
        int indexHero = heroList.indexOf(hero);

        if(indexHero == 0 && (herosPlaces[indexHero][0] != 7 || herosPlaces[indexHero][1] != 0)){
            herosPlaces[indexHero][0] = 7;
            herosPlaces[indexHero][1] = 0;
            System.out.println(hero.getName() + " is back to his nexus.");
            return true;
        }else if(indexHero == 1 && (herosPlaces[indexHero][0] != 7 || herosPlaces[indexHero][1] != 3)){
            herosPlaces[indexHero][0] = 7;
            herosPlaces[indexHero][1] = 3;
            System.out.println(hero.getName() + " is back to his nexus.");
            return true;
        }else if(indexHero == 2 && (herosPlaces[indexHero][0] != 7 || herosPlaces[indexHero][1] != 6)){
            herosPlaces[indexHero][0] = 7;
            herosPlaces[indexHero][1] = 6;
            System.out.println(hero.getName() + " is back to his nexus.");
            return true;
        }else {
            System.out.println(hero.getName() + " can't recall.");
            return false;
        }
    }

    public boolean heroTeleport(IHero hero, int indexTargetHero){
        int indexHero = heroList.indexOf(hero); //get the index of the hero to be teleported.

        if(indexHero != indexTargetHero && indexTargetHero>=0 && indexTargetHero<=3){
            IHero targetHero = heroList.get(indexTargetHero);
            int targetHeroX = herosPlaces[indexTargetHero][0];  // X of target hero
            int targetHeroY = herosPlaces[indexTargetHero][1];  // Y of target hero

            //Hero will be teleported to the left of target hero
            if(targetHeroY>0 && isThereHero(targetHeroX, targetHeroY-1)==null && map[targetHeroX][targetHeroY-1]!='I'){
                herosPlaces[indexHero][0] = targetHeroX;
                herosPlaces[indexHero][1] = targetHeroY-1;
                System.out.println(hero.getName() + " has been teleported next to " + targetHero.getName());
                return true;
            }

            //Hero will be teleported to the right of target hero
            if(targetHeroY<length-1 && isThereHero(targetHeroX, targetHeroY+1)==null && map[targetHeroX][targetHeroY+1]!='I'){
                herosPlaces[indexHero][0] = targetHeroX;
                herosPlaces[indexHero][1] = targetHeroY+1;
                System.out.println(hero.getName() + " has been teleported next to " + targetHero.getName());
                return true;
            }

            //Hero will be teleported to the bottom of target hero
            if(targetHeroX<length-1 && isThereHero(targetHeroX+1, targetHeroY)==null){
                herosPlaces[indexHero][0] = targetHeroX+1;
                herosPlaces[indexHero][1] = targetHeroY;
                System.out.println(hero.getName() + " has been teleported next to " + targetHero.getName());
                return true;
            }

        }
        return false;
    }

    public List<IMonster> findNearestMonsters(IHero hero){
        int heroIndex = heroList.indexOf(hero);
        int heroX = herosPlaces[heroIndex][0];
        int heroY = herosPlaces[heroIndex][1];

        List<IMonster> listOfNearestMonsters = new ArrayList<>();

        //If there is a monster in the same place of hero
        for(int i=0; i<monsterList.size(); i++){
            if(monsterPlaces[i][0]==heroX && monsterPlaces[i][1]==heroY){
                listOfNearestMonsters.add(monsterList.get(i));
            }
        }

        //If there is a monster in the left of hero
        if(heroY>0){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX && monsterPlaces[i][1]==heroY-1){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //If there is a monster in the right of hero
        if(heroY<length-1){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX && monsterPlaces[i][1]==heroY+1){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //If there is a monster in the top of hero
        if(heroX>0){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX-1 && monsterPlaces[i][1]==heroY){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //If there is a monster in the bottom of hero
        if(heroX<length-1){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX+1 && monsterPlaces[i][1]==heroY){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //If there is a monster in the top left of hero
        if(heroX>0 && heroY>0){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX-1 && monsterPlaces[i][1]==heroY-1){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //If there is a monster in the top right of hero
        if(heroX>0 && heroY<length-1){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX-1 && monsterPlaces[i][1]==heroY+1){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //If there is a monster in the bottom left of hero
        if(heroY>0 && heroX<length-1){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX+1 && monsterPlaces[i][1]==heroY-1){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //If there is a monster in the bottom right of hero
        if(heroX<length-1 && heroY<length-1){
            for(int i=0; i<monsterList.size(); i++){
                if(monsterPlaces[i][0]==heroX+1 && monsterPlaces[i][1]==heroY+1){
                    listOfNearestMonsters.add(monsterList.get(i));
                }
            }
        }

        //No monster is near the hero.
        return listOfNearestMonsters;
    }

    public List<IHero> findNearestHeroes(IMonster monster){
        int monsterIndex = monsterList.indexOf(monster);
        int monsterX = monsterPlaces[monsterIndex][0];
        int monsterY = monsterPlaces[monsterIndex][1];

        List<IHero> listOfNearestHeroes = new ArrayList<>();

        //If there is a monster in the same place of hero
        if(herosPlaces[0][0]==monsterX && herosPlaces[0][1]==monsterY){
            listOfNearestHeroes.add(heroList.get(0));
        }else if(herosPlaces[1][0]==monsterX && herosPlaces[1][1]==monsterY){
            listOfNearestHeroes.add(heroList.get(1));
        }else if(herosPlaces[2][0]==monsterX && herosPlaces[2][1]==monsterY){
            listOfNearestHeroes.add(heroList.get(2));
        }

        //If there is a monster in the left of hero
        if(monsterY>0){
            if(herosPlaces[0][0]==monsterX && herosPlaces[0][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX && herosPlaces[1][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX && herosPlaces[2][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //If there is a monster in the right of hero
        if(monsterY<length-1){
            if(herosPlaces[0][0]==monsterX && herosPlaces[0][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX && herosPlaces[1][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX && herosPlaces[2][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //If there is a monster in the top of hero
        if(monsterX>0){
            if(herosPlaces[0][0]==monsterX-1 && herosPlaces[0][1]==monsterY){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX-1 && herosPlaces[1][1]==monsterY){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX-1 && herosPlaces[2][1]==monsterY){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //If there is a monster in the bottom of hero
        if(monsterX<length-1){
            if(herosPlaces[0][0]==monsterX+1 && herosPlaces[0][1]==monsterY){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX+1 && herosPlaces[1][1]==monsterY){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX+1 && herosPlaces[2][1]==monsterY){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //If there is a monster in the top left of hero
        if(monsterX>0 && monsterY>0){
            if(herosPlaces[0][0]==monsterX-1 && herosPlaces[0][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX-1 && herosPlaces[1][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX-1 && herosPlaces[2][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //If there is a monster in the top right of hero
        if(monsterX>0 && monsterY<length-1){
            if(herosPlaces[0][0]==monsterX-1 && herosPlaces[0][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX-1 && herosPlaces[1][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX-1 && herosPlaces[2][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //If there is a monster in the bottom left of hero
        if(monsterY>0 && monsterX<length-1){
            if(herosPlaces[0][0]==monsterX+1 && herosPlaces[0][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX+1 && herosPlaces[1][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX+1 && herosPlaces[2][1]==monsterY-1){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //If there is a monster in the bottom right of hero
        if(monsterX<length-1 && monsterY<length-1){
            if(herosPlaces[0][0]==monsterX+1 && herosPlaces[0][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(0));
            }else if(herosPlaces[1][0]==monsterX+1 && herosPlaces[1][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(1));
            }else if(herosPlaces[2][0]==monsterX+1 && herosPlaces[2][1]==monsterY+1){
                listOfNearestHeroes.add(heroList.get(2));
            }
        }

        //No monster is near the hero.
        return listOfNearestHeroes;
    }

    public void putMonstersNextToNexus(IMonster monster, char c){
        if(monsterList.contains(null)){
            if(Character.toUpperCase(c)=='T'){
                monsterPlaces[0][0] = 0;
                monsterPlaces[0][1] = 1;
                monsterList.set(0, monster);
            }else if(Character.toUpperCase(c)=='M'){
                monsterPlaces[1][0] = 0;
                monsterPlaces[1][1] = 4;
                monsterList.set(1, monster);
            }else if(Character.toUpperCase(c)=='B'){
                monsterPlaces[2][0] = 0;
                monsterPlaces[2][1] = 7;
                monsterList.set(2, monster);
            }

        }else {
            int[][] newMonsterPlaces = Arrays.copyOf(monsterPlaces, monsterPlaces.length + 1);

            int newIndex = newMonsterPlaces.length - 1;

            if(Character.toUpperCase(c)=='T'){
                monsterPlaces[newIndex][0] = 0;
                monsterPlaces[newIndex][1] = 1;
            }else if(Character.toUpperCase(c)=='M'){
                monsterPlaces[newIndex][0] = 0;
                monsterPlaces[newIndex][1] = 4;
            }else if(Character.toUpperCase(c)=='B'){
                monsterPlaces[newIndex][0] = 0;
                monsterPlaces[newIndex][1] = 7;
            }

            monsterList.add(monster);

            monsterPlaces = newMonsterPlaces;
        }

    }

    public boolean heroInMonstersNexus(IHero hero){
        int indexHero = heroList.indexOf(hero);
        int heroX = herosPlaces[indexHero][0];
        int heroY = herosPlaces[indexHero][1];

        if(heroX==0){
            if(heroY==0 || heroY==1 || heroY==3 || heroY==4 || heroY==6 || heroY==7){
                System.out.println(hero.getName() + " has arrived to monsters nexus, heroes won.");
                return true;
            }
        }

        return false;
    }

    public boolean monsterInHeroesNexus(IMonster monster){
        int indexMonster = monsterList.indexOf(monster);
        int monsterX = herosPlaces[indexMonster][0];
        int monsterY = herosPlaces[indexMonster][1];

        if(monsterX==0){
            if(monsterY==0 || monsterY==1 || monsterY==3 || monsterY==4 || monsterY==6 || monsterY==7){
                System.out.println(monster.getName() + " has arrived to heroes nexus, monsters won.");
                return true;
            }
        }

        return false;
    }

    public List<IHero> getHeroList() {
        return heroList;
    }

    public List<IMonster> getMonsterList() {
        return monsterList;
    }
}
