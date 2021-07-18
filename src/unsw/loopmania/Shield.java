package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Shield extends Protection {
    private int blockChance = 7;
    
    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        blockChance += 3*level;
        super.setType("shield");
    }

    public Shield(int level) {
        super(level, 400*(1+(level-1)*15/100));
        blockChance += 3*level;
        super.setType("shield");
    }

    /**
     * Given the damage being inflicted on the character and will return the amount
     * of damage that will be inflicted after the reduction from the protection
     * @param damage the total damage being given
     * @return the damage after the reduction from protection has been applied
     */
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
