package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements Hero {
    private int experience;
    private int gold;
    private int cycles;
    private Item equippedWeapon;
    private Item equippedHelmet;
    private Item equippedShield;
    private Item equippedArmour;
    private BonusDamageStrategy appliedBuff;
    private CharacterStats stats;
    private SimpleIntegerProperty aliveSoldiers;
    private List<AlliedSoldier> soldiers;
    private Inventory inventory;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position, 100);
        experience = 0;
        gold = 0;
        cycles = 0;
        equippedWeapon = null;
        equippedHelmet = null;
        equippedShield = null;
        List<Card> cards = new ArrayList<Card>();
        appliedBuff = new NormalState();
        stats = new CharacterStats();
        soldiers = new ArrayList<AlliedSoldier>();
        aliveSoldiers = new SimpleIntegerProperty(0);
        inventory = new Inventory(this);
    }
    public Character() {
        super(100);
        experience = 0;
        gold = 0;
        cycles = 0;
        equippedWeapon = null;
        equippedHelmet = null;
        equippedShield = null;
        List<Card> cards = new ArrayList<Card>();
        appliedBuff = new NormalState();
        stats = new CharacterStats();
        soldiers = new ArrayList<AlliedSoldier>();
        inventory = new Inventory(this);
        aliveSoldiers = new SimpleIntegerProperty(0);
    }



    public void takeDamage(double damage){
        double newDamage = damage;
        if (!Objects.isNull(equippedShield)) {
            newDamage = ((Protection) equippedShield).protect(damage);
        }
        if (!Objects.isNull(equippedHelmet)) {

            newDamage = ((Protection) equippedHelmet).protect(damage);
        }
        if (!Objects.isNull(equippedArmour)) {

            newDamage = ((Protection) equippedArmour).protect(damage);
        }
        super.takeDamage(newDamage);
    }

    public void attack(Enemy enemy, BattleRunner b) {
        double newDamage = 5;
        if (equippedWeapon instanceof Sword) {
            newDamage = ((Weapon)equippedWeapon).getDamage();
        }
        if (equippedWeapon instanceof Stake) {
            newDamage = ((Stake)equippedWeapon).getDamage(enemy);
        }
        if (equippedWeapon instanceof Staff) {
            if (((Staff)equippedWeapon).castSpell(enemy, b)) {
                return;
            }
            newDamage = ((Staff)equippedWeapon).getDamage();
        }

        if (!Objects.isNull(equippedHelmet)) {
            newDamage = ((Helmet) equippedHelmet).calcAttackDamage(newDamage);
        }
        newDamage = appliedBuff.ApplyBonusDamge(newDamage);
        enemy.takeDamage(newDamage);
    }



    public void drinkPotion() {
        
        HealthPotion potion = inventory.getHealthPotion();
        if (!Objects.isNull(potion)) {
            potion.heal(this);
        }
    }

    // STORE STUFF
    //////////////////////////////////
    public void sellItem(Item i) {
        
    }
    ////////////////////////////////////////

    // INVENTORY ITEM RELATED STUFF
    ////////////////////////////////////

    public boolean hasring() {
        return false;
    }


    ////////////////////////////////////    


    // SOLDIER STUFF
    ////////////////////////////////////
    public void updateAlliedSoldierAmount() {
        for (AlliedSoldier s : soldiers) {
            if (s.getHealth() <= 0) {
                soldiers.remove(s);
            }
        }
        aliveSoldiers.set(soldiers.size()); // observer pattern wooo
    }

    public void addAlliedSoldier(AlliedSoldier soldier) {
        if (aliveSoldiers.getValue() < 3) {
            soldiers.add(0,soldier);
            aliveSoldiers.set(soldiers.size());
        }
    }
    ////////////////////////////////////

    // Getters and Setters and other incrementors
    ///////////////////////////////////////////////////////
    public int getHealth() {
        return health;
    }
    public int getXP() {
        return experience;
    }
    public int getGold() {
        return gold;
    }
    public int getCycles() {
        return cycles;
    }
    public int getAlliedSoldierCount() {
        return aliveSoldiers.get();
    }
    public List<AlliedSoldier> getSoldiers() {
        return soldiers;
    }
    public List<AlliedSoldier> getAlliedSoldiers() {
        return soldiers;
    }
    public BonusDamageStrategy getBonusDamageStrategy() {
        return appliedBuff;
    }
    public void setBonusDamageStrategy(BonusDamageStrategy buff) {
        appliedBuff = buff;
    }
    public void setHealth(int i) {
        health = i;
    } 
    public void restoreHealth(int amount) {
        health += amount;
        if (health > 100) {
            setHealth(100);
        }
    }
    public void gainGold(int amount) {
        gold += amount;
    }
    public void loseGold(int amount) {
        gold -= amount;
    }
    public void gainXP(int amount) {
        experience += amount;
    }
    public void gainCycle() {
        cycles++;
    }
    public void loseHealth(double damage){
        health -= (int)damage;
    }
    public int getHighestLevel(Item item) {
        return stats.getHighestLevel(((StaticEntity)item).getType());
    }
    public int getHighestLevel(String item) {
        return stats.getHighestLevel(item);
    }
    public void updateHighest(Item item) {
        stats.updateHighestLevel(item);
    }
    public Item getWeapon() {
        return equippedWeapon;
    }
    public Item getShield() {
        return equippedShield;
    }

    public Item getArmour() {
        return equippedArmour;
    }

    public Item getHelmet() {
        return equippedHelmet;
    }

    public void equip(Item i) {
        inventory.removeUnequippedItem(i);
        stats.updateHighestLevel(i);
        if (i instanceof Weapon) {
            equippedWeapon = i;
        } else if (i instanceof Shield) {
            equippedShield = i;
        } else if (i instanceof Armour) {
            equippedArmour = i;
        } else {
            equippedHelmet = i;
        }
    }
    //////////////////////////////////////////////////////
    public List<Item> getunequippedInventoryItems() {
        return inventory.getunequippedInventoryItems();
    }
    public StaticEntity addUnequippedItem(String string, int i) {
        return inventory.addUnequippedItem(string, i);
    }
    public  Inventory getInventory() {
        return inventory;
    }
    public Item getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        return inventory.getUnequippedInventoryItemEntityByCoordinates(x, y);
    }
    public void removeUnequippedInventoryItemByCoordinates(int nodeX, int nodeY) {
        inventory.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }
    public StaticEntity loadCard(String type, int width) {
        return inventory.loadCard(type, width);
    }
    public Card getMatchingCard(int cardNodeX, int cardNodeY) {
        return inventory.getMatchingCard(cardNodeX, cardNodeY);
    }
    public void destroyCard(Card card, int cardNodeX) {
        inventory.destroyCard(card, cardNodeX);
    }
    public Card getCardByCoordinate(int x) {
        return inventory.getCardByCoordinate(x);
    }

    public int getUnequippedInventoryItemsNum() {
        return inventory.getUnequippedInventoryItemsNum();
    }

    public int getCardsNum() {
        return inventory.getCardsNum();
    }
}