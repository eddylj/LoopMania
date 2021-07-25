package unsw.loopmania;

import java.util.List;
import java.util.ArrayList;

public class Doggie extends Enemy implements Boss{
    public static final int BATTLERADIUS = 1;
    public static final int SUPPORTRADIUS = 1;
    public static final int DAMAGE = 20;
    public static final int GOLDAMOUNT = 0;
    public static final int HEALTH = 500;
    public static final int XP = 700;
    public static final int STUNCHANCE = 20;
    private String[] cardDrops;

    /**
     * 
     * @param position
     */
    public Doggie(PathPosition position) {
        super(position, BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("doggie");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
        
    }
    
    public Doggie() {
        super(BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("doggie");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
    }
    @Override
    public List<StaticEntity> getLoot(Character character, int width, List<String> rareItems) {
        
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
        String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
        loot.add(character.loadCard(cardType, width));
        loot.add(character.addUnequippedItem("doggiecoin", 0));
        return loot;
    }

    /**
     * Deals damage to Hero
     */
    @Override
    public void attack (Hero h, BattleRunner b) {
        if (h instanceof Character) {
            int randomInt = LoopManiaWorld.getRandNum();
            if (randomInt < STUNCHANCE) {
                b.stunCharacter();
            }
        }
        h.takeDamage(this.getAttackDamage(), this);
    }
}
