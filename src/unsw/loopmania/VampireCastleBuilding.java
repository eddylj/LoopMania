package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends StaticEntity implements Building, BuildingOnCycle{

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
		super.setType("vampirecastle");
        //TODO Auto-generated constructor stub
    }

	@Override
	public Enemy spawnEnemy(PathPosition pathPosition) {
        EnemyFactory ef = new EnemyFactory();
        return ef.create(pathPosition, "vampire");
	}

	@Override
	public int generateNumberOfEnemies() {
        int num = LoopManiaWorld.getRandNum();
        int spawn1 = 60;
        if (num <= spawn1) {
            return 1;
        }
        else {
            return 2;
        }
    }
    
}
