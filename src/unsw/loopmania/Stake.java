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
        super(x, y, level, 350*(1+(level-1)*15/100), 20.0*(1+((level-1)*1.0)/10));
        critAttack = 50*(1+(level-1)/10);
        super.setType("stake");
    }

    public Stake(int level) {
        super(level, 350*(1+(level-1)*15/100), 20.0*(1+((level-1)*1.0)/10));
        critAttack = 50*(1+(level-1)/10);
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
