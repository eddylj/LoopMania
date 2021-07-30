package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.ConfusedRareItem;
import unsw.loopmania.InvinciblePotion;
import unsw.loopmania.Nuke;
import unsw.loopmania.TreeStump;
import unsw.loopmania.Enemies.Enemy;

public class ConfusingNuke extends Nuke implements ConfusedRareItem{
    private Item additional;
	public ConfusingNuke(SimpleIntegerProperty x, SimpleIntegerProperty y, Item additional) {
		super(x, y);
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
    
    @Override
    public void use(Character character) {
        ((InvinciblePotion)additional).use(character);
    }
}
