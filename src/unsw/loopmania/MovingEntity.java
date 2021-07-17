package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity {

    /**
     * object holding position in the path
     */
    public PathPosition position;
    public int health;

    /**
     * Create a moving entity which moves up and down the path in position
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position, int health) {
        super();
        this.position = position;
        this.health = health;
    }

    public MovingEntity(int health) {
        super();
        this.health = health;
    }

    public PathPosition getPosition() {
        return position;
    }

    /**
     * move clockwise through the path
     */
    public void moveDownPath() {
        position.moveDownPath();
    }

    /**
     * move anticlockwise through the path
     */
    public void moveUpPath() {
        position.moveUpPath();
    }

    public SimpleIntegerProperty x() {
        return position.getX();
    }

    public SimpleIntegerProperty y() {
        return position.getY();
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }
    /**
     * Takes health off entity for damage taken
     * @param attackdamage amount of damage to be taken
     */
    public void takeDamage(double attackDamage) {
        health -= ((int)attackDamage);
    }


    /**
     * Checks if movnign entity is dead
     * @return boolean for if entity health is <= 0
     */
    public boolean isDead() {
        if (health <= 0) {
            return true;
        }
        return false;
    }
}
