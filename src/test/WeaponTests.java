package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Sword;
import unsw.loopmania.Stake;
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;
import unsw.loopmania.Staff;
import unsw.loopmania.Vampire;
public class WeaponTests {
    @Test
    public void testSword() {
        Sword s = new Sword(1);
        assertEquals(35, s.getDamage());
        assertEquals(350, s.getPrice());
        assertEquals(140, s.getSellPrice());
        assertEquals(1, s.getLevel());
    } 

    @Test
    public void testStake() {
        Stake s = new Stake(1);
        Enemy p = new Slug();
        Enemy v = new Vampire();
        assertEquals(20, s.getDamage(p));
        assertEquals(50, s.getDamage(v));
        assertEquals(350, s.getPrice());
        assertEquals(140, s.getSellPrice());
        assertEquals(1, s.getLevel());

    } 
    @Test
    public void testStaff() {

        LoopManiaWorld l = new LoopManiaWorld(4);
        System.out.println(LoopManiaWorld.getRandNum());
        System.out.println(LoopManiaWorld.getRandNum());
        System.out.println(LoopManiaWorld.getRandNum());
        System.out.println(LoopManiaWorld.getRandNum());

        Staff s = new Staff(1);
        Enemy p = new Slug();
        Enemy v = new Vampire();
        assertEquals(18, s.getDamage());
        assertEquals(700, s.getPrice());
        assertEquals(280, s.getSellPrice());
        assertEquals(1, s.getLevel());
        System.out.println(LoopManiaWorld.getRandNum());
    } 
}
