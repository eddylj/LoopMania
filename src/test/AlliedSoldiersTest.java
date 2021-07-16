package test;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import unsw.loopmania.Slug;
import unsw.loopmania.AlliedSoldier;

public class AlliedSoldiersTest {
    AlliedSoldier a = new AlliedSoldier(1);

    @Test
    public void isDeadTest(){
        assertEquals(a.getHealth(), 30);
        a.setHealth(0);
        assertTrue(a.isDead());
    }

    @Test
    public void attackTest(){
        Slug s = new Slug();
        a.attack(s);
        assertEquals(s.getHealth(), 29);
    }

    @Test
    public void takeDamageTest() {
        Slug s = new Slug();
        s.attack(a);
        assertEquals(a.getHealth(), 20);
    }
}

