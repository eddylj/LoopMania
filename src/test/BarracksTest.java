package test;
import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
public class BarracksTest {
    @Test
    public void BarracksTest() {
        BarracksBuilding Barracks = new BarracksBuilding();
        Character c = new Character();
        Barracks.addAlliedSoldier();
        List aList  = c.getAlliedSoldiers();
        assertEquals(aList.size(), 1);
    }
    @Test
    public void MaxAlliedSoldierTest() {
        BarracksBuilding Barracks = new BarracksBuilding();
        Character c = new Character();
        Barracks.addAlliedSoldier();
        Barracks.addAlliedSoldier();
        Barracks.addAlliedSoldier();
        Barracks.addAlliedSoldier();
        Barracks.addAlliedSoldier();
        Barracks.addAlliedSoldier();
        List aList  = c.getAlliedSoldiers();
        assertEquals(aList.size(), 5);
    }
}
