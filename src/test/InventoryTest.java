package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Sword;
import unsw.loopmania.Armour;
import unsw.loopmania.Helmet;
import unsw.loopmania.Shield;
import unsw.loopmania.HealthPotion;

// import unsw.loopmania.OneRing;
import unsw.loopmania.Stake;
import unsw.loopmania.Staff;

public class InventoryTest {
    @Test
    public void PickUpDifferentItemsTest() {
        Character c = new Character();
        Item sword = new Sword(1);
        Item armour = new Armour(2);
        Item helmet = new Helmet(3);
        Item shield = new Shield(4);
        Item potion = new HealthPotion();
        Item healthpotion = new HealthPotion();
        // Item oneRing = new OneRing(6);
        Item stake = new Stake(7);
        Item staff = new Staff(8);
        Item sword2 = new Sword(9);
        Item helmet2 = new Helmet(9);
        Item potion2 = new HealthPotion();
        Item sword3 = new Sword(1);
        assertEquals(0, c.numEquipmentInInventory());
        c.pickup(sword);
        assertEquals(1, c.numEquipmentInInventory());
        c.pickup(helmet);
        c.pickup(armour);
        c.pickup(shield);
        c.pickup(potion);
        assertEquals(5, c.numEquipmentInInventory());
        c.pickup(healthpotion);
        c.pickup(stake);
        c.pickup(staff);
        c.pickup(sword2);
        c.pickup(helmet2);
        c.pickup(potion2);
        c.pickup(sword3);
        assertEquals(12, c.numEquipmentInInventory());
    }
    @Test
    public void PickUpMaxItemsTest() {
        Character c = new Character();
        for (int i = 0; i < 15; i++) {
            c.equip(new Sword(1));
        }
        assertEquals(15, c.numEquipmentInInventory());
        c.equip(new Sword(1));
        assertEquals(15, c.numEquipmentInInventory());
    }

    @Test
    public void EquipItemTest() {
        Character c = new Character();
        Item sword = new Sword(2);
        assertEquals(0, c.numEquipmentInInventory());
        c.pickup(sword);
        assertEquals(1, c.numEquipmentInInventory());
        c.equip(sword);
        assertEquals(0, c.numEquipmentInInventory());

    }
}
