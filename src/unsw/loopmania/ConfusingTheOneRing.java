package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingTheOneRing extends TheOneRing implements ConfusedRareItem{
    private Item additional;
    
    public ConfusingTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y, Item additional) {
        super(x, y);
        this.additional = additional;
    }

    @Override
    public boolean isWeapon() {
        return additional instanceof Weapon;
    }

    @Override 
    public boolean isShield() {
        return additional instanceof Shield;
    }

	@Override
	public double protect(double damage, Enemy e) {
		return ((TreeStump)additional).protect(damage, e);
	}

	@Override
	public double getDamage(Enemy e) {
		return ((Anduril)additional).getDamage(e);
	}
}
