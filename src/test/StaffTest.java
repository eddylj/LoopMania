package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.BattleRunner;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Shop;
import unsw.loopmania.Staff;
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;


public class StaffTest {
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private BattleRunner b = new BattleRunner();

    @Test
    public void StaffLevelStoredTest() {
        Character c = new Character();
        Item staff = new Staff(3);

        assertEquals(1, c.getHighestLevel(staff));
        c.equip(staff);
        assertEquals(3, c.getHighestLevel(staff));
    }

    @Test
    public void StaffSaleTest() {
        Character c = new Character();
        Item staff = new Staff(1);

        c.sellItem(staff);
        
        //assertEquals(280, c.getGold());
    }

    @Test
    public void StaffTranceTest() {
        LoopManiaWorld.setSeed(18);
        Character c = new Character();
        b.setCharacter(c);

        Item staff = new Staff(1);
        
        Enemy e = new Slug();
        enemies = new ArrayList<Enemy>();
        assertTrue(enemies.isEmpty());
        b.setEnemies(enemies);
        enemies.add(e);
        c.equip(staff);
        c.attack(e, b);

        assertTrue(c.getAlliedSoldierCount()>0);
        assertTrue(enemies.isEmpty());
        assertEquals(e.getHealth(), 32);
    }

    @Test
    public void StaffDamageTest() {
        LoopManiaWorld.setSeed(17);
        Character c = new Character();
        b.setCharacter(c);

        Item staff = new Staff(1);

        assertEquals(1, c.getHighestLevel(staff));
        
        Enemy e = new Slug();

        c.equip(staff);
        c.attack(e, b);
        assertEquals(e.getHealth(), 32);
    }

    @Test
    public void compareDamages() {
        Weapon staff = new Staff(1);
        Double damage = staff.getDamage();
        Double initialDamage = damage;
        for (int i = 2; i <= 10; i++) {
            Weapon nextStaff= new Staff(i);
            assertEquals(nextStaff.getDamage(), initialDamage*(1+(((i-1)*1.0)/10)));
            damage = nextStaff.getDamage();
        }
    }
    
}