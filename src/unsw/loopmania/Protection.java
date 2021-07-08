package unsw.loopmania;

public class Protection extends StaticEntity{
    private int level;
    private int goldAmount;

    public Protection (int level, int goldAmount, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.level = level;
        this.goldAmount = goldAmount;
    }

    /**
     * Given the damage being inflicted on the character and will return the amount
     * of damage that will be inflicted after the reduction from the protection
     * @param damage the total damage being given
     * @return the damage after the reduction from protection has been applied
     */
    public int protect(int damage) {
        return 0;
    }
}
