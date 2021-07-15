package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Stake extends Weapon{
    private double critAttack;
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(x, y, level, 350*(1+(level-1)*15/100), 20*(1+(level-1)/10));
        critAttack = 50*(1+(level-1)/10);
        super.setType("stake");
    }

    public Stake(int level) {
        super(level, 350*(1+(level-1)*15/100), 20*(1+(level-1)/10));
        critAttack = 50*(1+(level-1)/10);
        super.setType("stake");
    }


    public double getDamage(Enemy e) {
        if (e instanceof Vampire){
            return critAttack;
        }
        return super.getDamage();
    }
}
