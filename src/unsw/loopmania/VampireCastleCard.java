package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a vampire castle card in the backend game world
 */
import javafx.beans.property.SimpleIntegerProperty;

public class VampireCastleCard extends StaticEntity implements Card {

    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("vampirecastle");
    }
    
    public VampireCastleCard() {
        super();
        super.setType("vampirecastle");
    }

    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
