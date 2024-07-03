package item;

import heroes.IHero;
import monster.IMonster;

public interface ISpell extends IItem {

    //Methods
    public abstract boolean useSpellOnMonsterByHero(IHero hero, IMonster monster);

}
