package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Shield extends Protection {
    
    public Shield(int level, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(level, 400*(1+(level-1)*15/100), x, y);
    }

    public Shield(int level) {
        super(level, 400*(1+(level-1)*15/100));
    }

    @Override
    public int protect(int damage) {
        //Will implement the chance to fully block the damage
        return 0;
    }
    
}
