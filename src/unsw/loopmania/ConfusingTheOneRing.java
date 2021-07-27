package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingTheOneRing extends TheOneRing implements ConfusedRareItem{
    private Item additional;
    
    public ConfusingTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y, Item additional) {
        super(x, y);
        this.additional = additional;
    }

    @Override
    public Item getAdditional() {
        return additional;
    }

	@Override
	public double protect(double damage, Enemy e) {
		return ((TreeStump)additional).protect(damage, e);
	}

	@Override
	public double getDamage(Enemy e) {
		return ((Anduril)additional).getDamage(e);
	}

    @Override
    public void use(Character character) {
        ((InvinciblePotion)additional).use(character);
    }
}
