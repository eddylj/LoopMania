package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class VillageBuilding extends StaticEntity implements Building, BuildingOnMove{

    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("village");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove(MovingEntity character) {
        // TODO Auto-generated method stub
        if (character.getX() == super.getX() && character.getY() == super.getY()) {
            heal((Character) character);
        }
        
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public void heal(Character c) {
        c.restoreHealth((100- c.getHealth())/2);
    }
    
}
