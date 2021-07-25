package unsw.loopmania;

import java.util.List;

public class ElanMuske extends Enemy implements Boss{
    public static final int BATTLERADIUS = 1;
    public static final int SUPPORTRADIUS = 1;
    public static final int DAMAGE = 35;
    public static final int GOLDAMOUNT = 800;
    public static final int HEALTH = 700;
    public static final int XP = 1700;
    private String[] cardDrops;

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
    public StaticEntity getLoot(Character character, int width, List<String> rareItems) {
        return null;
    }

    @Override
    public void attack (Hero h, BattleRunner b) {
        h.takeDamage(this.getAttackDamage(), this);
        b.healenemies();
    }
}
