package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingInvinciblePotion extends InvinciblePotion implements ConfusedRareItem{
    private Item additional;

    public ConfusingInvinciblePotion(SimpleIntegerProperty x, SimpleIntegerProperty y, Item additional) {
        super(x, y);
        super.setType("invinciblepotion");
        this.additional = additional;
    }

    @Override
    public Item getAdditional() {
        return additional;
    }

	@Override
	public double protect(double damage, Enemy enemy) {
		return ((TreeStump)additional).protect(damage, enemy);
	}

	@Override
	public double getDamage(Enemy enemy) {
		return ((Anduril)additional).getDamage(enemy);
	}

}
