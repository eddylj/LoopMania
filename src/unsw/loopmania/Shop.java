package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Shop {
    private CharacterStats stats;
    private Character character;
    private ItemFactory i;
    Inventory inventory;

    /**
     * 
     * @param character
     */
    public Shop(Character character) {
        this.stats = character.getStats();
        this.inventory = character.getInventory();
        this.character = character;
        i = new ItemFactory();
    }
    /**
     * Gets purchase cost of item
     * @param item
     * @return cost to buy item
     */
    public int getBuyPrice(String item) {
        int price;
        if (item.equals("healthpotion")) {
            price = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), item).getPrice();
        }
        else {
            int level = stats.getHighestLevel(item);
            price = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), item, level + 1).getPrice();
        }
        return price;
    }

    public int getItemBuyLevel(String item) {
        int level = stats.getHighestLevel(item);
        return level + 1;
    }

    /**
     * Buys item from shop and adds it to inventory
     * @param item
     * @return item purchased
     */
    public Item buy(String item) {
        int level = stats.getHighestLevel(item);
        Item purchasedItem = (Item)inventory.addUnequippedItem(item, level+1);
        stats.updateHighestLevel(purchasedItem);
        int price = purchasedItem.getPrice();
        character.loseGold(price);
        return purchasedItem;
    }

    public int getSellPrice(Item item) {
        int price = item.getSellPrice();
        return price;
    }
    /**
     * Sells item in inventory
     * @param item
     */
    public void sell(Item item) {
        int price = item.getSellPrice();
        ((Entity)item).destroy();
        character.getunequippedInventoryItems().remove(item);
        character.gainGold(price);
    }
}
