package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public interface RareItemFactoryStrategy {
    public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type);
}
