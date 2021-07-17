package unsw.loopmania;

public class Shop {

    private CharacterStats stats;
    private Character character;
    itemFactory i = new itemFactory();

    public Shop(CharacterStats stats, Character character) {
        this.stats = stats;
        this.character = character;
    }

    public void buy(String item) {
        int level = stats.getHighestLevel(item);
        Item purchasedItem = Inventory.addUnequippedItem(item, level+1);
        stats.updateHighestLevel(purchasedItem);
        int Price = purchasedItem.getSellPrice();
        character.loseGold(Price);

    }
}
