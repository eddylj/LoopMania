package unsw.loopmania;

public interface Hero {
    public void attack(Enemy enemy, BattleRunner b);
    public void takeDamage(double attackDamage);
    public boolean isDead();
    public void setHealth(int i);
}
