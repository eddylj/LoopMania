package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.CampfireCard;
import unsw.loopmania.TrapCard;
import unsw.loopmania.VampireCastleCard;
import unsw.loopmania.Item;
import unsw.loopmania.Sword;
import unsw.loopmania.Shield;
import unsw.loopmania.Helmet;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;
import unsw.loopmania.Character;

public class GoldTest {
    @Test
    public void GetGoldFromSlugTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug = new Slug();
        Character c = new Character(slug, enemies);
        assertEquals(0, c.getGold());
        c.fight();
        assertEquals(100, c.getGold());
    }
    
    @Test
    public void GetGoldFromVampireTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy vampire = new Vampire();
        Character c = new Character(vampire, enemies);
        assertEquals(0, c.getGold());
        c.fight();
        assertEquals(500, c.getGold());
    }
    @Test
    public void GetGoldFromZombieTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy zombie = new Zombie();
        Character c = new Character(zombie, enemies);
        assertEquals(0, c.getGold());
        c.fight();
        assertEquals(250, c.getGold());
    }

    @Test
    public void getGoldFromSellingTest() {
        Item sword = new Sword(10);
        List<Item> equipment = new ArrayList<Item>();
        equipment.add(sword);
        Character c = new Character(equipment);
        assertEquals(0, c.getGold());
        c.sellItem(sword);
        assertEquals(sword.getSellPrice(), c.getGold());
        Item sword2 = new Sword(2);
        assertTrue(sword.getSellPrice() > sword2.getSellPrice());
    }

    @Test
    public void getGoldFromItemReplaceTest() {
        List<Item> equipment = new ArrayList<Item>();
        Item shield = new Shield(3);
        equipment.add(shield);
        for (int i = 0; i < 14; i++) {
            equipment.add(new Helmet(5));
        }
        Character c = new Character(equipment);
        Item sword = new Sword(1);
        assertEquals(0, c.getGold());
        c.pickup(sword);
        assertEquals(shield.getReplaceCost(), c.getGold());
    }

    @Test
    public void getGoldFromCardReplaceTest() {
        Character c = new Character();
        for (int i = 0; i < 10; i++) {
            c.pickup(new TrapCard());
        }
        assertEquals(0, c.getGold());
        c.pickup(new CampfireCard());
        assertTrue(300 <= c.getGold() && c.getGold() <= 400);
        c.pickup(new VampireCastleCard());
        assertTrue(600 <= c.getGold() && c.getGold() <= 800);
    }
}
