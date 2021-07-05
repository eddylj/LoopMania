package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;
import unsw.loopmania.Character;
import unsw.loopmania.Sword;
import unsw.loopmania.Equipment;

public class InventoryTest {
    @Test
    public void PickUpDifferentItemsTest() {
        Character c = new Character();
        Equipment sword = new Sword(1);
        Equipment armour = new Armour(2);
        Equipment helmet = new Helmet(3);
        Equipment shield = new Shield(4);
        Equipment potion = new Potion();
        Equipment oneRing = new OneRing(6);
        Equipment stake = new Stake(7);
        Equipment staff = new Staff(8);
        Equipment sword2 = new Sword(9);
        Equipment helmet2 = new Helmet(9);
        Equipment potion2 = new Potion();
        Equipment sword3 = new Sword(1);
        assertEquals(0, c.numEquipmentInInventory());
        c.pickUp(sword);
        assertEquals(1, c.numEquipmentInInventory());
        c.pickUp(helmet);
        c.pickUp(armour);
        c.pickUp(shield);
        c.pickUp(potion);
        assertEquals(5, c.numEquipmentInInventory());
        c.pickUp(oneRing);
        c.pickUp(stake);
        c.pickUp(staff);
        c.pickUp(sword2);
        c.pickUp(helmet2);
        c.pickUp(potion2);
        c.pickUp(sword3);
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
}
