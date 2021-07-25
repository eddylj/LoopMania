package unsw.loopmania;

public interface Hero {
    public void takeDamage(double attackDamage, Enemy e);
    public boolean isDead();
    public void setHealth(int i);
}
