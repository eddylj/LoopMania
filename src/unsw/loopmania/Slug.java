package unsw.loopmania;

import org.javatuples.Pair;

public class Slug extends Enemy{
    String[] cardDrops;

    public Slug (PathPosition position) {
        super(position, 1, 1, 10, 100, 50);
        super.setType("slug");
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "zombiepit"};
    }


    public Slug() {
        super(1, 1, 10, 100, 50);
        super.setType("slug");
    }

    public StaticEntity getLoot(Character character) {
        int num = LoopManiaWorld.getRandNum();
        // slug drops item better than current
        if (num < 15) {
            String itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            if (itemType.equals("healthpotion")) {
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
        else if (num < 20) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            return character.loadCard(cardType);
        }
    }
}
