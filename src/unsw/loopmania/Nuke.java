package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Nuke extends StaticEntity implements Item, RareItem {
    public static final int NOPRICE = 0;
    public static final int NUKESELLPRICE = 1000;
    public static final double NUKEREPLACEPERCENTAGE = 0.4;


    public Nuke(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("nuke");
    }

    public Nuke() {
        super();
        super.setType("nuke");
    }

	@Override
	public int getPrice() {
		return NOPRICE;
	}

	@Override
	public int getSellPrice() {
		return NUKESELLPRICE;
	}

	@Override
	public int getReplaceCost() {
		return (int)(NUKESELLPRICE * NUKEREPLACEPERCENTAGE);
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

    @Override
    public boolean isNuke() {
        return true;
    }
    
}
