package unsw.loopmania;

public class Shop {

    private CharacterStats stats;
    private Character character;
    itemFactory i = new itemFactory();
    Inventory inventory;
    public Shop(CharacterStats stats, Character character, Inventory inventory) {
        this.stats = stats;
        this.inventory = character.getInventory();
        this.character = character;
    }

    public void buy(String item) {
        int level = stats.getHighestLevel(item);
        Item purchasedItem = (Item)inventory.addUnequippedItem(item, level+1);
        stats.updateHighestLevel(purchasedItem);
        int Price = purchasedItem.getSellPrice();
        character.loseGold(Price);

    }
}
