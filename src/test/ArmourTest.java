package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Armour;

public class ArmourTest {

    @Test
    public void ArmourLevelStoredTest() {
        Character c = new Character();
        Item armour = new Armour(3);

        assertEquals(1, c.getHighestLevel(armour));
        c.pickup(armour);
        c.equip(armour);
        assertEquals(3, c.getHighestLevel(armour));
    }

    @Test
    public void HelmetTakeDamageTest() {
        Character c = new Character();
        Item armour = new Armour(1);

        c.pickup(armour);
        c.equip(armour);
        assertEquals(100, c.getHealth());
        c.takeDamage(10);
        assertEquals(94, c.getHealth());
    }

    @Test
    public void HelmetTakeDamageLevelTest() {
        Character c = new Character();
        Item armour = new Armour(3);

        c.pickup(armour);
        c.equip(armour);
        assertEquals(100, c.getHealth());
        c.takeDamage(50);
        assertEquals(77, c.getHealth());
    }

    @Test
    public void SellHelmetTest() {
        Character c = new Character();
        Item armour = new Armour(1);

        c.pickup(armour);
        
        c.sellItem(armour);
        assertEquals(400, c.getGold());
    }
}
