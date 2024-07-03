package market;

import heroes.IHero;
import infos.ConsoleColors;
import item.IItem;
import java.util.ArrayList;
import java.util.List;

public class Market implements IMarket {

    //Attributes
    List<IItem> items = new ArrayList<>();

    //Constructors
    public Market() {

    }

    //Methodes
    public void heroSellItem(IItem item, IHero hero){
        //this.addItem(item);
        hero.removeItem(item);
        hero.setGold(hero.getGold() + (item.getPrice())/2);
        System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + hero.getName() + " has sold " + item.getName() + " for " + ((item.getPrice())/2) + ConsoleColors.ANSI_RESET);
    }

    public void heroBuyItem(IItem item, IHero hero){
        if(hero.getGold() >= item.getPrice()){
            //this.removeItem(item);
            hero.addItem(item);
            hero.setGold(hero.getGold() - item.getPrice());
            System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + "Hero " + hero.getName() + " has bought " + item.getName() + " for " + item.getPrice() + ConsoleColors.ANSI_RESET);
        }else{
            System.out.println(ConsoleColors.ANSI_YELLOW + "Hero " + hero.getName() + " can't buy " + item.getName() + ConsoleColors.ANSI_RESET);
        }
    }

    public IItem findItemByName(String nameItem){
        for (IItem item : this.getItems()){
            if(item.getName().toLowerCase().equals(nameItem.toLowerCase())){
                return item;
            }
        }
        return null;
    }

    public void addItem(IItem item){
        this.getItems().add(item);
    }

    public void removeItem(IItem item){
        this.getItems().remove(item);
    }

    //Getters and Setters
    public List<IItem> getItems() {
        return items;
    }
    public void setItems(List<IItem> items) {
        this.items = items;
    }
}