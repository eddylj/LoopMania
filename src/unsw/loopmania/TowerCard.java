package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TowerCard extends StaticEntity implements Card {

    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("tower");
    }
    
    public TowerCard() {
        super();
        super.setType("tower");
    }

    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}