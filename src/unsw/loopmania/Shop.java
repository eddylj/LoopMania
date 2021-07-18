package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Shop {

    private CharacterStats stats;
    private Character character;
    private itemFactory i;
    Inventory inventory;

    public Shop(Character character) {
        this.stats = character.getStats();
        this.inventory = character.getInventory();
        this.character = character;
        i = new itemFactory();
    }

    public int getBuyPrice(String item) {
        int price;
        if (item.equals("healthpotion")) {
            price = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), item).getPrice();
        }
        else {
            int level = stats.getHighestLevel(item);
            price = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), item, level).getPrice();
        }
        return price;
    }

    public Item buy(String item) {
        int level = stats.getHighestLevel(item);
        Item purchasedItem = (Item)inventory.addUnequippedItem(item, level+1);
        stats.updateHighestLevel(purchasedItem);
        int price = purchasedItem.getSellPrice();
        character.loseGold(price);
        return purchasedItem;
    }

    public int getSellPrice(Item item) {
        int price = item.getSellPrice();
        return price;
    }

    public void sell(Item item) {
        ((Entity)item).destroy();
        character.getunequippedInventoryItems().remove(item);
    }
}
