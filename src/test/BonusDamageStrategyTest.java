package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Armour;
import unsw.loopmania.BattleRunner;
import unsw.loopmania.CampfireState;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.NormalState;
import unsw.loopmania.Weapon;
import unsw.loopmania.Enemy;
import unsw.loopmania.Helmet;
import unsw.loopmania.Slug;
import unsw.loopmania.Staff;
import unsw.loopmania.Vampire;
import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.TowerBuilding;


public class BonusDamageStrategyTest {
    
    private Character c = new Character();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<AlliedSoldier> allies = new ArrayList<AlliedSoldier>();
    private List<TowerBuilding> towers = new ArrayList<TowerBuilding>();
    private BattleRunner b = new BattleRunner(c, enemies, allies, towers);

    @Test
    public void attackTest(){
        Enemy e = new Slug();
        c.attack(e, b);
        assertEquals(45, e.getHealth());
        c.setBonusDamageStrategy(new CampfireState());
        c.attack(e, b);
        assertEquals(35, e.getHealth());
        c.setBonusDamageStrategy(new NormalState());
        c.attack(e, b);
        assertEquals(30, e.getHealth());

    }
}