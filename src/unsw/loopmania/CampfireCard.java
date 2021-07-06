package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class CampfireCard extends StaticEntity implements Card {

    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}