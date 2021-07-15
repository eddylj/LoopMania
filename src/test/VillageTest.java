package test;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.VillageBuilding;
import unsw.loopmania.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
public class VillageTest {
    @Test
    public void VillageTest() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        Enemy slug = new Slug();
        Character c = new Character(slug, enemies);
        c.fight();
        VillageBuilding v = new VillageBuilding();
        v.heal(); //maybe some other method
        assertEquals(100, c.getHealth());
    }
    
}
