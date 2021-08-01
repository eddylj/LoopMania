package unsw.loopmania.Shop;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Items.*;

/**
 * Strategy pattern that runs the shop in berserker game mode.
 * @author Group FRIDGE
 */
public class BeserkerShopStrategy implements ShopStrategy{
    private BooleanProperty available;
    private Character character;

    public BeserkerShopStrategy(Character character) {
        available = new SimpleBooleanProperty(true);
        this.character = character;
    }

    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem.isProtection()) {
            available.set(false);
        }
    }

    @Override
    public void restock() {
        available.set(true);
    }

    @Override
    public BooleanBinding getAvailable(Item item) {
        if (item.isProtection()) {
            return available.and(character.getGoldProperty().greaterThanOrEqualTo(item.getPrice()));
        }
        else {
            return Bindings.greaterThanOrEqual(character.getGoldProperty(), item.getPrice());
        }
    }

}
