package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingNuke extends Nuke implements ConfusedRareItem{
    private Item additional;
	public ConfusingNuke(SimpleIntegerProperty x, SimpleIntegerProperty y, Item additional) {
		super(x, y);
        this.additional = additional;
	}

	@Override
	public double protect(double damage, Enemy e) {
		return ((TreeStump)additional).protect(damage, e);
	}

	@Override
	public double getDamage(Enemy e) {
		return ((Anduril)additional).getDamage(e);
	}
    
    @Override
    public boolean isWeapon() {
        return additional.isWeapon();
    }

    @Override
    public boolean isShield() {
        return additional.isShield();
    }

    @Override
    public boolean isRing() {
        return additional.isRing();
    }
}
