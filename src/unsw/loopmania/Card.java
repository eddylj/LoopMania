package unsw.loopmania;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Card in the world
 * which doesn't move
 */
// public abstract class Card extends StaticEntity {
//     // TODO = implement other varieties of card than VampireCastleCard
//     public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
//         super(x, y);
//     }
// }
public interface Card {
    boolean canBePlaced(PathTile PathTile);
    public int getX();
    public int getY();
    public void destroy();
    public IntegerProperty x();
    public IntegerProperty y();
}
