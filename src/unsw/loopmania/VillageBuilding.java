package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class VillageBuilding extends StaticEntity implements Building, BuildingOnMove{

    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("village");
    }

    @Override
    public void updateOnMove(MovingEntity character) {
        if (!(character instanceof Character)) {
            return;
        }
        else if (character.getX() == super.getX() && character.getY() == super.getY()) {
            heal((Character) character);
        }
        
    }

    public void heal(Character c) {
        c.restoreHealth((100- c.getHealth())/2);
    }
    
}
