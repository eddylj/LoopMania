package unsw.loopmania;

public class SurvivalShopStrategy implements ShopStrategy{
    private boolean available;
    private Character character;

    public SurvivalShopStrategy(Character character) {
        available = true;
        this.character = character;
    }

    @Override
    public void buyItem(Item purchasedItem) {
        if (purchasedItem instanceof HealthPotion) {
            available = false;
            System.out.println("YOOOhooo\n\nuh oh");
        }
    }

    @Override
    public void restock() {
        available = true;
        
    }

    @Override
    public Boolean getAvailable(Item item) {
        System.out.println(String.format("COSTS: %s : %s", character.getGold(), item.getPrice()));
        if (item instanceof HealthPotion) {
            System.out.println(available);
            return available && character.getGold() >= item.getPrice();
        }
        else {
            return character.getGold() >= item.getPrice();
        }
    }
}
