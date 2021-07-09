package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int experience;
    private int gold;
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

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position, 100);
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
    }

    public int getHealth() {
        return 0;
    }

    public int getXP() {
        return 0;
    }

    public int getGold() {
        return 0;
    }

    public void loseHealth(int health){

    }

    public void restoreHealth(int health) {

    }

    public void gainGold(int amount) {

    }

    public void gainXP(int amount) {

    }

    public Item getHighestLevelEquipment(Item item) {
        return null;
    }

    public void buyItem (Item equipment) {

    }

    public List<Enemy> getEnemies() {
        return null;
    }

    public int getNumEquipmentInInventory() {
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
    
}
