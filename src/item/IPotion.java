package item;

import heroes.IHero;

import java.util.List;

public interface IPotion extends IItem {

    //Abstract methods
    public boolean usePotionByHero(IHero hero);

    //Getters and Setters
    public double getEffectAmount();
    public void setEffectAmount(double effectAmount);
    public List<String> getAttributeAffected();
    public void setAttributeAffected(List<String> attributeAffected);

}
