package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class CampfireBuilding extends StaticEntity implements Building, BuildingOnMove{

    private int radius;
    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("campfire");
        radius = 2;
    }

    @Override
    public void updateOnMove(MovingEntity character) {
        // TODO Auto-generated method stub
        double distance = Math.sqrt(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2));

        if (distance < 2) {
            applybuff((Character) character);
        } else {
            removeBuff((Character) character);
        }
        
        
    }
    
    private void applybuff(Character character) {
        if (character.getBonusDamageStrategy() instanceof CampfireState) {
            return;
        } else {
            character.setBonusDamageStrategy(new CampfireState());
        }
    }

    private void removeBuff(Character character) {
        if (character.getBonusDamageStrategy() instanceof NormalState) {
            return;
        } else {
            character.setBonusDamageStrategy(new NormalState());
        }
    }
}
