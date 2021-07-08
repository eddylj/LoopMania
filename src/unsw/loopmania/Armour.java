package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Armour extends Protection {
    
    public Armour(int level, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(level, 400*(1+(level-1)*15/100), x, y);
    }

    @Override
    public int protect(int damage) {
        //Will implement the percentage reduction
        return 0;
    }
}
