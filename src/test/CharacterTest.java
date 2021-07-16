package test;

import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.BattleRunner;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Enemy;
import unsw.loopmania.Item;
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
        c.attack(e, b);
        assertEquals(10, e.getHealth());
    }

    public void attackStakeTest(){
        Enemy e = new Vampire();
        c.attack(e, b);
        assertEquals(45, e.getHealth());
    }

    public void attackStaffTest(){
        Enemy e = new Slug();
        c.attack(e, b);
        assertEquals(45, e.getHealth());
    }

    public void attackHelmetTest(){
        Enemy e = new Slug();
        c.attack(e, b);
        assertEquals(45, e.getHealth());
    }


    @Test
    public void takeDamageShieldTest() {
        

    }
    @Test
    public void takeDamageArmourTest() {
        

    }
    @Test
    public void takeDamageHelmetTest() {
        

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
