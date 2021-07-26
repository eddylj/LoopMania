package unsw.loopmania;

import java.util.List;

public class Slug extends Enemy{
    public static final int BATTLERADIUS = 1;
    public static final int SUPPORTRADIUS = 1;
    public static final int DAMAGE = 10;
    public static final int GOLDAMOUNT = 100;
    public static final int HEALTH = 50;
    public static final int XP = 100;

    private String[] cardDrops;
    /**
     * 
     * @param position
     */
    public Slug (PathPosition position) {
        super(position, BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("slug");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "zombiepit"};
    }

    public Slug() {
        super(BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("slug");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "zombiepit"};
    }
    /**
     * Generates random loot for player for zombie
     * @param character
     * @param width
     * @param rareItems
     * @return StaticEntity loot
     */
    @Override
    public StaticEntity getLoot(Character character, int width, List<String> rareItems) {
        int num = LoopManiaWorld.getRandNum();
        // slug drops item better than current
        if (num < 15) {
            String itemType;
            if (num < 15 && !rareItems.isEmpty()) { // num < 1
                System.out.println("boutta get rare item");
                itemType = rareItems.get(LoopManiaWorld.getRandNum() % rareItems.size());
                System.out.println(String.format("[RARE ITEM] %s", itemType));
                System.out.println(String.format("\n\n\n\n\n\n%s\n\n\nhmm\nplslss\nshow\nup", itemType));
            }
            else {
                itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            }
            if (character.getNonLevelItems().contains(itemType)) {
                System.out.println(String.format("[ITEM] %s\n\n\n\nnn\n\n\n\nnnn\n\n\nnnn\nn\n\n", itemType));
                return character.addUnequippedItem(itemType, 0);
            }
            else if (num < 5) {
                int level = character.getHighestLevel(itemType) + 1;
                if (level > 10) {
                    level = 10;
                }
                System.out.println(String.format("[ITEM] %s", itemType));
                return character.addUnequippedItem(itemType, level);
            }
            else {
                int level = character.getHighestLevel(itemType);
                System.out.println(String.format("[ITEM] %s", itemType));
                return character.addUnequippedItem(itemType, level);
            }
        }
        else if (num < 25) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            System.out.println(String.format("[ITEM] %s", cardType));
            return character.loadCard(cardType, width);
        }
        System.out.println("Enemy didnt drop any loot");
        return null;
    }
}
