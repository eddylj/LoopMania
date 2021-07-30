package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.ConfusedRareItem;
import unsw.loopmania.InvinciblePotion;
import unsw.loopmania.Shield;
import unsw.loopmania.Enemies.Enemy;

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
