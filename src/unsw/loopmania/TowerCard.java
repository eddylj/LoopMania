package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TowerCard extends StaticEntity implements Card {

    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    public TowerCard() {
        super();
    }

    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}