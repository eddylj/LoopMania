package unsw.loopmania;

import org.junit.platform.engine.support.hierarchical.EngineExecutionContext;
import java.util.Random;

public abstract class Enemy extends MovingEntity {
    private int battleRadius;
    private int supportRadius;
    public String[] itemList;
    
    private int attackDamage;
    private int goldAmount;
    private int allyTurnCount;

    public Enemy (PathPosition position, int battleRadius, int supportRadius, int attackDamage, int goldAmount, int health) {
        super(position, health);
        this.battleRadius = battleRadius;
        this.supportRadius = supportRadius;
        this.attackDamage  = attackDamage;
        this.goldAmount = goldAmount;
        itemList = new String[] {"sword", "stake", "staff", "shield", "helmet", "armour", "healthpotion"};
    }

    public Enemy (int battleRadius, int supportRadius, int attackDamage, int goldAmount, int health) {
        super(health);
        this.battleRadius = battleRadius;
        this.supportRadius = supportRadius;
        this.attackDamage  = attackDamage;
        this.goldAmount = goldAmount;
        itemList = new String[] {"sword", "stake", "staff", "shield", "helmet", "armour", "healthpotion"};
    }

    public int getHealth() {
        return health;
    }


    /**
     * Enemy attacks the character given
     * @param character
     * @return boolean if the character was killed returns true
     */

    public void attack (Hero h) {
        h.takeDamage(attackDamage);
    }

    public void attack(Hero h, BattleRunner b) {
        h.takeDamage(attackDamage);
        
    }
    /**
     * Enemy attacks the ally given
     * @param ally
     * @return boolean if the ally was killed returns true
     */


    /**
     * Returns the gold that the enemy drops when they die
     * @param gold
     * @return
     */
    public int getGold(int gold) {
        return goldAmount;
    }

    public int getBattleRadius() {
        return this.battleRadius;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }


    public int getSupportRadius() {
        return this.supportRadius;
    }


    /**
     * move the enemy
     */
    public void move(){
        int directionChoice = LoopManiaWorld.getRandNum() % 2;
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

    public abstract StaticEntity getLoot(Character character);

}
