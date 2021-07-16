package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePitBuilding extends StaticEntity implements Building, BuildingOnCycle{

    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
		super.setType("zombiepit");
    }


	public Enemy spawnEnemy(PathPosition pathPosition) {
        EnemyFactory ef = new EnemyFactory();
		return ef.create(pathPosition, "zombie");
	}


	public int generateNumberOfEnemies() {
        int num = LoopManiaWorld.getRandNum();
        int spawn1 = 30;
        int spawn2 = spawn1 + 60;
        if (num <= spawn1) {
            return 1;
        }
        else if (num <= spawn2) {
            return 2;
        }
        else {
            return 3;
        }
    }
    
}
