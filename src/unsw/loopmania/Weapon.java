package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Base class for all weapons
 */
public abstract class Weapon extends StaticEntity implements Item{
    public int level;
    public double damage;
    public double price;

    /**
     * 
     * @param x
     * @param y
     * @param level
     * @param price
     * @param damage
     */
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y, int level, double price, Double damage) {
        super(x,y);
        this.level = level;
        this.price = 350 * Math.pow(1.15, level);
        this.damage = damage * Math.pow(1.1, level);
    }

    public Weapon(int level, Double price, Double damage) {
        super();
        this.level = level;
        this.price = price;
        this.damage = damage;

    }

    public double getDamage() {
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
        return (int)(price * 0.4);
    }
    @Override
    public int getReplaceCost() {
        return (int)(price * 0.2);
    }
}
