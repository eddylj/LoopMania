package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

public class CampfireCard extends StaticEntity implements Card {

    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("campfire");
    }
    
    public CampfireCard() {
        super();
        super.setType("campfire");
    }

    @Override
    public boolean canBePlaced(int x, int y, List<Pair<Integer, Integer>> orderedPath) {
        for (Pair<Integer, Integer> tile : orderedPath) {
            if (tile.getValue0() == x && tile.getValue1() == y) {
                return false;
            }
        }
        return true;
    }
    
    
}