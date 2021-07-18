package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Gold extends StaticEntity{
    private int amount;
    /**
     * 
     * @param x
     * @param y
     * @param amount
     */
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y, int amount) {
        super(x, y);
        this.amount = amount;
    }
    
}
