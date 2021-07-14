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
    public void updateOnCycle() {
        // TODO Auto-generated method stub
        
    }

	@Override
	public Enemy spawnEnemy(PathPosition pathPosition) {
        EnemyFactory ef = new EnemyFactory();
        return ef.create(pathPosition, "vampire");
	}

	@Override
	public int getChanceOfSpawning1() {
		return 60;
	}

	@Override
	public int getChanceOfSpawning2() {
		return 40;
	}
    
}
