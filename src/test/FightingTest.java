package test;

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
    public void fightSingleZombieTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy zombie = new Zombie();
        Character c = new Character(zombie, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        assertEquals(46, c.getHealth());
        assertFalse(zombie.shouldExist().get());
    }
    public void fightSingleVampireTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy vampire = new Vampire();
        Character c = new Character(vampire, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        assertTrue(c.getHealth() <= 46);
        assertFalse(vampire.shouldExist().get());
    }
    public void fightTwoSlugsTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug1 = new Slug();
        Enemy slug2 = new Slug();
        enemies.add(slug2);
        Character c = new Character(slug1, enemies);
        assertEquals(100, c.getHealth());
        c.fight();
        assertTrue(c.getHealth() <= 46);
        assertFalse(slug1.shouldExist().get());
        assertFalse(slug2.shouldExist().get());
    }
}
