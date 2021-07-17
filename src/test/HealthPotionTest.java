package test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.HealthPotion;

public class HealthPotionTest {

    @Test
    public void PotionRestoreDamageTest() {
        Character c = new Character();
        Item potion = new HealthPotion();

        assertEquals(100, c.getHealth());
        c.takeDamage(10);
        assertEquals(90, c.getHealth());
        HealthPotion potionType = (HealthPotion)potion;
        potionType.heal(c);
        assertEquals(100, c.getHealth());
    }

    @Test
    public void PotionRestoreMoreDamageTest() {
        Character c = new Character();
        Item potion = new HealthPotion();


        assertEquals(100, c.getHealth());
        c.takeDamage(20);
        assertEquals(80, c.getHealth());
        HealthPotion potionType = (HealthPotion)potion;
        potionType.heal(c);
        assertEquals(100, c.getHealth());
    }

}
