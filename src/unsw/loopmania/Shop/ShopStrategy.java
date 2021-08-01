package unsw.loopmania.Shop;

import unsw.loopmania.Items.Item;

/**
 * Strategy which allows for blocking user from rebuying certain items.
 */
public interface ShopStrategy {
    public void buyItem(Item purchasedItem);
    public void restock();
    public Boolean getAvailable(Item item);
}
