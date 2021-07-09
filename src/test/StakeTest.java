package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.Sword;
import unsw.loopmania.Stake;
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;


public class StakeTest {

    @Test
    public void StakeLevelStoredTest() {
        Character c = new Character();
        Item sword = new Sword(10);
        Item stake = new Stake(3);

        assertEquals(1, c.getHighestLevel(stake));
        c.unequip(sword);
        c.pickup(stake);
        c.equip(stake);
        assertEquals(3, c.getHighestLevel(stake));
    }

    @Test
    public void StakeSaleTest() {
        Character c = new Character();
        Item stake = new Stake(1);

        c.pickup(stake);
        c.sellItem(stake);
        assertEquals(140, c.getGold());
    }

    @Test
    public void StakeDamage() {
        Character c = new Character();
        Item sword = new Sword(10);
        Item stake = new Stake(1);

        c.unequip(sword);
        c.pickup(stake);
        Enemy slug = new Slug();
        c.attack(slug);
        assertEquals(slug.getHealth(), 40);
        Enemy vampire = new Vampire();
        c.attack(vampire);
        assertEquals(100, vampire.getHealth());
    }
}
