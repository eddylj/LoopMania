package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TheOneRing extends RareItem{

    /**
     * The class of health potion
     * @param x
     * @param y
     */
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setType("theonering");
    }
    /**
     * @return cost of selling item
     */
    @Override
    public int getSellPrice() {
        return 1000;
    }
    /**
     * @return gold gained when item is replaced as oldest in inventory
     */
    @Override
    public int getReplaceCost() {
        return 400;
    }
    
}
