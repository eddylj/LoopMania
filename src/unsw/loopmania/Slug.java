package unsw.loopmania;

import java.util.List;

public class Slug extends Enemy{
    private String[] cardDrops;
    /**
     * 
     * @param position
     */
    public Slug (PathPosition position) {
        super(position, 1, 1, 10, 100, 50, 1);
        super.setType("slug");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "zombiepit"};
    }

    public Slug() {
        super(1, 1, 10, 100, 50, 1);
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
            if (num < 1 && !rareItems.isEmpty()) {
                itemType = rareItems.get(LoopManiaWorld.getRandNum() % rareItems.size());
            }
            else {
                itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            }
            if (character.getNonLevelItems().contains(itemType)) {
                return character.addUnequippedItem(itemType, 0);
            }
            else if (num < 5) {
                int level = character.getHighestLevel(itemType) + 1;
                return character.addUnequippedItem(itemType, level);
            }
            else {
                int level = character.getHighestLevel(itemType);
                return character.addUnequippedItem(itemType, level);
            }
        }
        else if (num < 25) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            return character.loadCard(cardType, width);
        }
        return null;
    }
}
