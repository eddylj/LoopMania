package test;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.Enemy;
import unsw.loopmania.LoopManiaWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
public class VampireCastleTest {
    @Test
    public void VampireCastleSpawnTest() {
        VampireCastleBuilding vampireCastle = new VampireCastleBuilding();
        vampireCastle.spawnVampire();
        Vampire vampire = new Vampire();
        assertTrue(LoopManiaWorld.getEnemies().size() == 1 || LoopManiaWorld.getEnemies().size() == 2);
        for (Enemy enemy: LoopManiaWorld.getEnemies()) {
            assertTrue(enemy instanceof Vampire);
        }
    }

    @Test
    public void VampireCastlePositionTest() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(0);
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);
        VampireCastleBuilding vampireCastle = new VampireCastleBuilding(x, y);
        vampireCastle.spawnVampire();
        for (Enemy enemy: LoopManiaWorld.getEnemies()) {
            assertTrue(enemy.getX() == x.get() || enemy.getX() - 1 == x.get() || enemy.getX() + 1 == x.get());
            assertTrue(enemy.getY() == y.get() || enemy.getY() - 1 == y.get() || enemy.getY() + 1 == y.get());
        }
    }

    @Test
    public void VampireCastleCycleTest() {
    
    }
}
