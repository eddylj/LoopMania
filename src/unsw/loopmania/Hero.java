package unsw.loopmania;

public interface Hero {
    public void attack(Enemy enemy);
    public void takeDamage(int attackDamage);
    public boolean isDead();
    public void setHealth(int i);
}
