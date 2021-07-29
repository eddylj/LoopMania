package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BankBuilding extends StaticEntity implements Building, BuildingOnMove{
    /**
     * 
     * @param x
     * @param y
     */
    public BankBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("bank");
    }
    /**
     * Checks if character is on village. If so heal it
     * @param character
     */
    @Override
    public void updateOnMove(MovingEntity character) {
        if (!(character instanceof Character)) {
            return;
        }
        else if (character.getX() == super.getX() && character.getY() == super.getY()) {
            getInterest((Character) character);
        }
        
    }
    /**
     * Heals character
     * @param c
     */
    public void getInterest(Character character) {
        character.getInterest();
    }
}