package unsw.loopmania;

import java.util.List;

public class Zombie extends Enemy {
    private boolean canMove;
    private String[] cardDrops;
    
    /**
     * 
     * @param position
     */
    public Zombie (PathPosition position) {
        super(position, 2, 2, 18, 250, 100, 2);
        super.setType("zombie");
        canMove = false;
        cardDrops = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle"};
    }

    public Zombie() {
        super(2, 2, 18, 250, 100, 2);
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
                h.takeDamage(this.getAttackDamage());
            }

        } else {
            h.takeDamage(this.getAttackDamage());
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
            if (num < 1 && !rareItems.isEmpty()) {
                itemType = rareItems.get(LoopManiaWorld.getRandNum() % rareItems.size());
            }
            else {
                itemType = itemList[LoopManiaWorld.getRandNum() % itemList.length];
            }
            if (character.getNonLevelItems().contains(itemType)) {
                return character.addUnequippedItem(itemType, 0);
            }
            else if (num < 10) {
                int level = character.getHighestLevel(itemType) + 1;
                return character.addUnequippedItem(itemType, level);
            }
            else {
                int level = character.getHighestLevel(itemType);
                return character.addUnequippedItem(itemType, level);
            }
        }
        else if (num < 35) {
            String cardType = cardDrops[LoopManiaWorld.getRandNum() % cardDrops.length];
            return character.loadCard(cardType, width);
        }
        return null;
    }
}
