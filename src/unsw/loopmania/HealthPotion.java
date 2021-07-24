package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class HealthPotion extends StaticEntity implements Item{
    private int cost;
    /**
     * The class of health potion
     * @param x
     * @param y
     */
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setType("healthpotion");
        cost = 100;
    }

    public HealthPotion() {
        super();
        super.setType("healthpotion");
    }

    /**
     * Will heal the given character
     * @param character
     */
    public void heal(Character character) {
        character.restoreHealth(40);
    }
    @Override
    public int getPrice() {
        return cost;
    }

    @Override
    public int getSellPrice() {
        return 40;
    }

    @Override
    public int getReplaceCost() {
        return 20;
    }

    public void increaseCost(int timesBought) {
        cost += (50 * timesBought);
    }
}
