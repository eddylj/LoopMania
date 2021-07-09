package test;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Sword;

public class SellTest {
    
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
