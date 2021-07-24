package unsw.loopmania;


public interface ShopStrategy {
    public void buyItem(Item purchasedItem);
    public void restock();
    public Boolean getAvailable(Item item);
}
