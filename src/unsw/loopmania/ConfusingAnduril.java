package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingAnduril extends Anduril{
    private Item additional;

	public ConfusingAnduril(SimpleIntegerProperty x, SimpleIntegerProperty y, int level, Item additional) {
		super(x, y, level);
        this.additional = additional;
	}

    @Override
    public boolean isShield() {
        return additional instanceof Shield;
    }
    
    @Override
    public boolean isRing() {
        return additional instanceof TheOneRing;
    }
    
}
