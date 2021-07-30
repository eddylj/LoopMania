package unsw.loopmania.Heroes;

import unsw.loopmania.Enemies.Enemy;

public class ConvertedEnemy extends AlliedSoldier{
    private Enemy enemy;
    private int remainingTranceTime;
    /**
     * 
     * @param enemy
     */
    public ConvertedEnemy(Enemy enemy) {
        super();
        this.enemy = enemy;
    }
    /**
     * Attacks enemy
     */
    @Override
    public void attack(Enemy enemy) {
        super.attack(enemy);
        remainingTranceTime--;
    }
    /**
     * Checks if enemy is able to break free from trance
     * @return
     */
    public boolean canExitTrance() {
        if (remainingTranceTime == 0) {
            return true;
        } 
        return false;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
