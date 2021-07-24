package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Stake extends Weapon{

    private double critAttack;
    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, 350.0, 35.0);
        critAttack = 50 * Math.pow(1.1, level);
        super.setType("stake");
    }

    public Stake(int level) {
        super(level, 250.0, 20.0);
        critAttack = 50 * Math.pow(1.1, level);
        super.setType("stake");
    }

    /**
     * returns damage weapon deals
     * @param e
     * @return damage applicable for enemy
     */
    public double getDamage(Enemy e) {
        if (e instanceof Vampire){
            return critAttack;
        }
        return super.getDamage();
    }
}
