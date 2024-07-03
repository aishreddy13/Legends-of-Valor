package market;

import heroes.IHero;
import item.IItem;

import java.util.List;

public interface IMarket {

    //Methodes
    public void heroSellItem(IItem item, IHero hero);

    public void heroBuyItem(IItem item, IHero hero);

    public IItem findItemByName(String nameItem);

    public void addItem(IItem item);

    public void removeItem(IItem item);

    //Getters and Setters
    public List<IItem> getItems();
    public void setItems(List<IItem> items);

}
