package unsw.loopmania;

public interface Item {
    public int getPrice();
    public int getSellPrice();
    public int getReplaceCost();
    public boolean isWeapon();
    public boolean isShield();
    public boolean isRing();
}
