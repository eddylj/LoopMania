package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Heroes.Character;

public class StrengthPotion extends Potion{
    public static final int SELLPRICE = 60;
    public static final int REPLACECOST = 30;
    public static final int BASECOST = 50;
    public static final double BUFFDMG = 15.0;
    public int cost;
    /**
     * The class of health potion
     * @param x
     * @param y
     */
    public StrengthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setType("strengthpotion");
        cost = BASECOST;
    }

    public StrengthPotion() {
        super();
        super.setType("strengthpotion");
    }

    /**
     * Will heal the given character
     * @param character
     */
    public void use(Character character) {
        character.setBuff(BUFFDMG);
    }
    
    @Override
    public int getPrice() {
        return cost;
    }

    @Override
    public int getSellPrice() {
        return SELLPRICE;
    }

    @Override
    public int getReplaceCost() {
        return REPLACECOST;
    }

    public void increaseCost(int timesBought) {
        cost += (BASECOST * timesBought);
    }

    @Override
    public void reset_cost() {
        cost = BASECOST;        
    }


}
