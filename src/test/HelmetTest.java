package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Helmet;

public class HelmetTest {

    @Test
    public void HelmetLevelStoredTest() {
        Character c = new Character();
        Item helmet = new Helmet(3);

        assertEquals(1, c.getHighestLevel(helmet));
        c.pickup(helmet);
        c.equip(helmet);
        assertEquals(3, c.getHighestLevel(helmet));
    }

    @Test
    public void HelmetTakeDamageTest() {
        Character c = new Character();
        Item helmet = new Helmet(1);

        c.pickup(helmet);
        c.equip(helmet);
        assertEquals(100, c.getHealth());
        c.takeDamage(10);
        assertEquals(93, c.getHealth());
    }

    @Test
    public void HelmetTakeDamageLevelTest() {
        Character c = new Character();
        Item helmet = new Helmet(2);

        c.pickup(helmet);
        c.equip(helmet);
        assertEquals(100, c.getHealth());
        c.takeDamage(10);
        assertEquals(94, c.getHealth());
    }

    @Test
    public void SellHelmetTest() {
        Character c = new Character();
        Item helmet = new Helmet(1);

        c.pickup(helmet);
        
        c.sellItem(helmet);
        assertEquals(400, c.getGold());
    }
}
