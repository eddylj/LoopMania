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
    private BonusDamageStrategy applyBuffs;
    private CharacterStats stats;
    private SimpleIntegerProperty aliveSoldiers;
    private List<AlliedSoldier> soldiers;

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
        List<Item> stored = new ArrayList<Item>();
        applyBuffs = new NormalState();
        stats = new CharacterStats();
        soldiers = new ArrayList<AlliedSoldier>();
        aliveSoldiers = new SimpleIntegerProperty(0);
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
        List<Item> stored = new ArrayList<Item>();
        applyBuffs = new NormalState();
        stats = new CharacterStats();
        soldiers = new ArrayList<AlliedSoldier>();
        aliveSoldiers = new SimpleIntegerProperty(0);
    }

    public List<AlliedSoldier> getSoldiers() {
        return soldiers;
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
            if (((Staff)equippedWeapon).castSpell(enemy, b, getCycles())) {
                return;
            }
            newDamage = ((Staff)equippedWeapon).getDamage();
        }

        if (!Objects.isNull(equippedHelmet)) {
            newDamage = ((Helmet) equippedHelmet).calcAttackDamage(newDamage);
        }
        enemy.takeDamage(newDamage);
    }

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

    public void loseHealth(double damage){
        health -= (int)damage;
    }

    public boolean hasring() {
        return false;
    }

    public int getHighestLevel(Item item) {
        return stats.getHighest(((StaticEntity)item).getType());
    }

    public int getHighestLevel(String item) {
        return stats.getHighest(item);
    }

    public void updateHighest(Item item) {
        stats.updateHighest(item);
    }

    public void restoreHealth(int health) {

    }

    public void setHealth(int i) {
    } 
    

    public Integer numEquipmentInInventory() {
        return null;
    }

    public void equip(Item i) {
    }

    public Integer getNumEquipped() {
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

    public void gainGold(int amount) {

    }

    public void gainXP(int amount) {

    }

    public void gainCycle() {

    }


    public void buyItem (Item equipment) {

    }


    public void sellItem(Item i) {

    }

    public void pickup(Card card) {

    }

    public void pickup(Item item) {
        
    }

    public BonusDamageStrategy getBonusDamageStrategy() {
        return applyBuffs;
    }

    public void setBonusDamageStrategy(BonusDamageStrategy buff) {
        applyBuffs = buff;
    }

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
            soldiers.add(soldier);
            aliveSoldiers.set(soldiers.size());
        }
    }
    public List<AlliedSoldier> getAlliedSoldiers() {
        return soldiers;
    }

}