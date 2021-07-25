package unsw.loopmania;

import java.util.List;

public class Doggie extends Enemy implements Boss{
    public static final int BATTLERADIUS = 1;
    public static final int SUPPORTRADIUS = 1;
    public static final int DAMAGE = 20;
    public static final int GOLDAMOUNT = 0;
    public static final int HEALTH = 500;
    public static final int XP = 600;
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
    public StaticEntity getLoot(Character character, int width, List<String> rareItems) {
        return null;
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
