package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Enemies.Enemy;

public class ConfusingTreeStump extends Shield implements ConfusedRareItem{
    private Item additional;
	public ConfusingTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y, int level, Item additional) {
		super(x, y, level);
        super.setType("treestump");
        this.additional = additional;
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