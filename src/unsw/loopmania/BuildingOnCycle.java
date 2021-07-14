package unsw.loopmania;

public interface BuildingOnCycle {
    public void updateOnCycle();

    public Enemy spawnEnemy(PathPosition pathPosition);
    public int getChanceOfSpawning1();
    public int getChanceOfSpawning2();
}
