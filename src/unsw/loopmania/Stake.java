package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Stake extends Weapon{
    public static final double PRICE = 350.0;
    public static final double DAMAGE = 20.0;
    public static final int BASECRIT = 50;
    private double critAttack;
    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, PRICE, DAMAGE);
        critAttack = BASECRIT * Math.pow(1.1, level);
        super.setType("stake");
    }

    public Stake(int level) {
        super(level, PRICE, DAMAGE);
        critAttack = BASECRIT * Math.pow(1.1, level);
        super.setType("stake");
    }

    /**
     * returns damage weapon deals
     * @param e
     * @return damage applicable for enemy
     */
    public double getDamage(Enemy enemy) {
        if (enemy instanceof Vampire){
            return critAttack;
        }
        return super.getDamage();
    }
}
