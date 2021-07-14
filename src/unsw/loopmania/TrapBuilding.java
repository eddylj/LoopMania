package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TrapBuilding extends StaticEntity implements Building, BuildingOnMove{

    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("trap");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnMove() {
        // TODO Auto-generated method stub
        
    }
    
}
