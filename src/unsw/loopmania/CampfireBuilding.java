package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class CampfireBuilding extends StaticEntity implements Building, BuildingOnMove{

    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("campfire");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove() {
        // TODO Auto-generated method stub
        
    }
    
}
