package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Potion extends StaticEntity implements Item{

    public Potion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);

    }
    
    public Potion() {
        super();
    }

    @Override
	public boolean isWeapon() {
		return false;
	}

	@Override
	public boolean isShield() {
		return false;
	}

	@Override
	public boolean isRing() {
		return false;
	}

    @Override
    public boolean isNuke() {
        return false;
    }

    public abstract void increaseCost(int timesBought);
    public abstract void use(Character character);
}
