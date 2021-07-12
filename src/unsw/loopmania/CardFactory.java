package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;

public class CardFactory {
    //"Campfire", "Barracks", "Tower", "Trap", "VampireCastle", "ZombiePit", "Village"}
    public Card create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        if (type.equals("Campfire")) {
            return createCampfire(x, y);
        }
        else if (type.equals("Barracks")) {
            return createBarracks(x, y);
        }
        else if (type.equals("Tower")) {
            return createTower(x, y);
        }
        else if (type.equals("Trap")) {
            return createTrap(x, y);
        }
        else if (type.equals("VampireCastle")) {
            return createVampireCastle(x, y);
        }
        else if (type.equals("ZombiePit")) {
            return createZombiePit(x, y);
        }
        else if (type.equals("Village")) {
            return createVillage(x, y);
        }
        else {
            return null;
        }
    }

    public CampfireCard createCampfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new CampfireCard(x, y);
    }
    
    public BarracksCard createBarracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new BarracksCard(x, y);
    }
    
    public TowerCard createTower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new TowerCard(x, y);
    }
    
    public TrapCard createTrap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new TrapCard(x, y);
    }
    
    public VampireCastleCard createVampireCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new VampireCastleCard(x, y);
    }
    
    public VillageCard createVillage(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new VillageCard(x, y);
    }
    
    public ZombiePitCard createZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new ZombiePitCard(x, y);
    }
    
}
