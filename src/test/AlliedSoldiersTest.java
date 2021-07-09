package test;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import unsw.loopmania.Character;
import unsw.loopmania.Enemy;
import unsw.loopmania.Item;
import unsw.loopmania.Slug;
import unsw.loopmania.Sword;
import unsw.loopmania.AlliedSoldier;

public class AlliedSoldiersTest {

    
    @Test
    public void gainAlliedSoldierTest() {
        Character c = new Character();
        AlliedSoldier ally = new AlliedSoldier();
        assertEquals(0, c.numAlliedSoldiers());
        c.gainAlliedSoldier(ally);
        assertEquals(1, c.numAlliedSoldiers());

    }

    @Test
    public void gainMostAlliedSoldierTest() {
        Character c = new Character();
        AlliedSoldier ally1 = new AlliedSoldier();
        AlliedSoldier ally2 = new AlliedSoldier();
        AlliedSoldier ally3 = new AlliedSoldier();
        AlliedSoldier ally4 = new AlliedSoldier();

        assertEquals(0, c.numAlliedSoldiers());
        c.gainAlliedSoldier(ally1);
        assertEquals(1, c.numAlliedSoldiers());
        c.gainAlliedSoldier(ally2);
        assertEquals(2, c.numAlliedSoldiers());
        c.gainAlliedSoldier(ally3);
        assertEquals(3, c.numAlliedSoldiers());
        c.gainAlliedSoldier(ally4);
        assertEquals(3, c.numAlliedSoldiers());
    }

    @Test
    public void alliedSoldiersAttackTest() {
        AlliedSoldier ally = new AlliedSoldier();
        Enemy slug = new Slug();
        ally.attack(slug);
        assertEquals(30, slug.getHealth());
    }

    @Test
    public void alliedSoldierDamageTest() {
        AlliedSoldier ally = new AlliedSoldier();
        Enemy slug = new Slug();
        slug.attack(ally);
        assertEquals(20, ally.getHealth());

    }


}

