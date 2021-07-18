package unsw.loopmania;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;


public class Inventory {
    private List<Item> unequippedInventoryItems;
    private Character character;
    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;
    private List<Card> cardEntities;
    private List<String> nonLevelItems;



    public Inventory(Character character) {
        unequippedInventoryItems = new ArrayList<>();
        this.character = character;
        cardEntities = new ArrayList<>();
        nonLevelItems = new ArrayList<String>(Arrays.asList("healthpotion", "theonering"));
    }

    

    public StaticEntity loadCard(String type, int width) {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= width){
            int gold = LoopManiaWorld.getRandNum() + 1;
            character.gainGold(gold * 3);
            // TODO = give some cash/experience/item rewards for the discarding of the oldest card
            removeCard(0);
        }
        CardFactory cf = new CardFactory();
        // Card card = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        Card card = cf.create(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0), type);
        cardEntities.add(card);
        return (StaticEntity)card;
    }
    public Card getMatchingCard(int cardNodeX, int cardNodeY) {
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                return c;
            }
        }
        return null;
    }

    public void destroyCard(Card card, int cardNodeX) {
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);
    }

    public Card getCardByCoordinate(int x) {
        for (Card c : cardEntities) {
            if (c.getX() == x) {
                return c;
            }
        }
        return null;
    }

    public List<String> getNonLevelItems() {
        return nonLevelItems;
    }

    public void removeUnequippedItem(Item removeItem) {
        unequippedInventoryItems.remove(removeItem);
    }



    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }


    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }


    public StaticEntity addUnequippedItem (String type, int level){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        // now we insert the new sword, as we know we have at least made a slot available...
        itemFactory f = new itemFactory();
        Item item = null;
        if (nonLevelItems.contains(type)) {
            item = f.create(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), type);
        }
        else {
            item = f.create(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), type, level);
        }
        // Item item = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), level);
        unequippedInventoryItems.add(item);
        return (StaticEntity)item;
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Item item = unequippedInventoryItems.get(index);
        ((StaticEntity)item).destroy();
        // int goldAmount = item.getReplaceCost();
        character.gainGold(item.getReplaceCost());
        unequippedInventoryItems.remove(index);
    }
    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */

    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Item item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    public Item getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Item e: unequippedInventoryItems){
            StaticEntity entity = convertItemToStaticEntity(e);
            if ((entity.getX() == x) && (entity.getY() == y)){
                return e;
            }
        }
        return null;
    }

    private StaticEntity convertItemToStaticEntity(Item item) {
        return (StaticEntity)item;
    }
    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Item item){
        // item.destroy();
        unequippedInventoryItems.remove(item);
    }

    public List<Item> getunequippedInventoryItems() {
        return unequippedInventoryItems;
    }

    public HealthPotion getHealthPotion() {
        for (int i = unequippedInventoryItems.size() - 1; i >= 0; i--) {
            Item item = unequippedInventoryItems.get(i);
            if (item instanceof HealthPotion) {
                ((Entity)item).destroy();
                return ((HealthPotion)item);
            }
        }
        return null;
    }

    public int getUnequippedInventoryItemsNum() {
        return unequippedInventoryItems.size();
    }

    public int getCardsNum() {
        return cardEntities.size();
    }

    public static int getunequippedInventoryWidth() {
		return Inventory.unequippedInventoryWidth;
	}



    public static int getunequippedInventoryHeight() {
		return Inventory.unequippedInventoryHeight;
	}



}
