package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Potion extends StaticEntity implements Item {
    
    public Potion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setType("healthpotion");
    }

    public Potion() {
        super();
        super.setType("healthpotion");
    }

    public void heal(Character character) {

    }
    
    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public int getReplaceCost() {
        return 0;
    }
}
