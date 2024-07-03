package grid;

import heroes.IHero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid implements IGrid{

    //Attributs
    int length;
    char[][] map;
    int placeX;
    int placeY;

    public Grid(int length){
        this.length = length;
        this.map = new char[length][length];

        int nbInaccessibleSpaces = (int) (length*length*0.2);
        int nbMarketSpaces = (int) (length*length*0.3) + nbInaccessibleSpaces;

        //We will generate random number
        List<Integer> listOfPlaces = new ArrayList<>();
        for(int i=1; i<length*length; i++){
            listOfPlaces.add(i);
        }
        Collections.shuffle(listOfPlaces);
        listOfPlaces.add(0);

        boolean generatedRandomPlace = false; //To check if random place is generated or not.

        //We assign random places for the map
        for(int i=0; i<length*length; i++){
            int x = listOfPlaces.get(i);
            int y = 0;
            if(x>=length){
                while (x>=length){
                    x -= length;
                    y++;
                }
            }
            if(i<=nbInaccessibleSpaces){
                this.map[x][y] = '#';
            }else if(i<=nbMarketSpaces){
                this.map[x][y] = 'M';
            }else{
                this.map[x][y] = ' ';
            }
        }

        //Creating the party place.
        this.map[0][0] = ' ';
    }

    public void showMap(){
        for(int i=0; i<=this.length*4; i++){
            System.out.print("-");
        }
        System.out.println();
        for(int i=0; i<this.length; i++){
            for(int j=0; j<this.length; j++){
                if(i==this.placeX && j==this.placeY){
                    System.out.print("| " + "H" + " ");
                    if(j == this.length-1){
                        System.out.print("|");
                    }
                }else{
                    System.out.print("| " + map[i][j] + " ");
                    if(j == this.length-1){
                        System.out.print("|");
                    }
                }
            }
            System.out.println();
        }
        for(int i=0; i<=this.length*4; i++){
            System.out.print("-");
        }
    }

    public boolean isMarket(){
        if(map[this.placeX][this.placeY] == 'M'){
            return true;
        }
        return false;
    }

    public void whereCanMove(){
        if(placeX!=0 && map[placeX-1][placeY]!='#'){    //Heroes can move up
            System.out.println("Type W to move up.");
        }
        if(placeY!=0 && map[placeX][placeY-1]!='#'){    //Heroes can move left
            System.out.println("Type A to move left.");
        }
        if(placeX!=(length-1) && map[placeX+1][placeY]!='#'){   //Heroes can move down
            System.out.println("Type S to move down.");
        }
        if(placeY!=(length-1) && map[placeX][placeY+1]!='#'){   //Heroes can move right
            System.out.println("Type D to move right.");
        }
    }

    public boolean move(char c, List<IHero> listOfHeroes){
        //To be used to check if heroes moved.
        int XBefore = this.placeX;
        int YBefore = this.placeY;

        if(c=='a' || c=='A'){
            if(placeY!=0 && map[placeX][placeY-1]!='#'){
                this.placeY--;
            }else{
                System.out.println("You can't move left.");
            }
        }

        if(c=='d' || c=='D'){
            if(placeY!=(length-1) && map[placeX][placeY+1]!='#'){
                this.placeY++;
            }else{
                System.out.println("You can't move Right.");
            }
        }

        if(c=='w' || c=='W'){
            if(placeX!=0 && map[placeX-1][placeY]!='#'){
                this.placeX--;
            }else{
                System.out.println("You can't move Up.");
            }
        }

        if(c=='s' || c=='S'){
            if(placeX!=(length-1) && map[placeX+1][placeY]!='#'){
                this.placeX++;
            }else{
                System.out.println("You can't move Down.");
            }
        }

        if(XBefore!=this.placeX || YBefore!=this.placeY){ //If heroes moved to another place.
            //Enter battle if common place, chance of 50%
            if(this.map[this.placeX][this.placeY] == ' '){
                int rand = (int)(Math.random() * 10) + 1;   //Generating a random number between 1-10
                if(rand %2 ==0){    //if number is even, enter a battle
                    return true;
                }
            }
        }
        return false;
    }

}
