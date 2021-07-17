package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

public class VillageCard extends StaticEntity implements Card {

    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("village");
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
