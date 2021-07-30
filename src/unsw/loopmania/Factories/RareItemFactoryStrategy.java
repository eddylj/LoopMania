package unsw.loopmania.Factories;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Items.Item;

public interface RareItemFactoryStrategy {
    public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type);
}
