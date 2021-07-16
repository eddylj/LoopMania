package unsw.loopmania;

public class AlliedSoldier implements Hero{
    private int health;
    private int attackDamage;

    public AlliedSoldier(int cycle) {
        health = 30;
        attackDamage = 20 + cycle;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Attack with this weapon to the given enemy
     * @param enemy that is being attacked
     * @return boolean that is true if the enemy was killed
     */
    public void attack(Enemy enemy) {
        enemy.takeDamage(attackDamage);
    }

    /**
     * Will subtract the given damage from the ally
     * @param attackDamage attack damage given to the ally
     * @return returns true if the ally died
     */
    public void takeDamage(double attackDamage) {
        health -= attackDamage;
    }

    public boolean isDead() {
        if (health <= 0) {
            return true;
        } 
        return false;
    }
}
