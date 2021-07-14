package test;

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
import unsw.loopmania.Card;
import unsw.loopmania.CardFactory;
import unsw.loopmania.Enemy;
import unsw.loopmania.EnemyFactory;
import unsw.loopmania.Item;
import unsw.loopmania.PathPosition;
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
    @ValueSource(strings = {"Sword", "Helmet", "Armour", "Shield", "Stake", "Staff"})
    public void itemFactoryLevelTest(String input) {
        itemFactory f = new itemFactory();
        Random rand = new Random();
        int level = rand.nextInt(9) + 1;
        Item item1 = f.create(x, y, input, level);
        Item item2 = f.create(x, y, input, level);
        assertNotEquals(item1, item2);
    }

    @Test
    public void itemFactoryRegularTest() {
        itemFactory f = new itemFactory();
        Item potion = f.create(x, y, "Potion");
        Item potion2 = f.create(x, y, "Potion");
        assertNotEquals(potion, potion2);
    }

    @Test
    public void EnemyFactoryTest() {
        EnemyFactory e = new EnemyFactory();
        Enemy slug = e.create(new PathPosition(0, createPath()), "Slug");
        System.err.println(slug.getHealth());
        assertEquals(slug.getHealth(), 50);
        Enemy vampire = e.create(new PathPosition(1, createPath()), "Vampire");
        assertEquals(vampire.getHealth(), 150);
        Enemy zombie = e.create(new PathPosition(2, createPath()), "Zombie");
        assertEquals(zombie.getHealth(), 100);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Campfire", "Barracks", "Tower", "Trap", "VampireCastle", "ZombiePit", "Village"})
    public void CardFactoryTest(String input) {
        CardFactory c = new CardFactory();
        Card card = c.create(x, y, input);
        String cardClass = card.getClass().getSimpleName();
        assertEquals(String.format("%sCard",input), cardClass);
    }
}