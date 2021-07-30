package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TreeStump extends Shield implements RareItem{
    private static final int SELLPRICE = 1500;
    private static final int REPLACECOST = 375;

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

    public TreeStump(int level) {
        super(level);
        super.setType("treestump");
        super.setBlockChance(50);
    }

    @Override
    public int getSellPrice() {
        return SELLPRICE;
    }

    @Override
    public int getReplaceCost() {
        return REPLACECOST;
    }

    @Override
    public double protect(double damage, Enemy enemy) {
        //Will implement the chance to fully block the damag
        int blockChance = 25;

        int randomInt = LoopManiaWorld.getRandNum();
        if (enemy instanceof Boss) {
            blockChance += 50;            
        }
        if (randomInt < blockChance) {
            return 0;
        } else {
            return damage;
        }
    }

    @Override
    public boolean isTreeStump() {
        return true;
    }
    
}
