package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Shield extends Protection {
    
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        super.setType("shield");
    }

    public Shield(int level) {
        super(level, 400*(1+(level-1)*15/100));
        super.setType("shield");
    }

    @Override
    public int protect(int damage) {
        //Will implement the chance to fully block the damage
        return 0;
    }
    
}
