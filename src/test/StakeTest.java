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
        Item stake = new Stake(3);

        assertEquals(1, c.getHighestLevel(stake));
        c.equip(stake);
        assertEquals(3, c.getHighestLevel(stake));
    }

    @Test
    public void StakeSaleTest() {
        Character c = new Character();
        Item stake = new Stake(1);

        c.sellItem(stake);
        assertEquals(140, c.getGold());
    }

    @Test
    public void StakeDamage() {
        Character c = new Character();
        Item sword = new Sword(10);
        Item stake = new Stake(1);

        Enemy slug = new Slug();
        assertEquals(slug.getHealth(), 50);
        c.attack(slug);
        assertEquals(slug.getHealth(), 30);
        Enemy vampire = new Vampire();
        assertEquals(150, vampire.getHealth());
        c.attack(vampire);
        assertEquals(100, vampire.getHealth());
    }
}
