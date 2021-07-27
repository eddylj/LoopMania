package unsw.loopmania;


public class BeserkerShopStrategy implements ShopStrategy{
    private boolean available;
    private Character character;

    public BeserkerShopStrategy(Character character) {
        available = true;
        this.character = character;
    }

    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem instanceof Protection) {
            System.out.println("protection bougt");
            available = false;
        }
    }

    @Override
    public void restock() {
        available = true;
    }

    @Override
    public Boolean getAvailable(Item item) {
        if (item.isProtection()) {
            return available && (character.getGold() >= item.getPrice());
        }
        else {
            return character.getGold() >= item.getPrice();
        }
    }

    



}
