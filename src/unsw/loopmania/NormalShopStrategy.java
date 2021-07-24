package unsw.loopmania;


public class NormalShopStrategy implements ShopStrategy{
    private Character character;
    private boolean available;

    public NormalShopStrategy(Character character) {
        this.character = character;
        available = true;
        
    }

    @Override
    public void buyItem(Item purchasedItem) {
        // available doesn't change in normal shop    
    }

    @Override
    public void restock() {
        available = true;
    }

    @Override
    public Boolean getAvailable(Item item) {
        return character.getGold() >= item.getPrice();
    }
    
}
