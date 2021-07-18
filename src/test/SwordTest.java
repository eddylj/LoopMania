package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.BattleRunner;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Sword;
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;


public class SwordTest {
    private BattleRunner b = new BattleRunner();
    @Test
    public void StartWithSwordAndUnequipTest() {
        Character c = new Character();
        Item sword = new Sword(10);
        assertEquals(1, c.getHighestLevel(sword));
        c.equip(sword);
        assertEquals(10, c.getHighestLevel(sword));
    }

    @Test
    public void EquipLowerLevelSword() {
        Character c = new Character();
        Item sword1 = new Sword(10);
        Item sword2 = new Sword(3);
        assertEquals(1, c.getHighestLevel(sword1));
        c.equip(sword1);
        assertEquals(10, c.getHighestLevel(sword1));
        c.equip(sword2);
        assertEquals(10, c.getHighestLevel(sword2));
    }

    @Test
    public void compareDamages() {
        Weapon sword = new Sword(1);
        Double damage = sword.getDamage();
        for (int i = 2; i <= 10; i++) {
            Weapon nextSword = new Sword(i);
            assertEquals(nextSword.getDamage(), damage * 1.1);
            damage = nextSword.getDamage();
        }
    }

    @Test
    public void whackSomethingWithSwordTest() {
        Character c = new Character();
        Item sword = new Sword(1);

        c.equip(sword);
        Enemy slug = new Slug();
        assertEquals(slug.getHealth(), 50);
        c.attack(slug, b);
        assertEquals(slug.getHealth(), 15);
    }
} 
