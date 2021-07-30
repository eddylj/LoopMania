package test;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        potionType.use(c);
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
        potionType.use(c);
        assertEquals(100, c.getHealth());
    }

}
