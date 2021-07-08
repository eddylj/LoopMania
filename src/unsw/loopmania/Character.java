package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int experience;
    private int gold;
    private Equipment equippedWeapon;
    private Equipment equippedHelmet;
    private Equipment equippedShield;
    private List<Enemy> enemies;
    private List<Card> cards;
    private List<Equipment> stored;
    private List<AlliedSoldier> alliedSoldiers;
    private BonusDamageStrategy applyBuffs;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position, 100);
        experience = 0;
        gold = 0;
        equippedWeapon = new Sword();
        equippedHelmet = null;
        equippedShield = null;
        enemies = new ArrayList<Enemy>();
        cards = new ArrayList<Card>();
        stored = new ArrayList<Equipment>;
        alliedSoldiers = new ArrayList<AlliedSoldier>();
        applyBuffs = new NormalState();
    }

    public void loseHealth(int health){

    }

    public void restoreHealth(int health) {

    }

    public void gainGold(int amount) {

    }

    public void gainXP(int amount) {

    }

    public Equipment getHighestLevelEquipment(Equipment item) {
        
    }

    public void buyItem (Equipment equipment) {

    }


    
}
