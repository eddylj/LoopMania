package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Protection extends StaticEntity implements Item{
    private int level;
    private int goldAmount;

    public Protection (int level, int goldAmount, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.level = level;
        this.goldAmount = goldAmount;
    }

    public Protection(int level, int price) {
        super();
        this.level = level;
        this.goldAmount = price;
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

    public int getLevel() {
        return level;
    }

    @Override
    public int getPrice() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSellPrice() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getReplaceCost() {
        // TODO Auto-generated method stub
        return 0;
    }
}
