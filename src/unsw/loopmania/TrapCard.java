package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TrapCard extends StaticEntity implements Card {

    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("trap");
    }
    
    public TrapCard() {
        super();
        super.setType("trap");
    }

    @Override
    public boolean canBePlaced(PathTile PathTile) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
