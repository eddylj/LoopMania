package unsw.loopmania;

public class convertedEnemy extends AlliedSoldier{
    private Enemy enemy;
    private int remainingTranceTime;

    public convertedEnemy(Enemy enemy, int cycle) {
        super();
        this.enemy = enemy;
    }

    @Override
    public void attack(Enemy e) {
        super.attack(e);
        remainingTranceTime--;
    }
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
