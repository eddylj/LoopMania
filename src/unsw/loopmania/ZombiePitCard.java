package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePitCard extends StaticEntity implements Card {

    public ZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public ZombiePitCard() {
        super();
    }
    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}