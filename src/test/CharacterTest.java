package test;

import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.Armour;
import unsw.loopmania.BattleRunner;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Enemy;
import unsw.loopmania.Helmet;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Shield;
import unsw.loopmania.Slug;
import unsw.loopmania.Sword;
import unsw.loopmania.TowerBuilding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CharacterTest {
    private Character c = new Character();
    
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<AlliedSoldier> allies = new ArrayList<AlliedSoldier>();
    private List<TowerBuilding> towers = new ArrayList<TowerBuilding>();
    private BattleRunner b = new BattleRunner(c, enemies, allies, towers);


    @Test
    public void CharacterExistsTest() {
        assertTrue(c.shouldExist().get());
    }

    @Test
    public void CharacterStartStatsTest() {
        Character c = new Character();
        assertEquals(100, c.getHealth());
        assertEquals(0, c.getGold());
        assertEquals(0, c.getXP());
    }
    @Test
    public void attackTest(){
        Enemy e = new Slug();
        c.attack(e, b);
        assertEquals(45, e.getHealth());
    }


    //TO DO
    public void attackSwordTest(){
        Enemy e = new Slug();
        Sword s = new Sword(1);
        Item swordItem = (Item) s;
        c.equip(swordItem);
        c.attack(e, b);
        assertEquals(15, e.getHealth());
        c.uneq
    }

    public void attackStakeTest(){
        Enemy e1 = new Vampire();
        Stake s = new Stake(1);
        Item stakeItem = (Item) s;
        c.equip(stakeItem);
        c.attack(e1, b);
        assertEquals(100, e1.getHealth());
        Enemy e2 = new Slug();
        c.attack(e2, b);
        assertEquals(30, e2.getHealth());

    }

    public void attackStaffTest(){

        Enemy e = new Vampire();
        Staff s = new Staff(1);
        Item staffItem = (Item) s;
        LoopManiaWorld.setSeed(40);
        c.equip(staffItem);
        Enemy e = new Slug();
        c.attack(e, b);
        assertEquals(32, e.getHealth());
    }

    public void attackHelmetTest(){
        Enemy e = new Slug();
        Stake s = new Stake(1);
        Item stakeItem = (Item) s;
        Item helmetItem = (Item) new Helmet(1);
        c.equip(stakeItem);
        c.equip(helmetItem);
        c.attack(e, b);
        assertEquals(32, e.getHealth());
    }


    @Test
    public void takeDamageShieldTest() {
        Character c = new Character();
        Shield shield = new Shield(1);
        LoopManiaWorld.setSeed(22);
        Item shieldItem = (Item) shield;
        c.equip(shieldItem);
        c.takeDamage(10);
        assertEquals(c.getHealth(), 100);
        c.takeDamage(10);
        assertEquals(c.getHealth(), 90);
    }

    @Test
    public void takeDamageArmourTest() {
        Character c = new Character();
        Armour armour = new Armour(1);
        Item armItem = (Item) armour;
        c.equip(armItem);
        c.takeDamage(10);
        assertEquals(c.getHealth(), 94);
    }

    @Test
    public void takeDamageHelmetTest() {
        Character c = new Character();
        Helmet helmet = new Helmet(1);
        Helmet helmetItem = (Item) helmet;
        c.equip(helmetItem);
        c.takeDamage(10);
        assertEquals(c.getHealth(), 97);
    }


    @Test
    public void SellRemovesItemFromInventory() {
        Character c = new Character();
        Item sword = new Sword(1);
        assertEquals(0, c.numEquipmentInInventory());
        c.pickup(sword);
        assertEquals(1, c.numEquipmentInInventory());
        c.sellItem(sword);
        assertEquals(0, c.numEquipmentInInventory());

    }

    @Test
    public void SellGainGold() {
        Character c = new Character();
        Item sword = new Sword(1);
        c.pickup(sword);
        c.sellItem(sword);
        assertEquals(140, c.getGold());
    }

    @Test
    public void SellLevelGoldValue() {
        Character c = new Character();
        Item sword = new Sword(5);
        c.pickup(sword);
        c.sellItem(sword);
        assertEquals(224, c.getGold());

    }
}
