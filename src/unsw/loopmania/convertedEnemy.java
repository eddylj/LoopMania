package unsw.loopmania;

public class convertedEnemy extends AlliedSoldier{
    private Enemy enemy;
    private int remainingTranceTime;
    /**
     * 
     * @param enemy
     */
    public convertedEnemy(Enemy enemy) {
        super();
        this.enemy = enemy;
    }
    /**
     * Attacks enemy
     */
    @Override
    public void attack(Enemy e) {
        super.attack(e);
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
