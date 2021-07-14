package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int experience;
    private int gold;
    private int cycles;
    private Item equippedWeapon;
    private Item equippedHelmet;
    private Item equippedShield;
    private Item equippedArmour;
    private List<Enemy> enemies;
    private List<Card> cards;
    private List<Item> stored;
    private List<AlliedSoldier> alliedSoldiers;
    private BonusDamageStrategy applyBuffs;
    private Enemy attackingEnemy;
    private CharacterStats stats;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position, 100);
        experience = 0;
        gold = 0;
        cycles = 0;
        equippedWeapon = new Sword(1);
        equippedHelmet = null;
        equippedShield = null;
        enemies = new ArrayList<Enemy>();
        cards = new ArrayList<Card>();
        stored = new ArrayList<Item>();
        alliedSoldiers = new ArrayList<AlliedSoldier>();
        applyBuffs = new NormalState();
        stats = new CharacterStats();
    }

    public int getHealth() {
        return 0;
    }

    public int getXP() {
        return 0;
    }

    public int getGold() {
        return gold;
    }

    public int getCycles() {
        return cycles;
    }

    public void loseHealth(int health){

    }

    public int getHighestLevel(Item item) {
        return stats.getHighest(item.getType());
    }

    public int getHighestLevel(String item) {
        return stats.getHighest(item);
    }

    public void updateHighest(Item item) {
        stats.updateHighest(item);
    }

    public void restoreHealth(int health) {

    }

    public void gainGold(int amount) {

    }

    public void gainXP(int amount) {

    }

    public void gainCycle(int amount) {

    }

    public Item getHighestLevelEquipment(Item item) {
        return null;
    }

    public void buyItem (Item equipment) {

    }

    public List<Enemy> getEnemies() {
        return null;
    }

    public void gainAlliedSoldier(AlliedSoldier ally) {

    }
    public int numAlliedSoldiers() {
        return 0;
    }

    public void fight() {

    }

    public void sellItem(Item i) {

    }

    public void pickup(Card card) {

    }

    public void pickup(Item item) {
        
    }

    public Character() {
        super(100);
        experience = 0;
        gold = 0;
        equippedWeapon = new Sword(1);
        equippedHelmet = null;
        equippedShield = null;
        enemies = new ArrayList<Enemy>();
        cards = new ArrayList<Card>();
        stored = new ArrayList<Item>();
        alliedSoldiers = new ArrayList<AlliedSoldier>();
        applyBuffs = new NormalState();
        stats = new CharacterStats();
    }

    public Character(Enemy attacker, List<Enemy> supporting) {
        super(100);
        experience = 0;
        gold = 0;
        equippedWeapon = new Sword(1);
        equippedHelmet = null;
        equippedShield = null;
        enemies = new ArrayList<Enemy>();
        cards = new ArrayList<Card>();
        stored = new ArrayList<Item>();
        alliedSoldiers = new ArrayList<AlliedSoldier>();
        applyBuffs = new NormalState();
        for (Enemy e : supporting) {
            enemies.add(e);
        }
        attackingEnemy = attacker;
        stats = new CharacterStats();
    }
    
    public Character(Enemy attacker, List<Enemy> supporting, List<Item> equipment) {
        super(100);
        experience = 0;
        gold = 0;
        equippedWeapon = new Sword(1);
        equippedHelmet = null;
        equippedShield = null;
        equippedArmour = null;
        enemies = new ArrayList<Enemy>();
        cards = new ArrayList<Card>();
        stored = new ArrayList<Item>();
        alliedSoldiers = new ArrayList<AlliedSoldier>();
        applyBuffs = new NormalState();
        for (Enemy e : supporting) {
            enemies.add(e);
        }
        attackingEnemy = attacker;
        for (Item i : equipment) {
            if (i instanceof Weapon) {
                equippedWeapon = i;
            }
            else if (i instanceof Helmet) {
                equippedHelmet = i;
            }
            else if (i instanceof Shield) {
                equippedShield = i;
            }
            else if (i instanceof Armour) {
                equippedArmour = i;
            }
        }
        stats = new CharacterStats();
    }

    public Character(List<Item> equipment) {
        super(100);
        experience = 0;
        gold = 0;
        equippedWeapon = new Sword(1);
        equippedHelmet = null;
        equippedShield = null;
        equippedArmour = null;
        enemies = new ArrayList<Enemy>();
        cards = new ArrayList<Card>();
        stored = new ArrayList<Item>();
        alliedSoldiers = new ArrayList<AlliedSoldier>();
        applyBuffs = new NormalState();
        for (Item i : equipment) {
            if (i instanceof Weapon) {
                equippedWeapon = i;
            }
            else if (i instanceof Helmet) {
                equippedHelmet = i;
            }
            else if (i instanceof Shield) {
                equippedShield = i;
            }
            else if (i instanceof Armour) {
                equippedArmour = i;
            }
        }
        stats = new CharacterStats();
    }

    public void attack(Enemy enemy) {

    }
    
    public void takeDamage(int damage) {
        
    }

    public Integer numEquipmentInInventory() {
        return null;
    }

    public void equip(Item i) {
    }

    public Integer getNumEquipped() {
        return null;
    }

    public Integer getHighestLevel(Item sword) {
        return null;
    }

    public void unequip(Item sword) {
    }

    public Integer getNumStored() {
        return null;
    }

    public Object getEquippedWeapon() {
        return null;
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
     * spawn a sword in the world and return the sword entity
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Sword addUnequippedSword(int level){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new sword, as we know we have at least made a slot available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), level);
        unequippedInventoryItems.add(sword);
        return sword;
    }

    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }
    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item){
        item.destroy();
        unequippedInventoryItems.remove(item);
    }
        /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Entity e: unequippedInventoryItems){
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }
    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
    * @param index index from 0 to length-1
    */
   private void removeItemByPositionInUnequippedInventoryItems(int index){
       Entity item = unequippedInventoryItems.get(index);
       item.destroy();
       unequippedInventoryItems.remove(index);
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
}
