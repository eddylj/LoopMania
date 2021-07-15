package unsw.loopmania;

public class convertedEnemy extends AlliedSoldier{
    Enemy enemy;
    
    public convertedEnemy(Enemy enemy, int cycle) {
        super(cycle);
        this.enemy = enemy;
    }

}
