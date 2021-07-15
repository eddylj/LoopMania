package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TowerBuilding extends StaticEntity implements Building, BuildingOnMove{

    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("tower");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove(MovingEntity enemy) {
        enemy.takeDamage(4);
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public boolean range(Character character) {
        double distance = Math.pow((character.getX()-building.getX()), 2) +  Math.pow((character.getY()-building.getY()), 2);
        if (distance < 3) {
            return true;
        }
        return false;
    }
    public void attack(Enemy e) {
    }
    
}
