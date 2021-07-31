package unsw.loopmania.Enemies;

import java.util.List;
import java.util.ArrayList;

import org.javatuples.Pair;

import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Heroes.Hero;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Buildings.CampfireBuilding;
import unsw.loopmania.Entities.StaticEntity;

public class Vampire extends Enemy {
    public static final int BATTLERADIUS = 2;
    public static final int SUPPORTRADIUS = 3;
    public static final int DAMAGE = 18;
    public static final int GOLDAMOUNT = 500;
    public static final int HEALTH = 150;
    public static final int XP = 800;
    private String[] cardDrops;
    private VampireAttackStrategy Strategy;

    /**
     * 
     * @param position
     */
    public Vampire (PathPosition position) {
        super(position, BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("vampire");
        Strategy = new VampireNormal();
        cardDrops = new String[]{"campfire", "barracks", "tower", "bank", "trap", "village", "vampirecastle", "zombiepit"};
    
    }

    public Vampire() {
        super(BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("vampire");
        Strategy = new VampireNormal();
        cardDrops = new String[]{"campfire", "barracks", "tower", "bank", "trap", "village", "vampirecastle", "zombiepit"};
    }

    /**
     * Deals damage to Hero
     */
    @Override
    public void attack (Hero hero) {
        Strategy.attack(hero, this);
    }

    /**
     * Moves vampire based on where campfires are
     * @param campfire
     */
    public void move(CampfireBuilding campfire) {
        Pair<Integer, Integer> forward = position.getClockwisePosition();
        Pair<Integer, Integer> backward = position.getAntiClockwisePosition();
        double forwardDistance = Math.sqrt(Math.pow(forward.getValue0() - getX(), 2) + Math.pow(forward.getValue1() - getY(), 2));
        double backwardDistance = Math.sqrt(Math.pow(backward.getValue0() - getX(), 2) + Math.pow(backward.getValue1() - getY(), 2));
        if (forwardDistance > backwardDistance) {
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
        if (num < 30) {
            String itemType;
            if (num < 5 && !rareItems.isEmpty()) {
                itemType = rareItems.get(LoopManiaWorld.getRandNum() % rareItems.size());
            }
            else {
                itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            }
            if (character.getNonLevelItems().contains(itemType)) {
                loot.add(character.addUnequippedItem(itemType, 0));
            }
            else if (num < 20) {
                int level = character.getHighestLevel(itemType) + 1;
                if (level > 10) {
                    level = 10;
                }
                loot.add(character.addUnequippedItem(itemType, level));
                System.out.println("Up one level");
            }
            else {
                int level = character.getHighestLevel(itemType);
                loot.add(character.addUnequippedItem(itemType, level));
                System.out.println("same level");
            }
        }
        else if (num < 50) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            loot.add(character.loadCard(cardType, width));
        }
        return loot;
    }
    
    public void setStrategy(VampireAttackStrategy Strategy) {
        this.Strategy = Strategy;
    }
}
