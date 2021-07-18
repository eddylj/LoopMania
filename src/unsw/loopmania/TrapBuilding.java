package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TrapBuilding extends StaticEntity implements Building, BuildingOnMove{
    /**
     * 
     * @param x
     * @param y
     */
    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("trap");
    }

    /**
     * Checks if enemy has stepped on trap. If so activate trap
     * @param enemy
     */
    @Override
    public void updateOnMove(MovingEntity enemy) {
        if (!(enemy instanceof Enemy)) {
            return;
        }
        else if (enemy.getX() == super.getX() && enemy.getY() == super.getY()) {
            activate(enemy);
            this.destroy();
        }
    }

    /**
     * Activates trap
     * @param enemy
     */
    public void activate(MovingEntity enemy) {
        enemy.takeDamage(30);
        if (enemy.isDead()) {
            enemy.destroy();
        }
    }
    
}
