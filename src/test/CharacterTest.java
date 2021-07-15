package test;

import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CharacterTest {
    @Test
    public void CharacterExistsTest() {
        Character c = new Character();
        assertTrue(c.shouldExist().get());
    }

    @Test
    public void CharacterStartStatsTest() {
        Character c = new Character();
        assertEquals(100, c.getHealth());
        assertEquals(0, c.getGold());
        assertEquals(0, c.getXP());
    }

    @Test
    public void CharacterDieTest() {
        Enemy vampire1 = new Vampire();
        Enemy vampire2 = new Vampire();
        Enemy vampire3 = new Vampire();
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(vampire2);
        enemies.add(vampire3);
        Character c = new Character(vampire1, enemies);
        c.fight();
        assertFalse(c.shouldExist().get());
        assertEquals(0, c.getHealth());
    }

    @Test
    public void SellRemovesItemFromInventory() {
        Character c = new Character();
        Item sword = new Sword(1);
        assertEquals(0, c.numEquipmentInInventory());
        c.pickup(sword);
        assertEquals(1, c.numEquipmentInInventory());
        c.sellItem(sword);
        assertEquals(0, c.numEquipmentInInventory());

    }

    @Test
    public void SellGainGold() {
        Character c = new Character();
        Item sword = new Sword(1);
        c.pickup(sword);
        c.sellItem(sword);
        assertEquals(140, c.getGold());
    }

    @Test
    public void SellLevelGoldValue() {
        Character c = new Character();
        Item sword = new Sword(5);
        c.pickup(sword);
        c.sellItem(sword);
        assertEquals(224, c.getGold());

    }
}
