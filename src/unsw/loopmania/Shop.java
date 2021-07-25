package unsw.loopmania;

import static org.junit.jupiter.api.DynamicTest.stream;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import test.HealthPotionTest;

public class Shop {
    private CharacterStats stats;
    private Character character;
    private ItemFactory i;
    private ShopStrategy available;
    private int boughtHealthPotions;
    Inventory inventory;

    /**
     * 
     * @param character
     */
    public Shop(Character character) {
        this.stats = character.getStats();
        this.inventory = character.getInventory();
        this.character = character;
        this.available = new NormalShopStrategy(character);
        this.boughtHealthPotions = 0;
        // this.available = new SurvivalShopStrategy(character);
        i = new ItemFactory();
    }
    /**
     * Gets purchase cost of item
     * @param item
     * @return cost to buy item
     */
    public int getBuyPrice(String item) {
        return previewItem(item).getPrice();
        // if (item.equals("healthpotion")) {
        //     return previewItem(item).getPrice() + 50 * boughtHealthPotions;
        // }
        // else {
        //     return previewItem(item).getPrice();
        // }
    }

    private Item previewItem(String itemType) {
        Item item = null;
        if (itemType.equals("healthpotion")) {
            item = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), itemType);
            ((HealthPotion)item).increaseCost(boughtHealthPotions);
        }
        else {
            int level = stats.getHighestLevel(itemType);
            item = i.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), itemType, level + 1);
        }
        return item;
    }

    public int getItemBuyLevel(String item) {
        int level = stats.getHighestLevel(item)+1;
        if (level > 10) {
            return 10;
        }
        return level;
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
        int price = getBuyPrice(item);
        character.loseGold(price);
        available.buyItem(purchasedItem);
        if (item.equals("healthpotion")) {
            System.out.println("Just bought healthpotion");
            boughtHealthPotions++;
        }
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
