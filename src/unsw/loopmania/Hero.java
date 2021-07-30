package unsw.loopmania;

public interface Hero {
    public void takeDamage(double attackDamage, Enemy enemy);
    public boolean isDead();
    public void setHealth(int health);
}
