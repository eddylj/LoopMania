package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingRareItemFactory implements RareItemFactoryStrategy{
    private RareItemFactory rF;

    public ConfusingRareItemFactory(RareItemFactory rF) {
        this.rF = rF;
    }

	@Override
	public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
		return rF.createConfusing(x, y, type);
	}
    
}
