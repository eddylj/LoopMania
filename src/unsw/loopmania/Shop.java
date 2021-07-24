package unsw.loopmania;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Shop {
    private CharacterStats stats;
    private Character character;
    private ItemFactory i;
    private ShopStrategy available;
    Inventory inventory;

    /**
     * 
     * @param character
     */
    public Shop(Character character) {
        this.stats = character.getStats();
        this.inventory = character.getInventory();
        this.character = character;
        // this.available = new NormalShopStrategy();
        this.available = new SurvivalShopStrategy(character);
        i = new ItemFactory();
    }
    /**
     * Gets purchase cost of item
     * @param item
     * @return cost to buy item
     */
    public int getBuyPrice(String item) {
        return previewItem(item).getPrice();
    }

    private Item previewItem(String itemType) {
        Item item = null;
        if (itemType.equals("healthpotion")) {
            item = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), itemType);
        }
        else {
            int level = stats.getHighestLevel(itemType);
            item = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), itemType, level + 1);
        }
        return item;
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
        available.buyItem(purchasedItem);
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

    public BooleanProperty canBuy(String item) {
        // return new SimpleBooleanProperty(vailable.getAvailable(previewItem(item)));
        return new SimpleBooleanProperty(available.getAvailable(previewItem(item)));
    }

    public void restock() {
        available.restock();
    }

    public void setSurvival() {
        available = new SurvivalShopStrategy(character);
    }

    public void setBeserker() {
        available = new BeserkerShopStrategy(character);
    }
}
