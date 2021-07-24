package unsw.loopmania;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BeserkerShopStrategy implements ShopStrategy{
    private boolean available;
    private Character character;

    public BeserkerShopStrategy(Character character) {
        available = true;
        this.character = character;
    }

    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem instanceof Protection) {
            available = false;
        }
    }

    @Override
    public void restock() {
        available = true;
    }

    @Override
    public Boolean getAvailable(Item item) {
        if (item instanceof Protection) {
            return available && (character.getGold() >= item.getPrice());
        }
        else {
            return character.getGold() >= item.getPrice();
        }
    }

    



}