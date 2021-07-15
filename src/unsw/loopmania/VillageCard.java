package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class VillageCard extends StaticEntity implements Card {

    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("village");
    }
    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
