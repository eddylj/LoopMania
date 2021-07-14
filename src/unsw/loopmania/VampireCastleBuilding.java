package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends StaticEntity implements Building, BuildingOnCycle{

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnCycle() {
        // TODO Auto-generated method stub
        
    }
    
}
