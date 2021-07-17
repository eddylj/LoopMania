package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Armour;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaApplication;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Shield;
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Helmet;
import unsw.loopmania.Slug;
import unsw.loopmania.Staff;
import unsw.loopmania.Vampire;
import unsw.loopmania.Hero;

public class ProtectionTests {

    //Armour Tests
    @Test
    public void damageProtectionTest() {
        Armour armour = new Armour(1);
        assertEquals(armour.getType(), "armour");
        for (int i = 0; i < 10; i++) {
            double damage = i * 10;
            assertEquals(damage - armour.getDamageReduction(), armour.protect(damage));
        }
    }

    @Test
    public void DifferentLevelTest() {
        Armour armour1 = new Armour(1);
        Armour armour2 = new Armour(10);
        double damage = 100;
        double armour1Damage = armour1.protect(damage);
        double armour2Damage = armour2.protect(damage);
        assertNotEquals(armour1Damage, armour2Damage);
    }

    //ShieldTests
    @Test
    public void ShieldTest() {
        
        LoopManiaWorld.setSeed(22);
        Shield s1 = new Shield(1);
        assertEquals(s1.protect(10), 0);
        assertEquals(s1.protect(10), 10);
        assertEquals(s1.protect(10), 10);
        assertEquals(s1.protect(10), 10);
    }
    
    @Test
    public void ShieldLevelTest() {
        LoopManiaWorld.setSeed(22);
        Shield s2 = new Shield(2);
        assertEquals(s2.protect(10), 0);
        assertEquals(s2.protect(10), 10);
        assertEquals(s2.protect(10), 0);
        assertEquals(s2.protect(10), 10);
    }

    @Test
    public void VampireShieldTest() {
        
        LoopManiaWorld.setSeed(6);
        Character c = new Character();
        Vampire v1 = new Vampire();
        Hero h = (Hero) c;
        v1.attack(h);
        assertEquals(57, c.getHealth());

        Shield s1 = new Shield(1);
        LoopManiaWorld.setSeed(6);
        Vampire v2 = new Vampire();
        c.equip(s1);
        v2.attack(h);
        assertEquals(39, c.getHealth());
    }

    //HelmetTests
    @Test
    public void helmetProtectTest(){
        Helmet h = new Helmet(1);
        assertEquals(7, h.protect(10));
        assertEquals(90, h.calcAttackDamage(100));

    }

    @Test
    public void helmetProtectLevelTest() {

        Helmet h = new Helmet(5);
        assertEquals(13, h.protect(20));
        assertEquals(94, h.calcAttackDamage(100));
    }
}
