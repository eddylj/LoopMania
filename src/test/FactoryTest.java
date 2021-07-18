package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.javatuples.Pair;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.Building;
import unsw.loopmania.BuildingFactory;
import unsw.loopmania.CampfireBuilding;
import unsw.loopmania.Card;
import unsw.loopmania.CardFactory;
import unsw.loopmania.Enemy;
import unsw.loopmania.EnemyFactory;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.TowerBuilding;
import unsw.loopmania.TrapBuilding;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.VillageBuilding;
import unsw.loopmania.ZombiePitBuilding;
import unsw.loopmania.itemFactory;

public class FactoryTest {
    private SimpleIntegerProperty x;
    private SimpleIntegerProperty y;

    public FactoryTest() {
        x = new SimpleIntegerProperty();
        y = new SimpleIntegerProperty();
        x.set(0);
        y.set(0);
    }

    private List<Pair<Integer, Integer>> createPath() {
        List<Pair<Integer, Integer>> l = new ArrayList<Pair<Integer, Integer>>();
        l.add(new Pair<Integer, Integer>(0, 0));
        l.add(new Pair<Integer, Integer>(0, 1));
        l.add(new Pair<Integer, Integer>(0, 2));
        l.add(new Pair<Integer, Integer>(1, 2));
        l.add(new Pair<Integer, Integer>(2, 2));
        l.add(new Pair<Integer, Integer>(2, 1));
        l.add(new Pair<Integer, Integer>(2, 0));
        l.add(new Pair<Integer, Integer>(1, 0));
        return l;
    }

    @ParameterizedTest
    @ValueSource(strings = {"sword", "helmet", "armour", "shield", "stake", "staff"})
    public void itemFactoryLevelTest(String input) {
        itemFactory f = new itemFactory();
        Random rand = new Random();
        int level = (LoopManiaWorld.getRandNum() % 10) + 1;
        Item item1 = f.create(x, y, input, level);
        Item item2 = f.create(x, y, input, level);
        assertNotEquals(item1, item2);
    }

    @Test
    public void itemFactoryRegularTest() {
        itemFactory f = new itemFactory();
        Item potion = f.create(x, y, "healthpotion");
        Item potion2 = f.create(x, y, "healthpotion");
        assertNotEquals(potion, potion2);
    }

    @Test
    public void EnemyFactoryTest() {
        EnemyFactory e = new EnemyFactory();
        Enemy slug = e.create(new PathPosition(0, createPath()), "slug");
        System.err.println(slug.getHealth());
        assertEquals(slug.getHealth(), 50);
        Enemy vampire = e.create(new PathPosition(1, createPath()), "vampire");
        assertEquals(vampire.getHealth(), 150);
        Enemy zombie = e.create(new PathPosition(2, createPath()), "zombie");
        assertEquals(zombie.getHealth(), 100);
    }

    @ParameterizedTest
    @ValueSource(strings = {"campfire", "barracks", "tower", "trap", "vampirecastle", "zombiepit", "village"})
    public void CardFactoryTest(String input) {
        CardFactory c = new CardFactory();
        Card card = c.create(x, y, input);
        String cardClass = card.getClass().getSimpleName();
        assertEquals(String.format("%sCard",input), cardClass);
    }

    
    BuildingFactory b = new BuildingFactory();
    @Test
    public void createCampfireTest() {
        Building i = b.create(x, y, "campfire");
        assertTrue(i instanceof CampfireBuilding);
    }
    @Test
    public void createbarracksTest() {
        Building i = b.create(x, y, "barracks");
        assertTrue(i instanceof BarracksBuilding);
    }
    @Test
    public void createtowerTest() {
        Building i = b.create(x, y, "tower");
        assertTrue(i instanceof TowerBuilding);
    }
    @Test
    public void createtrapTest() {
        Building i = b.create(x, y, "trap");
        assertTrue(i instanceof TrapBuilding);
    }
    @Test
    public void createvampirecastleTest() {
        Building i = b.create(x, y, "vampirecastle");
        assertTrue(i instanceof VampireCastleBuilding);
    }
    @Test
    public void createzombiepitTest() {
        Building i = b.create(x, y, "zombiepit");
        assertTrue(i instanceof ZombiePitBuilding);
    }
    @Test
    public void createvillageTest() {
        Building i = b.create(x, y, "village");
        assertTrue(i instanceof VillageBuilding);
    }
}
