package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Protection extends StaticEntity implements Item{
    private int level;
    private int price;

    public Protection (int level, int price, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.level = level;
        this.price = price;
    }

    public Protection(int level, int price) {
        super();
        this.level = level;
        this.price = price;
    }

    /**
     * Given the damage being inflicted on the character and will return the amount
     * of damage that will be inflicted after the reduction from the protection
     * @param damage the total damage being given
     * @return the damage after the reduction from protection has been applied
     */
    public abstract double protect(double damage);

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }

    public int getSellPrice() {
        return (int) (price * 0.4);
    }

    @Override
    public int getReplaceCost() {
        // TODO Auto-generated method stub
        return 0;
    }
}
