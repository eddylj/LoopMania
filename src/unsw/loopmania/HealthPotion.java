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
        super.setType("healthpotion");
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

    public int getPrice() {
        return 100;
    }

    @Override
    public int getSellPrice() {
        return 40;
    }

    @Override
    public int getReplaceCost() {
        return 20;
    }
}
