package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class HealthPotion extends StaticEntity implements Item{
    public static final int SELLPRICE = 40;
    public static final int REPLACECOST = 20;
    public static final int BASECOST = 50;
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
        return SELLPRICE;
    }

    @Override
    public int getReplaceCost() {
        return REPLACECOST;
    }

    public void increaseCost(int timesBought) {
        cost += (BASECOST * timesBought);
    }

	@Override
	public boolean isWeapon() {
		return false;
	}

	@Override
	public boolean isShield() {
		return false;
	}

	@Override
	public boolean isRing() {
		return false;
	}

	

}
