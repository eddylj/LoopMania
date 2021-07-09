package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Staff extends Weapon{

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, 700*(1+(level-1)*15/100), 18*(1+(level-1)/10));
    }    

    public Staff(int level) {
        super(level, 700*(1+(level-1)*15/100), 18*(1+(level-1)/10));
    }
    
    @Override
    public boolean dealDamage(Enemy enemy) {
        //Need to also implement the chance to convert enemies
        return true;
    }
} 
