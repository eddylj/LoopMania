package unsw.loopmania;

public class Shop {

    private CharacterStats stats;
    private Character character;
    itemFactory i = new itemFactory();
    Inventory inventory;

    public Shop(Character character) {
        this.stats = character.getStats();
        this.inventory = character.getInventory();
        this.character = character;
    }

    public int getBuyPrice(String item) {
        int level = stats.getHighestLevel(item);
        Item purchasedItem = (Item)inventory.addUnequippedItem(item, level+1);
        int price = purchasedItem.getPrice();
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

    public Item sell(Item item) {
        
    }
}
