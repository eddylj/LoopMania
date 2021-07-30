package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ConfusingTreeStump extends Shield implements ConfusedRareItem{
    private Item additional;
	public ConfusingTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y, int level, Item additional) {
		super(x, y, level);
        this.additional =additional;
	}

    @Override
    public Item getAdditional() {
        return additional;
    }

	@Override
	public double getDamage(Enemy enemy) {
		return ((Anduril)additional).getDamage(enemy);
	}

    @Override
    public void use(Character character) {
        ((InvinciblePotion)additional).use(character);
    }
}
