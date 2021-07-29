package unsw.loopmania;

import java.util.List;
import java.util.ArrayList;

public class ElanMuske extends Enemy implements Boss{
    public static final int BATTLERADIUS = 1;
    public static final int SUPPORTRADIUS = 1;
    public static final int DAMAGE = 35;
    public static final int GOLDAMOUNT = 800;
    public static final int HEALTH = 700;
    public static final int XP = 1700;
    private String[] cardDrops;
    private boolean canMove = true;
    /**
     * 
     * @param position
     */
    public ElanMuske(PathPosition position) {
        super(position, BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("elanmuske");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
        
    }
    public ElanMuske() {
        super(BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("elanmuske");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
        
    }

    @Override
    public List<StaticEntity> getLoot(Character character, int width, List<String> rareItems) {
        character.increaseBossKills();
        int num = LoopManiaWorld.getRandNum();
        List <StaticEntity> loot = new ArrayList<StaticEntity>();
        if (num >= 50) {
            String rareType = rareItems.get(LoopManiaWorld.getRandNum() % rareItems.size());
            loot.add(character.addUnequippedItem(rareType, 0));
        }
        String itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
        if (character.getNonLevelItems().contains(itemType)) {
            loot.add(character.addUnequippedItem(itemType, 0));
        } else {
            int level;
            if (num < 80 && num > 10) {
                level = character.getHighestLevel(itemType) + 2;
                if (level > 10) {
                    level = 10;
                }
            } else {
                level = character.getHighestLevel(itemType) + 1;
                if (level > 10) {
                    level = 10;
                }
            }
            loot.add(character.addUnequippedItem(itemType, level));
        }
        if (num < 60) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            loot.add(character.loadCard(cardType, width));
        }
        return loot;
    }

    @Override
    public void attack (Hero h, BattleRunner b) {
        h.takeDamage(this.getAttackDamage(), this);
        b.healenemies();
    }

    @Override
    public void move() {
        if (canMove) {
            super.moveDownPath();
            canMove = false;
        }
        else {
            canMove = true;
        }
    }
}
