package unsw.loopmania.Cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Entities.StaticEntity;

public class TrapCard extends StaticEntity implements Card {
    /**
     * 
     * @param x
     * @param y
     */
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("trap");
    }
    
    public TrapCard() {
        super();
        super.setType("trap");
    }

    /**
     * checks whether card can be placed on path
     * @param x
     * @param y
     * @param orderedPath
     * @return boolean on whether card can be placed
     */
    @Override
    public boolean canBePlaced(int x, int y, List<Pair<Integer, Integer>> orderedPath) {
        for (Pair<Integer, Integer> tile : orderedPath) {
            if (x == tile.getValue0() && y == tile.getValue1()) {
                return true;
            }
        }
        return false;
    }
}
