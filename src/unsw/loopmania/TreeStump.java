package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TreeStump extends Shield implements RareItem{
    

    /**
     * Main constructor for Armour class
     * @param x int Column coordinate in inventory
     * @param y int Row coordinate in inventory
     * @param level int Level of armour
     */
    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level);
        super.setType("treestump");
    }
    @Override
    public double protect(double damage, Enemy e) {
        //Will implement the chance to fully block the damag
        int blockChance = 25;

        int randomInt = LoopManiaWorld.getRandNum();
        if (e instanceof Boss) {
            blockChance += 50;            
        }
        if (randomInt < blockChance) {
            return 0;
        } else {
            return damage;
        }
    }
    
}
