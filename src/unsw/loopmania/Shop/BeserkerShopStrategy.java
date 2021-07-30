package unsw.loopmania.Shop;

import unsw.loopmania.Character;
import unsw.loopmania.Items.Item;

/**
 * Strategy pattern that runs the shop in berserker game mode.
 * @author Group FRIDGE
 */
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
        if (item.isProtection()) {
            return available && (character.getGold() >= item.getPrice());
        }
        else {
            return character.getGold() >= item.getPrice();
        }
    }

    



}
