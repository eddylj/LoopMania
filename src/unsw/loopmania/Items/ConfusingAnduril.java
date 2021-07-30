package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.Enemies.*;
import unsw.loopmania.Heroes.Character;

public class ConfusingAnduril extends Anduril implements ConfusedRareItem{
    private Item additional;

	public ConfusingAnduril(SimpleIntegerProperty x, SimpleIntegerProperty y, int level, Item additional) {
		super(x, y, level);
        super.setType("anduril");
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
    public void use(Character character) {
        ((InvinciblePotion)additional).use(character);
    }
}
