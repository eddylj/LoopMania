package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class HerosCastleBuilding extends StaticEntity implements Building, BuildingOnMove{
    /**
     * 
     * @param x
     * @param y
     */
    public HerosCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("heros_castle");
    }

    @Override
    public void updateOnMove(MovingEntity movingEntity) {
    }    
}
