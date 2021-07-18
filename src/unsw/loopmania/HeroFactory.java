package unsw.loopmania;


public class HeroFactory {

    public Hero create() {
        return createAlliedSoldier();
    }
    public Hero create(Enemy e) {
        return createConvertedEnemy(e);
    }

    private convertedEnemy createConvertedEnemy(Enemy enemy) {
        return new convertedEnemy(enemy);
    }

    private AlliedSoldier createAlliedSoldier() {
        return new AlliedSoldier();
    }

    
}
