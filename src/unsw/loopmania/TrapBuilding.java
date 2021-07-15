package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TrapBuilding extends StaticEntity implements Building, BuildingOnMove{

    private boolean broken;
    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("trap");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove(MovingEntity enemy) {
        // TODO Auto-generated method stub
        if (!broken && enemy.getX() == super.getX() && enemy.getY() == super.getY()) {
            activate(enemy);
            broken = true;
        }
    }

    public void activate(MovingEntity enemy) {
        enemy.takeDamage(30);
    }

    public boolean checkBroken() {
        return broken;
    }
    
}
