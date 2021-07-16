package test;
import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.BonusDamageStrategy;
import unsw.loopmania.CampfireBuilding;
import unsw.loopmania.CampfireState;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Enemy;
import unsw.loopmania.NormalState;
import unsw.loopmania.PathPosition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.DrbgParameters.Capability;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

public class CampfireTest {
    private JSONObject goals;
    private List<Pair<Integer, Integer>> path;
    private SimpleIntegerProperty x;
    private SimpleIntegerProperty y;
    
    public CampfireTest() {
        // goals = new JSONObject();
        // goals.put("goal", "gold");
        // goals.put("quantity", 1000);
        path = createPath();
        x = new SimpleIntegerProperty();
        y = new SimpleIntegerProperty();
        x.set(1);
        y.set(1);
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
    public void CampfireInRangeTest() {
        Character c = new Character(new PathPosition(0, path));
        CampfireBuilding campfire = new CampfireBuilding(x, y);
        
        BonusDamageStrategy b = c.getBonusDamageStrategy();
        assert(b instanceof NormalState);
        campfire.updateOnMove(c);
        BonusDamageStrategy newB = c.getBonusDamageStrategy();
       
        assert(newB instanceof CampfireState);
        campfire.updateOnMove(c);
        BonusDamageStrategy newB2 = c.getBonusDamageStrategy();
        assert(newB2 instanceof CampfireState);
    }
    @Test
    public void CampfireNotInRangeTest() {
        Character c = new Character(new PathPosition(0, path));
        x.set(5);
        y.set(5);
        CampfireBuilding campfire = new CampfireBuilding(x, y);
        campfire.updateOnMove(c);
        BonusDamageStrategy b = c.getBonusDamageStrategy();
        assert(b instanceof NormalState);
    }

    
}
