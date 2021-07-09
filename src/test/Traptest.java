package test;
package test;
import unsw.loopmania.Character;
import unsw.loopmania.Vampire;
import unsw.loopmania.Enemy;
import unsw.loopmania.TrapBuilding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
public class Traptest {
    @Test
    public void trapdamagetest(){
        TrapBuilding t = new TrapBuilding();
        Enemy slug = new Slug();
        t.activate(slug);
        assert(slug.getHealth() < 50)
    }
}
