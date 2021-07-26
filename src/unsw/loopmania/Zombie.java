package unsw.loopmania;

import java.util.List;

public class Zombie extends Enemy {
    public static final int BATTLERADIUS = 2;
    public static final int SUPPORTRADIUS = 2;
    public static final int DAMAGE = 18;
    public static final int GOLDAMOUNT = 250;
    public static final int HEALTH = 100;
    public static final int XP = 500;
    private boolean canMove;
    private String[] cardDrops;
    
    /**
     * 
     * @param position
     */
    public Zombie (PathPosition position) {
        super(position, BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("zombie");
        canMove = false;
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle"};
    }

    public Zombie() {
        super(BATTLERADIUS, SUPPORTRADIUS, DAMAGE, GOLDAMOUNT, HEALTH, XP);
        super.setType("zombie");
        canMove = false;
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle"};
    }

    /**
     * Deals damage to Hero
     * @param h
     * @param b
     */
    public void attack(Hero h, BattleRunner b) {
        // Accounts for zombie critical bite against AS
        if (h instanceof AlliedSoldier) {
            int randomInt = LoopManiaWorld.getRandNum();
            if (randomInt >= 0 && randomInt <= 9) {
                h.setHealth(0);
                b.convertAllyToZombie((AlliedSoldier)h);
            } else {
                h.takeDamage(this.getAttackDamage(), this);
            }

        } else {
            h.takeDamage(this.getAttackDamage(), this);
        }
    }

    /**
     * move the enemy
     */
    @Override
    public void move() {
        if (canMove) {
            super.move();
            canMove = false;
        }
        else {
            canMove = true;
        }
    }

    /**
     * Generates random loot for player for zombie
     * @param character
     * @param width
     * @param rareItems
     * @return StaticEntity loot
     */
    public StaticEntity getLoot(Character character, int width, List<String> rareItems) {
        int num = LoopManiaWorld.getRandNum();
        if (num < 20) {
            String itemType;
            if (num < 20 && !rareItems.isEmpty()) { // num < 1
                System.out.println("boutta get rare item");
                itemType = rareItems.get(LoopManiaWorld.getRandNum() % rareItems.size());
                System.out.println(String.format("[Rare ITEM] %s", itemType));
            }
            else {
                itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            }
            if (character.getNonLevelItems().contains(itemType)) {
                System.out.println(String.format("[ITEM] %s", itemType));
                return character.addUnequippedItem(itemType, 0);
            }
            else if (num < 10) {
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
        else if (num < 35) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            System.out.println(String.format("[ITEM] %s", cardType));
            return character.loadCard(cardType, width);
        }
        System.out.println("Enemy didnt drop any loot");
        return null;
    }
}
