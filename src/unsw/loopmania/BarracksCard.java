package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BarracksCard extends StaticEntity implements Card {

    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}