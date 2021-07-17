package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Armour extends Protection {
    private double damageReduction;

    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        damageReduction = 0.4 + 0.03*(level-1);
        super.setType("armour");
    }
    
    public Armour(int level) {
        super(level, 400*(1+(level-1)*15/100));
        damageReduction = 0.4 + 0.03*(level-1);
        super.setType("armour");
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    @Override
    public double protect(double damage) {
        //Will implement the percentage reduction
        return damage * (1 - damageReduction);
    }
}
