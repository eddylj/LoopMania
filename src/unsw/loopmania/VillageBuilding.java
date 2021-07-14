package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class VillageBuilding extends StaticEntity implements Building, BuildingOnMove{

    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("village");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove() {
        // TODO Auto-generated method stub
        
    }
    
}
