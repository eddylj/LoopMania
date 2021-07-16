package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TowerBuilding extends StaticEntity implements Building{

    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        super.setType("tower");
    }

    public boolean isInRange(Character character) {
        double distance = Math.sqrt(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2));
        if (distance < 3) {
            return true;
        }
        return false;
    }
    public void attack(Enemy e) {
        int maxHealth = e.getHealth();
        e.takeDamage(maxHealth * 0.08);
    }
    
}
