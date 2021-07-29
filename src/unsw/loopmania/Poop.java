package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Poop extends StaticEntity{
    private int damage;
    /**
     * 
     * @param x
     * @param y
     */
    public Poop(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        damage = 5;
    }

    public Poop() {
        super();
        damage = 5;
    }
    

    /**
     * Checks if character has stepped on Poop. If so activate Poop
     * @param character
     */
    public void updateOnMove(Character character) {
        if (!(character instanceof Character)) {
            return;
        }
        else if (character.getX() == super.getX() && character.getY() == super.getY()) {
            activate(character);
        }
    }

    /**
     * Activates Poops
     * @param character
     */
    public void activate(Character character) {
        character.takeRawDamage(damage);
        this.destroy();
    }

    public int getAmount() {
        return damage;
    }
}