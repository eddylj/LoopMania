package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Item extends StaticEntity{

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public Item() {
        super();
    }
    public abstract int getPrice();
    public abstract int getSellPrice();
    public abstract int getReplaceCost();

    public boolean isWeapon() {
        return false;
    }
    public boolean isShield() {
        return false;
    }
    public boolean isRing() {
        return false;
    }
    public boolean isNuke() {
        return false;
    }
}
