
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Armour;

public class ArmourTest {
    private JSONObject goals;
    private List<Pair<Integer, Integer>> path;
    private SimpleIntegerProperty x;
    private SimpleIntegerProperty y;
    
    public ArmourTest() {
        goals = new JSONObject();
        goals.put("goal", "gold");
        goals.put("quantity", 1000);
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
    public void damageProtectionTest() {
        Armour armour = new Armour(x, y, 1);
        assertEquals(armour.getType(), "armour");
        for (int i = 0; i < 10; i++) {
            double damage = i * 10;
            assertEquals(damage *(1- armour.getDamageReduction()), armour.protect(damage));
        }
    }

    @Test
    public void DifferentLevelTest() {
        Armour armour1 = new Armour(x, y, 1);
        x.set(2);
        Armour armour2 = new Armour(x, y, 10);
        double damage = 100;
        double armour1Damage = armour1.protect(damage);
        double armour2Damage = armour2.protect(damage);
        assertNotEquals(armour1Damage, armour2Damage);
    }
}
