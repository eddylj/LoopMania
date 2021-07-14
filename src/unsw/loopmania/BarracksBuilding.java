package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BarracksBuilding extends StaticEntity implements Building, BuildingOnMove{

    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("barracks");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove() {
        // TODO Auto-generated method stub
        
    }
    
}
