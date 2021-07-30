package unsw.loopmania.Entities;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;

public class Coin extends StaticEntity{
    private int amount;
    /**
     * 
     * @param x
     * @param y
     */
    public Coin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        amount = LoopManiaWorld.getRandNum()/2+50;
    }

    public Coin() {
        super();
        amount = LoopManiaWorld.getRandNum()/2+50;
    }

    /**
     * Checks if character has stepped on coin. If so activate coin
     * @param character
     */
    public void updateOnMove(Character character) {
        // If entity is not a character, return
        if (!(character instanceof Character)) {
            return;
        }
        else if (character.getX() == super.getX() && character.getY() == super.getY()) {
            activate(character);
        }
    }

    /**
     * Activates coins
     * @param character
     */
    public void activate(Character character) {
        character.gainGold(amount);
        this.destroy();
    }

    public int getAmount() {
        return amount;
    }
}
