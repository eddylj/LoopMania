package test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Potion;

public class PotionTest {

    @Test
    public void PotionRestoreDamageTest() {
        Character c = new Character();
        Item potion = new Potion();

        c.pickup(potion);

        assertEquals(100, c.getHealth());
        c.takeDamage(10);
        assertEquals(90, c.getHealth());
        //Potion potionType = potion;
        //potionType.heal(c);
        assertEquals(94, c.getHealth());
    }

    @Test
    public void PotionRestoreMoreDamageTest() {
        Character c = new Character();
        Item potion = new Potion();

        c.pickup(potion);

        assertEquals(100, c.getHealth());
        c.takeDamage(20);
        assertEquals(90, c.getHealth());
        //Potion potionType = potion;
        //potionType.heal(c);
        assertEquals(88, c.getHealth());
    }

    @Test
    public void SellHelmetTest() {
        Character c = new Character();
        Item potion = new Potion();

        c.pickup(potion);
        assertEquals(0, c.getGold());
        
        c.sellItem(potion);
        assertEquals(100, c.getGold());
    }
}
