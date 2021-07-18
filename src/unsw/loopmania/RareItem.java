package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class RareItem extends StaticEntity implements Item{

    public RareItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public int getPrice() {
        return -1;
    }

    @Override
    public abstract int getSellPrice();

    @Override
    public abstract int getReplaceCost();
    
}
