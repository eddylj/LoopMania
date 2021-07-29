package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Thornmail extends Armour {
    public static final int PRICE = 350;
    private static final double DMGREDUCTION = 0.3;
    private static final double DMGTHORNS = 0.1;
    private double damageReduction;
    private double thorns;

    /**
     * Main constructor for Armour class
     * @param x int Column coordinate in inventory
     * @param y int Row coordinate in inventory
     * @param level int Level of armour
     */
    public Thornmail(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        // super(level, PRICE, x, y);
        super(x, y, level);
        thorns = DMGTHORNS + 0.01*(level - 1);
        damageReduction = DMGREDUCTION + 0.03*(level-1);
        super.setType("thornmail");
    }
    
    /**
     * Constructor for Armour class used in testing
     * @param level int Level of armour
     */
    public Thornmail(int level) {
        super(level);
        thorns = DMGTHORNS + 0.01*(level - 1);
        damageReduction = DMGREDUCTION + 0.03*(level-1);
        super.setType("thornmail");
    }

    /**
     * FIgures out how much damage armour prevents
     * @param damage double Incoming damage
     * @return double outgoing damage (after armour reduces it)
     */
    public double protect(double damage, Enemy enemy) {
        enemy.takeDamage(damage * thorns);
        return damage * (1 - damageReduction);
    }
    
}