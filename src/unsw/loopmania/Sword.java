package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    // TODO = add more weapon/item types
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, 350*(1+(level-1)*15/100), 35*(1+(level-1)/10));
    }

    public Sword(int level) {
        super(level, 350*(1+(level-1)*15/100), 35*(1+(level-1)/10));
    }
    
}
