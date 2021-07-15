package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class CampfireBuilding extends StaticEntity implements Building, BuildingOnMove{

    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("campfire");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove(MovingEntity character) {
        // TODO Auto-generated method stub
        if (super.getX() == character.getX() && super.getY() == character.getY()) {
            applybuff((Character) character);
        } else {
            removeBuff((Character) character);
        }
        
        
    }

    @Override
    public String getType() {
        return super.getType();
    }
    
    public void applybuff(Character character) {
        character.setBonusDamageStrategy(new CampfireState());
    }

    public void removeBuff(Character character) {
        character.setBonusDamageStrategy(new NormalState());
    }
}
