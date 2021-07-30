package unsw.loopmania;

import java.util.List;


public abstract class Enemy extends MovingEntity {
    private int battleRadius;
    private int supportRadius;
    public String[] itemList;   
    private int attackDamage;
    private int goldAmount;
    private int xpAmount;

    public Enemy (PathPosition position, int battleRadius, int supportRadius, int attackDamage, int goldAmount, int health, int xp) {
        super(position, health);
        this.battleRadius = battleRadius;
        this.supportRadius = supportRadius;
        this.attackDamage  = attackDamage;
        this.goldAmount = goldAmount;
        this.xpAmount = xp;
        itemList = new String[] {"sword", "stake", "staff", "shield", "helmet", "armour", "healthpotion", "strengthpotion"};
    }

    public Enemy (int battleRadius, int supportRadius, int attackDamage, int goldAmount, int health, int xp) {
        super(health);
        this.battleRadius = battleRadius;
        this.supportRadius = supportRadius;
        this.attackDamage  = attackDamage;
        this.goldAmount = goldAmount;
        this.xpAmount = xp;
        itemList = new String[] {"sword", "stake", "staff", "shield", "helmet", "armour", "healthpotion", "strengthpotion"};
    }

    /**
     * Enemy attacks the character given
     * @param character
     * @return boolean if the character was killed returns true
     */

    public void attack (Hero hero) {
        hero.takeDamage(attackDamage, this);
    }

    public void attack(Hero hero, BattleRunner bR) {
        hero.takeDamage(attackDamage, this);

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
    /**
     * Generates random loot for player for zombie
     * @param character
     * @param width
     * @param rareItems
     * @return StaticEntity loot
     */
    public abstract List<StaticEntity> getLoot(Character character, int width, List<String> rareItems);

    // Getters and Setters
    
    public int getHealth() {
        return health.get();
    }
    public int getGold() {
        return goldAmount;
    }

    public int getXP() {
        return xpAmount;
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

    public void heal() {
        health.set((int)(health.get() * 1.05));
    }
}
