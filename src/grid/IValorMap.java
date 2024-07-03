package grid;

import heroes.IHero;
import monster.IMonster;

import java.util.List;

public interface IValorMap {

    public void showMap();

    public List<IHero> heroesNextMarket();
    public boolean isMarket(int heroIndex);

    public void whereCanMove(IHero hero);

    public List<IMonster> findNearestMonsters(IHero hero);

    public boolean move(char c, IHero hero);

    public IMonster isThereMonster(int x, int y);

    public IHero isThereHero(int x, int y);

    public void putHerosNextToNexus(IHero hero, char c);

    public void putMonstersNextToNexus(IMonster monster, char c);

    public void monsterMove(IMonster monster);

    public void monsterTurn(IMonster monster);

    public boolean heroInMonstersNexus(IHero hero);

    public boolean monsterInHeroesNexus(IMonster monster);

    public boolean heroTeleport(IHero hero, int indexTargetHero);

    public boolean heroRecall(IHero hero);

    public List<IHero> getHeroList();

    public List<IMonster> getMonsterList();
}
