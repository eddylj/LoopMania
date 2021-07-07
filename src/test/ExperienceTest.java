package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.loopmania.ZombiePitCard;

public class ExperienceTest {
    @Test
    public void getExperienceFromSlugTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug = new Slug();
        Character c = new Character(slug, enemies);
        assertEquals(0, c.getXP());
        c.fight();
        AssertEquals(1, c.getXP());
    }
    
    @Test
    public void getExperienceFromZombieTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy zombie = new Zombie();
        Character c = new Character(zombie, enemies);
        assertEquals(0, c.getXP());
        c.fight();
        AssertEquals(2, c.getXP());
    }
    @Test
    public void getExperienceFromVampireTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy vampire = new Vampire();
        Character c = new Character(vampire, enemies);
        assertEquals(0, c.getXP());
        c.fight();
        AssertEquals(4, c.getXP());
    }

    @Test
    public void getExperienceFromCardReplaceTest() {
        Character c = new Character();
        for (int i = 0; i < 10; i++) {
            c.pickup(new ZombiePitCard());
        }
        assertEquals(0, c.getXP());
        c.pickup(new ZombiePitCard());
        assertEquals(3, c.getXP());
    }
    
    @Test
    public void getExperienceFromItemReplaceTest() {
        Character c = new Character();
        for (int i = 0; i < 10; i++) {
            c.pickup(new Potion());
        }
        assertEquals(0, c.getXP());
        c.pickup(new Stake(4));
        assertEquals(3, c.getXP());
    }

}

