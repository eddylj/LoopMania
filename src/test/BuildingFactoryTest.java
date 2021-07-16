package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.BarracksBuilding;
import unsw.loopmania.Building;
import unsw.loopmania.BuildingFactory;
import unsw.loopmania.CampfireBuilding;
import unsw.loopmania.TowerBuilding;
import unsw.loopmania.TrapBuilding;
import unsw.loopmania.VampireCastleBuilding;
import unsw.loopmania.VillageBuilding;
import unsw.loopmania.ZombiePitBuilding;

public class BuildingFactoryTest {
    SimpleIntegerProperty x = new SimpleIntegerProperty(0);
    SimpleIntegerProperty y = new SimpleIntegerProperty(0);
    BuildingFactory b = new BuildingFactory();

    //"Campfire", "Barracks", "Tower", "Trap", "VampireCastle", "ZombiePit", "Village"}
    @Test
    public void createCampfireTest() {
        Building i = b.create(x, y, "campfire");
        assertTrue(i instanceof CampfireBuilding);
    }
    @Test
    public void createbarracksTest() {
        Building i = b.create(x, y, "barracks");
        assertTrue(i instanceof BarracksBuilding);
    }
    @Test
    public void createtowerTest() {
        Building i = b.create(x, y, "tower");
        assertTrue(i instanceof TowerBuilding);
    }
    @Test
    public void createtrapTest() {
        Building i = b.create(x, y, "trap");
        assertTrue(i instanceof TrapBuilding);
    }
    @Test
    public void createvampirecastleTest() {
        Building i = b.create(x, y, "vampirecastle");
        assertTrue(i instanceof VampireCastleBuilding);
    }
    @Test
    public void createzombiepitTest() {
        Building i = b.create(x, y, "zombiepit");
        assertTrue(i instanceof ZombiePitBuilding);
    }
    @Test
    public void createvillageTest() {
        Building i = b.create(x, y, "village");
        assertTrue(i instanceof VillageBuilding);
    }


}
