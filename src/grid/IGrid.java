package grid;

import heroes.IHero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface IGrid {

    public void showMap();

    public boolean isMarket();

    public void whereCanMove();

    public boolean move(char c, List<IHero> listOfHeroes);


}