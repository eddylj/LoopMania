package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ItemFactory {
    //"Sword", "Helmet", "Armour", "Shield", "Stake", "Staff"
    /**
     * Generates respective item
     * @param x
     * @param y
     * @param type
     * @param level
     * @return Item
     */
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
        else if (type.equals("axe")) {
            return createAxe(x, y, level);
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
        else if (type.equals("thornmail")) {
            return createThornmail(x, y, level);
        }
        else {
            return null;
        }
    }



    public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        if (type.equals("healthpotion")) {
            return createHealthPotion(x, y);
        }
        else if (type.equals("doggiecoin")) {
            return createDoggieCoin(x, y);
        }
        else if (type.equals("strengthpotion")) {
            return createStrengthPotion(x,y);
        }
        else {
            return null;
        }
    }
    
    private Item createStrengthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new StrengthPotion(x,y);
    }

    private Item createDoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new DoggieCoin(x, y);
    }

    // private Item createTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
    //     return new TreeStump(x,y,level);
    // }

    // private Item createAnduril(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
    //     return new Anduril(x, y, level);
    // }
    private Item createAxe(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Axe(x, y, level);
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
    public Thornmail createThornmail(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        return new Thornmail(x, y, level);
    }
    public HealthPotion createHealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new HealthPotion(x, y);
    }

}
