package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Anduril extends Sword implements RareItem{
    private static final double DAMAGE = 50.0;
    private static final int SELLPRICE = 1500;
    private static final int REPLACECOST = 375;
    private static final int BOSSDAMAGEMULTIPLIER = 3;

    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level);
        super.setType("anduril");
    }

    public Anduril(int level) {
        super(level);
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
    @Override
    public double getDamage(Enemy enemy) {
        // double damage = super.getDamage();
        if (enemy instanceof Boss){
            return DAMAGE * BOSSDAMAGEMULTIPLIER;
        } else {
            return DAMAGE;
        }
    }
}
