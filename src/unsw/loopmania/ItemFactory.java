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
        // if (type.equals("anduril")) {
        //     return createAnduril(x, y, level);
        // }
        else if (type.equals("helmet")) {
            return createHelmet(x, y, level);
        }
        else if (type.equals("armour")) {
            return createArmour(x, y, level);
        }
        else if (type.equals("shield")) {
            return createShield(x, y, level);
        }
        // if (type.equals("treestump")) {
        //     return createTreeStump(x, y, level);
        // }
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
        // else if (type.equals("theonering")) {
        //     return createTheOneRing(x, y);
        // }
        else if (type.equals("doggiecoin")) {
            return createDoggieCoin(x, y);
        }
        else {
            return null;
        }
    }

    // public Item create(String type) {
    //     if (type.equals("theonering")) {
    //         return createTheOneRing();
    //     }
    //     else if (type.equals("anduril")) {
    //         return createAnduril();
    //     }
    //     else if (type.equals("treestump")) {
    //         return createTreeStump();
    //     }
    //     else {
    //         return null;
    //     }
    // }
    
    private Item createDoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new DoggieCoin(x, y);
    }

    // private Item createTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
    //     return new TreeStump(x,y,level);
    // }

    // private Item createAnduril(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
    //     return new Anduril(x, y, level);
    // }

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
    // public TheOneRing createTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
    //     return new TheOneRing(x, y);
    // }
    // public TheOneRing createTheOneRing() {
    //     return new TheOneRing();
    // }
    // public Anduril createAnduril() {
    //     return new Anduril(1);
    // }
    // public TreeStump createTreeStump() {
    //     return new TreeStump(1);
    // }

}
