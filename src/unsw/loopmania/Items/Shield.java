package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;

public class Shield extends Protection {
    public static final int PRICE = 400;
    private int blockChance = 7;
    
    /**
     * 
     * @param x
     * @param y
     * @param level
     */
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, PRICE, x, y);
        blockChance += 3 * level;
        super.setType("shield");
    }

    public Shield(int level) {
        super(level, 400);
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
        int randomInt = LoopManiaWorld.getRandNum();
        if (randomInt < blockChance) {
            return 0;
        }
        return damage;
    }
    public int getBlockChance() {
        return blockChance;
    }
    public void setBlockChance(int blockChance){
        this.blockChance = blockChance;
    }

    @Override
    public boolean isShield() {
        return true;
    }
}