package unsw.loopmania;

/**
 * An interface for all buildings that activate when something
 * walks ontop/within range of them
 * @author Group FRIDGE
 */
public interface BuildingOnMove {
    public void updateOnMove(MovingEntity movingEntity);
    public String getType();
}
