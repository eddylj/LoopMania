package unsw.loopmania.Shop;

import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Items.Item;

public class NormalShopStrategy implements ShopStrategy{
    private Character character;
    private boolean available;

    public NormalShopStrategy(Character character) {
        this.character = character;
        available = true;
        
    }

    /**
     * Items can be bought regularly
     */
    @Override
    public void buyItem(Item purchasedItem) {
        // available doesn't change in normal shop    
    }

    @Override
    public void restock() {
        available = true;
    }
    
    /**
     * Checks whether character has enough gold to purchase item
     */
    @Override
    public Boolean getAvailable(Item item) {
        return character.getGold() >= item.getPrice();
    }
    
}
