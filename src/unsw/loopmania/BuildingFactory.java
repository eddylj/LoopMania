package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BuildingFactory {
    //"Campfire", "Barracks", "Tower", "Trap", "VampireCastle", "ZombiePit", "Village"}
    public Building create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        if (type.equals("campfire")) {
            return createCampfire(x, y);
        }
        else if (type.equals("barracks")) {
            return createBarracks(x, y);
        }
        else if (type.equals("tower")) {
            return createTower(x, y);
        }
        else if (type.equals("trap")) {
            return createTrap(x, y);
        }
        else if (type.equals("vampirecastle")) {
            return createVampireCastle(x, y);
        }
        else if (type.equals("zombiepit")) {
            return createZombiePit(x, y);
        }
        else if (type.equals("village")) {
            return createVillage(x, y);
        }
        else if (type.equals("heros_castle")) {
            return createHeroCastle(x, y);
        }
        else {
            return null;
        }
    }

    private CampfireBuilding createCampfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new CampfireBuilding(x, y);
    }
    
    private BarracksBuilding createBarracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new BarracksBuilding(x, y);
    }
    
    private TowerBuilding createTower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new TowerBuilding(x, y);
    }
    
    private TrapBuilding createTrap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new TrapBuilding(x, y);
    }
    
    private VampireCastleBuilding createVampireCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new VampireCastleBuilding(x, y);
    }
    
    private VillageBuilding createVillage(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new VillageBuilding(x, y);
    }
    
    private ZombiePitBuilding createZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new ZombiePitBuilding(x, y);
    }

    private HerosCastleBuilding createHeroCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // return new CampfireCard(x, y);
        return new HerosCastleBuilding(x, y);
    }
}
