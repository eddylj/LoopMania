package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class StandardRareItemFactory implements RareItemFactoryStrategy{
	private RareItemFactory rF;

	public StandardRareItemFactory(RareItemFactory rF) {
		this.rF = rF;
	}

	@Override
	public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        return rF.createStandard(x, y, type);
    }

	
    
}
