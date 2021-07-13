package unsw.loopmania;

public class AlliedSoldier {
    private int health;
    private int attackDamage;

    public AlliedSoldier(int cycle) {
        health = 30;
        attackDamage = 20 + cycle;
    }

    /**
     * Attack with this weapon to the given enemy
     * @param enemy that is being attacked
     * @return boolean that is true if the enemy was killed
     */
    public void attack(Enemy enemy) {
        enemy.takeDamage(attackDamage);
    }

    public int getHealth() {
        return health;
    }

    /**
     * Will subtract the given damage from the ally
     * @param attackDamage attack damage given to the ally
     * @return returns true if the ally died
     */
    public void takeDamage(int attackDamage) {
        health -= attackDamage;
    }

    public boolean isDead() {
        if (health <= 0) {
            return true;
        } 
        return false;
    }
}
