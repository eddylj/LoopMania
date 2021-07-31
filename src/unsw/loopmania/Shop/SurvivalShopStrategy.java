package unsw.loopmania.Shop;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Items.Item;

public class SurvivalShopStrategy implements ShopStrategy{
    private BooleanProperty available;
    private Character character;

    public SurvivalShopStrategy(Character character) {
        available = new SimpleBooleanProperty(true);
        this.character = character;
    }

    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem.isPotion()) {
            available.set(false);
        }
    }

    @Override
    public void restock() {
        available.set(true);
    }

    @Override
    public BooleanBinding getAvailable(Item item) {
        if (item.isPotion()) {
            return available.and(character.getGoldProperty().greaterThanOrEqualTo(item.getPrice()));
        }
        else {
            return Bindings.greaterThanOrEqual(character.getGoldProperty(), item.getPrice());
        }
    }
}
