package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Shield extends Protection {
    private int blockChance = 10;
    
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        blockChance += 3*level;
    }

    public Shield(int level) {
        super(level, 400*(1+(level-1)*15/100));
    }

    @Override
    public double protect(int damage) {
        //Will implement the chance to fully block the damage
        
        return 0;
    }
    
}
