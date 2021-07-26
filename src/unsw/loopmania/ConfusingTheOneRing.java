package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingTheOneRing extends TheOneRing{
    private Item additional;
    
    public ConfusingTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y, Item additional) {
        super(x, y);
        this.additional = additional;
    }

    @Override
    public boolean isWeapon() {
        return additional instanceof Weapon;
    }

    @Override 
    public boolean isShield() {
        return additional instanceof Shield;
    }
}
