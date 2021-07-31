package unsw.loopmania.Shop;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import unsw.loopmania.Items.Item;

public interface ShopStrategy {
    public void buyItem(Item purchasedItem);
    public void restock();
    public BooleanBinding getAvailable(Item item);
}
