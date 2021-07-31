package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Heroes.Character;

public abstract class Potion extends Item{
    public Potion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);

    }
    
    public Potion() {
        super();
    }

    public abstract void reset_cost();
    public abstract void increaseCost(int timesBought);
    public abstract void use(Character character);
}
