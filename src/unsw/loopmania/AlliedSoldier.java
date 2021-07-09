package unsw.loopmania;

public class AlliedSoldier {
    private int health;

    public AlliedSoldier() {
        health = 30;
    }

    /**
     * Attack with this weapon to the given enemy
     * @param enemy that is being attacked
     * @return boolean that is true if the enemy was killed
     */
    public boolean attack(Enemy enemy) {
        return true;
    }

    public int getHealth() {
        return health;
    }

    /**
     * Will subtract the given damage from the ally
     * @param attackDamage attack damage given to the ally
     * @return returns true if the ally died
     */
    public boolean damage(int attackDamage) {
        return true;
    }
}
