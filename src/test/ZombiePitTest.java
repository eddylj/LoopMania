package test;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;
import unsw.loopmania.ZombiePitBuilding;
import unsw.loopmania.Enemy;
import unsw.loopmania.LoopManiaWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
public class ZombiePitTest {
    @Test
<<<<<<< HEAD
    public void ZombieBuildingTest() {

    }
    @Test
    public void ZombieBuildingSpawnTest() {

    }
=======
    public void ZombiePitSpawnTest() {
        ZombiePitBuilding zombiePit = new ZombiePitBuilding();
        zombiePit.spawnZombie();
        assertTrue(LoopManiaWorld.getEnemies().size() == 1 || LoopManiaWorld.getEnemies().size() == 2
            || LoopManiaWorld.getEnemies().size() == 3);
        for (Enemy enemy: LoopManiaWorld.getEnemies()) {
            assertTrue(enemy instanceof Zombie);
        }
    }

    @Test
    public void ZombiePitPositionTest() {
        SimpleIntegerProperty x = new SimpleIntegerProperty(0);
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);
        ZombiePitBuilding zombiePit = new ZombiePitBuilding(x, y);
        zombiePit.spawnZombie();
        for (Enemy enemy: LoopManiaWorld.getEnemies()) {
            assertTrue(enemy.getX() == x.get() || enemy.getX() - 1 == x.get() || enemy.getX() + 1 == x.get());
            assertTrue(enemy.getY() == y.get() || enemy.getY() - 1 == y.get() || enemy.getY() + 1 == y.get());
        }
    }

    public void ZombiePitCycleTest() {
>>>>>>> vampire and zombie building and shop cycle tests
    
    }

}


