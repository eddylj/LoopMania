package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingTreeStump extends Shield implements ConfusedRareItem{
    private Item additional;
	public ConfusingTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y, int level, Item additional) {
		super(x, y, level);
        this.additional =additional;
	}

    @Override
    public boolean isWeapon() {
        return additional instanceof Weapon;
    }

    @Override
    public boolean isRing() {
        return additional instanceof TheOneRing;
    }

    @Override
    public boolean isNuke() {
        return true;
    }

	@Override
	public double getDamage(Enemy e) {
		return ((Anduril)additional).getDamage(e);
	}
    
}
