package test;

import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;
import unsw.loopmania.Sword;
import unsw.loopmania.Helmet;
import unsw.loopmania.Armour;
import unsw.loopmania.Shield;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FightingTest {
    @Test
    public void fightSingleSlugTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug = new Slug();
        Character c = new Character(slug, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        assertEquals(80, c.getHealth());
        assertFalse(slug.shouldExist().get());
    }

    @Test
    public void fightSingleZombieTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy zombie = new Zombie();
        Character c = new Character(zombie, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        assertEquals(46, c.getHealth());
        assertFalse(zombie.shouldExist().get());
    }

    @Test
    public void fightSingleVampireTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy vampire = new Vampire();
        Character c = new Character(vampire, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        assertTrue(c.getHealth() <= 46);
        assertFalse(vampire.shouldExist().get());
    }

    @Test
    public void fightTwoSlugsTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug1 = new Slug();
        Enemy slug2 = new Slug();
        enemies.add(slug2);
        Character c = new Character(slug1, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        AssertEquals(50, c.getHealth());
        assertFalse(slug1.shouldExist().get());
        assertFalse(slug2.shouldExist().get());
    }

    @Test
    public void fightTwoZombiesTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy zombie1 = new Zombie();
        Enemy zombie2 = new Zombie();
        enemies.add(zombie2);
        Character c = new Character(zombie1, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        AssertEquals(0, c.getHealth());
        assertFalse(zombie1.shouldExist().get());
        assertTrue(zombie2.shouldExist().get());
        assertEquals(100, zombie2.getHealth());
    }

    @Test
    public void fightTwoZombiesWithBetterSwordTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        List<Item> equipment = new ArrayList<Item>;
        Enemy zombie1 = new Zombie();
        Enemy zombie2 = new Zombie();
        Item sword = new Sword(10);
        equipment.add(sword);
        enemies.add(zombie2);
        Character c = new Character(zombie1, enemies, equipment);
        c.fight();
        assertEquals(46, c.getHealth());
        assertFalse(zombie1.shouldExist().get());
        assertFalse(zombie2.shouldExist().get());
    }

    @Test
    public void fightTwoVampiresTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy vampire1 = new Vampire();
        Enemy vampire2 = new Vampire();
        enemies.add(vampire2);
        Character c = new Character(vampire1, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        AssertEquals(0, c.getHealth());
    }

    @Test
    public void fightTwoZombiesWithMaxGearTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        List<Item> equipment = new ArrayList<Item>;
        Enemy vampire1 = new Vampire();
        Enemy vampire2 = new Vampire2();
        Item sword = new Sword(10);
        Item helmet = new Helmet(10);
        Item armour = new Armour(10);
        Item shield = new Shield(10);
        equipment.add(sword);
        equipment.add(helmet);
        equipment.add(armour);
        equipment.add(shield);
        enemies.add(vampire2);
        Character c = new Character(vampire1, enemies, equipment);
        c.fight();
        assertTrue(c.getHealth() >= 25); // NOTE: 75 damage is max 2 vampires can do against full gear (100% critical attacks)
        assertFalse(vampire1.shouldExist().get());
        assertFalse(vampire2.shouldExist().get());
    }

    @Test
    public void fightLotsOfEnemiesTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug1 = new Slug();
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                enemies.add(new Slug());
            }
            else if (i % 3 == 1) {
                enemies.add(new Vampire());
            }
            else {
                enemies.add(new Zombie());
            }
        }
        Character c = new Character(slug1, enemies);
        c.fight();
        assertEquals(0, c.getHealth());
    }

    @Test
    public void fightNoEnemiesTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Character c = new Character(null, enemies);
        c.fight();
        assertEquals(100, c.getHealth());
    }

    @Test
    public void fightThreeSlugsTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug1 = new Slug();
        Enemy slug2 = new Slug();
        enemies.add(slug2);
        Enemy slug3 = new Slug();
        enemies.add(slug3);
        Character c = new Character(slug1, enemies);
        c.fight();
        assertEquals(0, c.getHealth());
        assertFalse(slug2.shouldExist().get());
        assertFalse(slug3.shouldExist().get());
        assertEquals(15, slug1.getHealth());
    }

    @Test
    public void fightThreeSlugsWithHelmet() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        List<Equipment> equipment = new ArrayList<Equipment>();
        equipment.add(new Helmet(1));
        Enemy slug1 = new Slug();
        Enemy slug2 = new Slug();
        enemies.add(slug2);
        Enemy slug3 = new Slug();
        enemies.add(slug3);
        Character c = new Character(slug1, enemies, equipment);
        c.fight();
        assertEquals(30, c.getHealth());
        assertFalse(slug1.shouldExist().get());
        assertFalse(slug2.shouldExist().get());
        assertFalse(slug3.shouldExist().get());
    }
}
