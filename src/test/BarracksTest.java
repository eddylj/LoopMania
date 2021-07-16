package test;
import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.BarracksCard;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Enemy;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
public class BarracksTest {

    private List<Pair<Integer, Integer>> path;
    private SimpleIntegerProperty x;
    private SimpleIntegerProperty y;
    
    public BarracksTest() {
        path = createPath();
        x = new SimpleIntegerProperty();
        y = new SimpleIntegerProperty();
        x.set(0);
        y.set(0);
    }

    private List<Pair<Integer, Integer>> createPath() {
        List<Pair<Integer, Integer>> l = new ArrayList<Pair<Integer, Integer>>();
        l.add(new Pair<Integer, Integer>(0, 0));
        l.add(new Pair<Integer, Integer>(0, 1));
        l.add(new Pair<Integer, Integer>(0, 2));
        l.add(new Pair<Integer, Integer>(1, 2));
        l.add(new Pair<Integer, Integer>(2, 2));
        l.add(new Pair<Integer, Integer>(2, 1));
        l.add(new Pair<Integer, Integer>(2, 0));
        l.add(new Pair<Integer, Integer>(1, 0));
        return l;
    }

    @Test
    public void AddAlliedSoldiersTest() {
        
        Character c = new Character(new PathPosition(0, path));
        BarracksBuilding b = new BarracksBuilding(x, y);
        assertEquals(b.getType(), "barracks");
        assertEquals(c.getAlliedSoldierCount(), 0);
        b.updateOnMove(c);
        assertEquals(c.getAlliedSoldierCount(), 1);
        b.updateOnMove(c);
        assertEquals(c.getAlliedSoldierCount(), 2);
        b.updateOnMove(c);
        assertEquals(c.getAlliedSoldierCount(), 3);
        b.updateOnMove(c);
        assertEquals(c.getAlliedSoldierCount(), 3);
    }

    @Test
    public void NoAlliedSoldiersAddedTest() {
        Character c = new Character(new PathPosition(0, path));
        x.set(1);
        BarracksBuilding b = new BarracksBuilding(x, y);
        assertEquals(c.getAlliedSoldierCount(), 0);
        b.updateOnMove(c);
        assertEquals(c.getAlliedSoldierCount(), 0);
    }

    @Test
    public void cardCanBePlacedTest(){
        BarracksCard b = new BarracksCard(x, y);
        
    }
}
