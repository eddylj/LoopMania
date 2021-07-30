package unsw.loopmania.Factories;

import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.Hero;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Heroes.ConvertedEnemy;

public class HeroFactory {

    public Hero create() {
        return createAlliedSoldier();
    }
    public Hero create(Enemy e) {
        return createConvertedEnemy(e);
    }

    private ConvertedEnemy createConvertedEnemy(Enemy enemy) {
        return new ConvertedEnemy(enemy);
    }

    private AlliedSoldier createAlliedSoldier() {
        return new AlliedSoldier();
    }

    
}
