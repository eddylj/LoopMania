package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Anduril extends Weapon implements RareItem{
    private static final double NOPRICE = 0;
    private static final double DAMAGE = 50.0;
    private static final int SELLPRICE = 1500;
    private static final int REPLACECOST = 375;

    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, NOPRICE, DAMAGE);
        super.setType("anduril");
    }

    public Anduril(int level) {
        super(level, NOPRICE , DAMAGE);
        super.setType("anduril");
    }

    @Override 
    public int getSellPrice() {
        
        return SELLPRICE;
    }

    @Override
    public int getReplaceCost() {
        
        return REPLACECOST;
    }

    /**
     * returns damage weapon deals
     * @param e
     * @return damage applicable for enemy
     */
    public double getDamage(Enemy e) {
        if (e instanceof Boss){
            return DAMAGE * 3;
        } else {
            return DAMAGE;
        }
    }
}
