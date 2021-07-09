package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class HealthPotion extends StaticEntity implements Item{
    
    /**
     * The class of health potion
     * @param x
     * @param y
     */
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
    }

    public HealthPotion() {
        super();
    }

    /**
     * Will heal the given character
     * @param character
     */
    public void heal(Character character) {

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
