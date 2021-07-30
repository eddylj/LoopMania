package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    public static final double PRICE = 350.0;
    public static final double DAMAGE = 35.0;
    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, PRICE, DAMAGE);
        super.setType("sword");
    }

    public Sword(int level) {
        super(level, PRICE, DAMAGE);
        super.setType("sword");
    }

    public double getDamage(Enemy enemy) {
        return super.getDamage();
    }
    
}
