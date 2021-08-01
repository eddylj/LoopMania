package unsw.loopmania.Shop;

import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Items.*;

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

    /**
     * Stops player from buying multiple Protection items.
     * Don't need to use purchasedItem.isProtection() because Rare
     * items can never be bough
     * @param purchasedItem the item being purchased
     */
    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem instanceof Protection) {
            available = false;
        }
    }

    /**
     * Makes Protection items available to purchase again
     */
    @Override
    public void restock() {
        available = true;
    }

    /**
     * Checks whether item is available to buy.
     * Takes whether item is protection, price and player's gold into account.
     */
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
