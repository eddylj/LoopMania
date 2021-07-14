package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ZombiePitBuilding extends StaticEntity implements Building, BuildingOnCycle{

    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
		super.setType("zombiepit");
        //TODO Auto-generated constructor stub
    }

    @Override
    public void updateOnCycle() {
        // TODO Auto-generated method stub
        
    }

	@Override
	public Enemy spawnEnemy(PathPosition pathPosition) {
        EnemyFactory ef = new EnemyFactory();
		return ef.create(pathPosition, "zombie");
	}

	@Override
	public int getChanceOfSpawning1() {
		return 30;
	}

	@Override
	public int getChanceOfSpawning2() {
		return 60;
	}
    
}
