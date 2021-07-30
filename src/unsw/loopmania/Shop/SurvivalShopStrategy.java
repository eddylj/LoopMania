package unsw.loopmania.Shop;

import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Items.Item;

public class SurvivalShopStrategy implements ShopStrategy{
    private boolean available;
    private Character character;

    public SurvivalShopStrategy(Character character) {
        available = true;
        this.character = character;
    }

    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem.isPotion()) {
            available = false;
        }
    }

    @Override
    public void restock() {
        available = true;
        
    }

    @Override
    public Boolean getAvailable(Item item) {
        if (item.isPotion()) {
            return available && character.getGold() >= item.getPrice();
        }
        else {
            return character.getGold() >= item.getPrice();
        }
    }
}
