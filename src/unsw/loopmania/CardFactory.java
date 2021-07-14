package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;

public class CardFactory {
    //"Campfire", "Barracks", "Tower", "Trap", "VampireCastle", "ZombiePit", "Village"}
    public Card create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
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
        // public VampireCastleCard loadVampireCard(){
        //     // if adding more cards than have, remove the first card...
        //     if (cardEntities.size() >= getWidth()){
        //         // TODO = give some cash/experience/item rewards for the discarding of the oldest card
        //         removeCard(0);
        //     }
        //     VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        //     cardEntities.add(vampireCastleCard);
        //     return vampireCastleCard;
        // }
        // return null;
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
