package item;

public abstract class Item implements IItem {

    //Attributes
    String name;
    double price;
    int level;

    //Constructors
    public Item(String name, double price, int level) {
        this.name = name;
        this.price = price;
        this.level = level;
    }


    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}