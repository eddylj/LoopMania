package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a vampire castle card in the backend game world
 */

public class VampireCastleCard extends StaticEntity implements Card {

    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("vampirecastle");
    }
    
    public VampireCastleCard() {
        super();
        super.setType("vampirecastle");
    }

    private boolean nextTo(int oldX, int oldY, int newX, int newY) {
        if (oldX == newX + 1 && oldY == newY) {
            return true;
        }
        if (oldX == newX - 1 && oldY == newY) {
            return true;
        }
        if (oldX == newX && oldY == newY + 1) {
            return true;
        }
        if (oldX == newX && oldY == newY - 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canBePlaced(int x, int y, List<Pair<Integer, Integer>> orderedPath) {
        if (orderedPath.contains(new Pair<Integer, Integer>(x,y))) {
            return false;
        }
        for (Pair<Integer, Integer> tile : orderedPath) {
            if (nextTo(x, y, tile.getValue0(), tile.getValue1())) {
                return true;
            }
        }
        return false;
    }
    
}
