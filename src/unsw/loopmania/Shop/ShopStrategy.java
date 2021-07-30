package unsw.loopmania.Shop;

import unsw.loopmania.Items.Item;

public interface ShopStrategy {
    public void buyItem(Item purchasedItem);
    public void restock();
    public Boolean getAvailable(Item item);
}
