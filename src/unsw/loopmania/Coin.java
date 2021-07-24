package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

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

    /**
     * Checks if character has stepped on coin. If so activate coin
     * @param enemy
     */
    public void updateOnMove(Character character) {
        if (!(character instanceof Character)) {
            return;
        }
        else if (character.getX() == super.getX() && character.getY() == super.getY()) {
            activate(character);
            this.destroy();
        }
    }

    /**
     * Activates coins
     * @param character
     */
    public void activate(Character character) {
        character.gainGold(amount);
    }
}
