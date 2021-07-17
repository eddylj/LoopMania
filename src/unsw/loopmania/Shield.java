package unsw.loopmania;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;

public class Shield extends Protection {
    private int blockChance = 7;
    
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        blockChance += 3*level;
        super.setType("shield");
    }

    public Shield(int level) {
        super(level, 400*(1+(level-1)*15/100));
        super.setType("shield");
    }

    @Override
    public double protect(double damage) {
        //Will implement the chance to fully block the damag
        int randomInt = LoopManiaWorld.getRandNum();
        if (randomInt < blockChance) {
            return 0;
        }
        return damage;
    }
    
}
