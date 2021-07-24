package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Armour provides protection for the character during battles.
 * It reduces the incoming damage by a certain percentage,
 * ranging from 40% at level 1 to 70% at level 10.
 * @author Group FRIDGE
 */
public class Armour extends Protection {
    private double damageReduction;

    /**
     * Main constructor for Armour class
     * @param x int Column coordinate in inventory
     * @param y int Row coordinate in inventory
     * @param level int Level of armour
     */
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400, x, y);
        damageReduction = 0.4 + 0.03*(level-1);
        super.setType("armour");
    }
    
    /**
     * Constructor for Armour class used in testing
     * @param level int Level of armour
     */
    public Armour(int level) {
        super(level, 400);
        damageReduction = 0.4 + 0.03*(level-1);
        super.setType("armour");
    }

    /**
     * Gets amount of damage percentage that armour prevents
     * @return int Damage percentage prevented by armour
     */
    public double getDamageReduction() {
        return damageReduction;
    }

    /**
     * FIgures out how much damage armour prevents
     * @param damage double Incoming damage
     * @return double outgoing damage (after armour reduces it)
     */
    @Override
    public double protect(double damage) {
        //Will implement the percentage reduction
        return damage * (1 - damageReduction);
    }
}
