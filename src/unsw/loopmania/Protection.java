package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Protection extends Item{
    private int level;
    private double price;
    /**
     * 
     * @param level
     * @param price
     * @param x
     * @param y
     */
    public Protection (int level, double price, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.level = level;
        this.price = price * Math.pow(1.15, level - 1);
    }

    public Protection(int level, int price) {
        super();
        this.level = level;
        this.price = price * Math.pow(1.15, level - 1);
    }

    /**
     * Given the damage being inflicted on the character and will return the amount
     * of damage that will be inflicted after the reduction from the protection
     * @param damage the total damage being given
     * @return the damage after the reduction from protection has been applied
     */
    public double protect(double damage) {
        return damage;
    }
    public double protect(double damage, Enemy e) {
        return damage;
    }
    

    public int getLevel() {
        return level;
    }
    @Override
    public int getPrice() {
        return (int)price;
    }
    @Override
    public int getSellPrice() {
        return (int) (price * 0.4);
    }

    @Override
    public int getReplaceCost() {
        return (int) (price * 0.2);
    }

    @Override
    public boolean isProtection() {
        return true;
    }
}
