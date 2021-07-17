package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePitCard extends StaticEntity implements Card {

    public ZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("zombiepit");
    }

    public ZombiePitCard() {
        super();
        super.setType("zombiepit");
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
        for (Pair<Integer, Integer> tile : orderedPath) {
            if (nextTo(x, y, tile.getValue0(), tile.getValue1())) {
                return true;
            }
        }
        return false;
    }
    
}