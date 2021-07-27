package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Potion extends Item{

    public Potion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);

    }
    
    public Potion() {
        super();
    }

    public abstract void increaseCost(int timesBought);
    public abstract void use(Character character);
}
