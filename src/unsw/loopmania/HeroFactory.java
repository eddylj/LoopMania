package unsw.loopmania;


public class HeroFactory {

    public Hero create(int cycle) {
     
        return createAlliedSoldier(cycle);
    }
    public Hero create(Enemy e, int cycle) {
    
        return createConvertedEnemy(e, cycle);
    }



    private convertedEnemy createConvertedEnemy(Enemy enemy, int cycle) {
        return new convertedEnemy(enemy, cycle);
    }

    private AlliedSoldier createAlliedSoldier(int cycle) {
        return new AlliedSoldier();
    }

    
}
