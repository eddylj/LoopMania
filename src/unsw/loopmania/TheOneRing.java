package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TheOneRing extends Item implements RareItem{
    public static final int NOPRICE = 0;
    /**
     * The class of health potion
     * @param x
     * @param y
     */
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setType("theonering");
    }

    public TheOneRing() {
        super();
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
    @Override
    public int getPrice() {
        return NOPRICE;
    }

	@Override
	public boolean isRing() {
		return true;
	}
}
