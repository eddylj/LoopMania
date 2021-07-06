package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SwordTest {
    @Test
    public void StartWithSwordAndUnequipTest() {
        Character c = new Character();
        Equipment sword = new Sword(10);
        assertEquals(1, c.getHighestLevel(sword));
        assertEquals(1, c.getNumEquipped());
        c.unequip(sword);
        assertEquals(1, c.getHighestLevel(sword));
        assertEquals(0, c.getNumEquipped());
    }

    @Test
    public void EquipLowerLevelSword() {
        List<Equipment> equipment = new ArrayList<Equipment>();
        Sword sword1 = new Sword(1);
        equipment.add(sword1);
        Character c = new Character(equipment);
        assertEquals(1, c.getHighestLevel(sword1));
        assertEquals(1, c.getNumStored());
        Sword sword2 = new Sword(2);
        c.pickUp(sword2);
        assertEquals(1, c.getHighestLevel(sword1));
        assertEquals(2, c.getNumStored());
        c.equip(sword2);
        c.equip(sword1);
        assertEquals(sword1, c.getEquippedWeapon());
    }

    @Test
    public void compareDamages() {
        Equipment sword = new Sword(1);
        int damage = sword.getDamage();
        for (int i = 2; i <= 10; i++) {
            Equipment nextSword = new Sword(i);
            assertTrue(nextSword.getDamage() = Math.floor(damage * 1.1 + 0.5));
            damage = nextSword.getDamage();
        }
    }

    @Test
    public void whackSomethingWithSwordTest() {
        Equipment sword = new Sword(10);
        List<Equipment> equipment = new ArrayList<Equipment>();
        equipment.add(sword);
        Enemy slug = new Slug();
        List<Enemy> enemies = new ArrayList<Enemy>();
        Character c = new Character(slug, enemies, equipment);
        c.fight();
        assertEquals(100, c.getHealth());
        assertFalse(slug.shouldExist().get());
    }
} 
