package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.Slug;
import unsw.loopmania.Enemies.Vampire;
import unsw.loopmania.Enemies.Zombie;
import unsw.loopmania.Entities.StaticEntity;
import unsw.loopmania.Weapon;
import unsw.loopmania.Protection;
import unsw.loopmania.LoopManiaWorld;

public class DropsTest {

    private Slug slug = new Slug();
    private Zombie zombie = new Zombie();
    private Vampire vampire = new Vampire();
    private List<String> rare = new ArrayList<String>();

    public DropsTest() {
        rare.add("theonering");
    }
    
    private void checkWeapon(int seed, String weapon, int level, Enemy enemy, Character c) {
        LoopManiaWorld.setSeed(seed);
        StaticEntity item = enemy.getLoot(c, 8, rare).get(0);
        System.out.println(item.getType());
        assertEquals(item.getType(), weapon);
        assertEquals(((Weapon)item).getLevel(), level);
    }

    private void checkProtection(int seed, String protector, int level, Enemy enemy, Character c) {
        LoopManiaWorld.setSeed(seed);
        StaticEntity item = enemy.getLoot(c, 8, rare).get(0);
        System.out.println(item.getType());
        assertEquals(item.getType(), protector);
        assertEquals(((Protection)item).getLevel(), level);
    }

    private void checkOther(int seed, String itemString, Enemy enemy, Character c) {
        LoopManiaWorld.setSeed(seed);
        StaticEntity item = enemy.getLoot(c, 8, rare).get(0);
        System.out.println(item.getType());
        assertEquals(item.getType(), itemString);
    }

    private void checkCard(int seed, String itemString, Enemy enemy, Character c) {
        LoopManiaWorld.setSeed(seed);
        StaticEntity item = enemy.getLoot(c, 20, rare).get(0);
        System.out.println(item.getType());
        assertEquals(item.getType(), itemString);
    }

    @Test
    public void SlugDrop() {
        Character c1 = new Character();

        checkWeapon(184, "sword", 2, slug, c1);
        checkWeapon(112, "staff", 2, slug, c1);
        checkWeapon(26, "stake", 2, slug, c1);

        checkProtection(365, "shield", 2, slug, c1);
        checkProtection(22, "helmet", 2, slug, c1);
        checkProtection(33, "armour", 2, slug, c1);

        checkOther(322, "healthpotion", slug, c1);
        checkOther(18, "theonering", slug, c1);

        Character c2 = new Character();
        checkWeapon(85, "sword", 1, slug, c2);
        checkWeapon(2, "staff", 1, slug, c2);
        checkWeapon(153, "stake", 1, slug, c2);
        checkProtection(10, "shield", 1, slug, c2);
        checkProtection(96, "helmet", 1, slug, c2);
        checkProtection(30, "armour", 1, slug, c2); 

        checkCard(50, "village", slug, c1);
        checkCard(54, "barracks", slug, c1);
        checkCard(89, "zombiepit", slug, c1);
        checkCard(104, "campfire", slug, c1);
        checkCard(137, "tower", slug, c1);
        checkCard(93, "trap", slug, c1);

    }


    @Test
    public void ZombieDrop() {
        Character c1 = new Character();

        checkWeapon(184, "sword", 2, zombie, c1);
        checkWeapon(2, "staff", 2, zombie, c1);
        checkWeapon(26, "stake", 2, zombie, c1);

        checkProtection(45, "shield", 2, zombie, c1);
        checkProtection(22, "helmet", 2, zombie, c1);
        checkProtection(30, "armour", 2, zombie, c1);

        checkOther(41, "healthpotion", zombie, c1);
        checkOther(18, "theonering", zombie, c1);

        Character c2 = new Character();
        checkWeapon(14, "sword", 1, zombie, c2);
        checkWeapon(157, "staff", 1, zombie, c2);
        checkWeapon(93, "stake", 1, zombie, c2);
        checkProtection(10, "shield", 1, zombie, c2);
        checkProtection(50, "helmet", 1, zombie, c2);
        checkProtection(104, "armour", 1, zombie, c2); 

        checkCard(177, "village", zombie, c1);
        checkCard(264, "barracks", zombie, c1);
        checkCard(58, "vampirecastle", zombie, c1);
        checkCard(69, "campfire", zombie, c1);
        checkCard(347, "tower", zombie, c1);
        checkCard(260, "trap", zombie, c1);
    }

    @Test
    public void VampireDrop() {
        Character c1 = new Character();

        checkWeapon(14, "sword", 2, vampire, c1);
        checkWeapon(2, "staff", 2, vampire, c1);
        checkWeapon(100, "stake", 2, vampire, c1);

        checkProtection(120, "shield", 2, vampire, c1);
        checkProtection(96, "helmet", 2, vampire, c1);
        checkProtection(104, "armour", 2, vampire, c1);

        checkOther(116, "healthpotion", vampire, c1);
        checkOther(33, "theonering", vampire, c1);

        Character c2 = new Character();
        checkWeapon(38, "sword", 1, vampire, c2);
        checkWeapon(19, "staff", 1, vampire, c2);
        checkWeapon(62, "stake", 1, vampire, c2);
        checkProtection(34, "shield", 1, vampire, c2);
        checkProtection(58, "helmet", 1, vampire, c2);
        checkProtection(146, "armour", 1, vampire, c2); 

        checkCard(3, "village", vampire, c1);
        checkCard(7, "barracks", vampire, c1);
        checkCard(11, "vampirecastle", vampire, c1);
        checkCard(51, "zombiepit", vampire, c1);
        checkCard(31, "campfire", vampire, c1);
        checkCard(15, "tower", vampire, c1);
        checkCard(66, "trap", vampire, c1);
    }
}


