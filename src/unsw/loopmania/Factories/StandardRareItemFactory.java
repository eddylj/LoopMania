package unsw.loopmania.Factories;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Items.Item;

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
