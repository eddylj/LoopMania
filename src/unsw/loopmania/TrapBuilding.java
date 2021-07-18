package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TrapBuilding extends StaticEntity implements Building, BuildingOnMove{

    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("trap");
    }

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

    public void activate(MovingEntity enemy) {
        enemy.takeDamage(30);
        if (enemy.isDead()) {
            enemy.destroy();
        }
    }
    
}
