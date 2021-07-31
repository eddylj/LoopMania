package unsw.loopmania.Enemies;

import java.util.List;
import java.util.ArrayList;

import org.javatuples.Pair;

import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Heroes.Hero;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Entities.StaticEntity;

public class Thief extends Enemy {
    public static final int BATTLERADIUS = 3;
    public static final int SUPPORTRADIUS = 3;
    public static final int DAMAGE = 0;
    public static final int GOLDAMOUNT = 250;
    public static final int HEALTH = 100;
    public static final int XP = 300;
    private String[] cardDrops;

    /**
     * 
     * @param position
     */
    public Thief (PathPosition position) {
        super(position, BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("thief");
        cardDrops = new String[]{"vampirecastle", "zombiepit"};
    
    }

    public Thief() {
        super(BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("thief");
        cardDrops = new String[]{"vampirecastle", "zombiepit"};
    }

    /**
     * Deals damage to Hero
     */
    @Override
    public void attack (Hero hero) {
        if (hero instanceof Character && LoopManiaWorld.getRandNum() < 50) {
            ((Character) hero).loseRandomItem();
        }
    }

    /**
     * Moves thief based on where character is
     * @param character
     */
    public void move(Character character) {
        Pair<Integer, Integer> forward = position.getClockwisePosition();
        Pair<Integer, Integer> backward = position.getAntiClockwisePosition();
        double forwardDistance = Math.sqrt(Math.pow(forward.getValue0() - getX(), 2) + Math.pow(forward.getValue1() - getY(), 2));
        double backwardDistance = Math.sqrt(Math.pow(backward.getValue0() - getX(), 2) + Math.pow(backward.getValue1() - getY(), 2));
        if (forwardDistance < backwardDistance) {
            moveUpPath();
        }
        else {
            moveDownPath();
        }
    }

    /**
     * Generates random loot for player for vampire
     * @param character
     * @param width
     * @param rareItems
     * @return StaticEntity loot
     */
    public List<StaticEntity> getLoot(Character character, int width, List<String> rareItems) {
        int num = LoopManiaWorld.getRandNum();
        List <StaticEntity> loot = new ArrayList<StaticEntity>();
        if (num < 10) {
            String itemType;
            if (num < 1 && !rareItems.isEmpty()) {
                itemType = rareItems.get(LoopManiaWorld.getRandNum() % rareItems.size());
            }
            else {
                itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            }
            if (character.getNonLevelItems().contains(itemType)) {
                loot.add(character.addUnequippedItem(itemType, 0));
            }
            else if (num < 5) {
                int level = character.getHighestLevel(itemType) + 1;
                if (level > 10) {
                    level = 10;
                }
                loot.add(character.addUnequippedItem(itemType, level));
            }
            else {
                int level = character.getHighestLevel(itemType);
                loot.add(character.addUnequippedItem(itemType, level));
            }
        }
        else if (num < 20) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            loot.add(character.loadCard(cardType, width));
        }
        return loot;
    }
}
