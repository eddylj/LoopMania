package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Enemies.Enemy;

public class ConfusingTheOneRing extends TheOneRing implements ConfusedRareItem{
    private Item additional;
    
    public ConfusingTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y, Item additional) {
        super(x, y);
        super.setType("theonering");
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
