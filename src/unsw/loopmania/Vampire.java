package unsw.loopmania;

import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

public class Vampire extends Enemy {
    private String[] cardDrops;


    private VampireAttackStrategy Strategy;

    public Vampire (PathPosition position) {
        super(position, 2, 3, 18, 500, 150);
        super.setType("vampire");
        Strategy = new VampireNormal();
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
    
    }

    public Vampire() {
        super(2, 3, 18, 500, 150);
        super.setType("vampire");
        Strategy = new VampireNormal();
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
    }

    public void setStrategy(VampireAttackStrategy Strategy) {
        this.Strategy = Strategy;
    }
    @Override
    public void attack (Hero h) {
        Strategy.attack(h, this);
    }

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

    public StaticEntity getLoot(Character character) {
        int num = LoopManiaWorld.getRandNum();
        if (num < 30) {
            String itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            if (itemType.equals("healthpotion")) {
                return character.addUnequippedItem(itemType, 0);
            }
            else if (num < 20) {
                int level = character.getHighestLevel(itemType) + 1;
                return character.addUnequippedItem(itemType, level);
            }
            else {
                int level = character.getHighestLevel(itemType);
                return character.addUnequippedItem(itemType, level);
            }
        }
        else if (num < 45) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            return character.loadCard(cardType);
        }
    }
    
}
