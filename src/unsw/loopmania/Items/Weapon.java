package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Items.Item;

/**
 * Base class for all weapons
 */
public abstract class Weapon extends Item{
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
        this.price = price * Math.pow(1.15, level - 1);
        this.damage = damage * Math.pow(1.1, level - 1);
    }

    public Weapon(int level, Double price, Double damage) {
        super();
        this.level = level;
        this.price = price * Math.pow(1.15, level - 1);
        this.damage = damage * Math.pow(1.1, level - 1);
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

    @Override
	public boolean isWeapon() {
		return true;
	}
}
