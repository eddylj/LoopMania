package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Helmet extends Protection {
    private int damageReduction = 3;
    private double debuff;
    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        debuff = 10 - (level-1);
        damageReduction += (level-1);
        super.setType("helmet");
    }
    
    public Helmet(int level) {
        super(level, 400*(1+(level-1)*15/100));
        debuff = 10 - (level-1);
        damageReduction += (level-1);
        super.setType("helmet");
    }

     /**
     * Given the damage being inflicted on the character and will return the amount
     * of damage that will be inflicted after the reduction from the protection
     * @param damage the total damage being given
     * @return the damage after the reduction from protection has been applied
     */
    @Override
    public double protect(double damage) {
        //Will implement the drop by the scalar value
        return damage - damageReduction;
    }
    /**
     * Applies damage debuff to character
     * @param attackDamage
     * @return reduced damage
     */
    public double calcAttackDamage(double attackDamage) {
        if (debuff > 0) {
            return attackDamage * (1- debuff*0.01);
        } 
        return attackDamage;
    }
    
}
