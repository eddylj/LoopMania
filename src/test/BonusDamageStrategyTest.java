package test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;

import unsw.loopmania.BattleRunner;
import unsw.loopmania.CampfireState;
import unsw.loopmania.Character;
import unsw.loopmania.NormalState;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;


public class BonusDamageStrategyTest {
    
    private Character c = new Character();
    private BattleRunner b = new BattleRunner();

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