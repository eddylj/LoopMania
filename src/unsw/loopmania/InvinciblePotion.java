package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class InvinciblePotion extends Potion implements RareItem{
    public static final int NOCOST = 0;
    public static final int SELLPRICE = 1000;
    public static final double REPLACEPERCENTAGE = 0.4;

    public InvinciblePotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("invinciblepotion");
    }

    public InvinciblePotion() {
        super();
        super.setType("invinciblepotion");
    }



	@Override
	public void increaseCost(int timesBought) {
        // Rare items can't be bought.
	}

	@Override
	public void use(Character character) {
		character.makeInvincible();
	}

	@Override
	public int getPrice() {
		return NOCOST;
	}

	@Override
	public int getSellPrice() {
		return SELLPRICE;
	}

	@Override
	public int getReplaceCost() {
		return (int)(SELLPRICE * REPLACEPERCENTAGE);
	}
    
}
