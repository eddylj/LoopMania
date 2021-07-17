package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

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
    public boolean canBePlaced(int x, int y, List<Pair<Integer, Integer>> orderedPath) {
        for (Pair<Integer, Integer> tile : orderedPath) {
            if (x == tile.getValue0() && y == tile.getValue1()) {
                return true;
            }
        }
        return false;
    }
}
