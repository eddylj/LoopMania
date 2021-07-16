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
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Helmet;
import unsw.loopmania.Slug;
import unsw.loopmania.Staff;
import unsw.loopmania.Vampire;
public class protectionTests {

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
    
    }

    //HelmetTests
    @Test
    public void helmetProtectTest(){
        Helmet h = new Helmet(1);
        assertEquals(5, h.protect(10));
        assertEquals(91, h.calcAttackDamage(100));

    }
}
