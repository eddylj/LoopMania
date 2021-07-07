package unsw.loopmania;

public class Stake extends Weapon{
    private int critAttack;
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, 350*(1+(level-1)*15/100), 20*(1+(level-1)/10));
        critAttack = 50*(1+(level-1)/10);
    }

    @Override
    public boolean dealDamage(Enemy enemy) {
        // Also need to implement checking if the enemy is a vampire
        return true;
    }
}
