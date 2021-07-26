package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingTreeStump extends Shield{
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
    
}
