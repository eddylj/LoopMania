package unsw.loopmania;

/**
 * An interface for all buildings that activate at the start of each cycle.
 * @author Group FRIDGE
 */
public interface BuildingOnCycle {
    public Enemy spawnEnemy(PathPosition pathPosition);
    public int generateNumberOfEnemies();
}
