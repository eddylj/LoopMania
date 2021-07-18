package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class itemFactory {
    //"Sword", "Helmet", "Armour", "Shield", "Stake", "Staff"
    public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type, int level) {
        if (type.equals("sword")) {
            return createSword(x, y, level);
        }
        else if (type.equals("helmet")) {
            return createHelmet(x, y, level);
        }
        else if (type.equals("armour")) {
            return createArmour(x, y, level);
        }
        else if (type.equals("shield")) {
            return createShield(x, y, level);
        }
        else if (type.equals("stake")) {
            return createStake(x, y, level);
        }
        else if (type.equals("staff")) {
            return createStaff(x, y, level);
        }
        else {
            return null;
        }
    }

    public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        if (type.equals("healthpotion")) {
            return createHealthPotion(x, y);
        }
        else if (type.equals("theonering")) {
            return createTheOneRing(x, y);
        }
        else {
            return null;
        }
    }
    
    public Sword createSword(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Sword(x, y, level);
    }
    
    public Stake createStake(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Stake(x, y, level);
    }
    public Staff createStaff(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Staff(x, y, level);
    }
    public Helmet createHelmet(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Helmet(x, y, level);
    }
    public Armour createArmour(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Armour(x, y, level);
    }
    public Shield createShield(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Shield(x, y, level);
    }
    public HealthPotion createHealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new HealthPotion(x, y);
    }
    public TheOneRing createTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new TheOneRing(x, y);
    }

}
