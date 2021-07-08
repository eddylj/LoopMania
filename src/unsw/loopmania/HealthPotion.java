package unsw.loopmania;

public class HealthPotion extends StaticEntity {
    
    /**
     * The class of health potion
     * @param x
     * @param y
     */
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
    }

    /**
     * Will heal the given character
     * @param character
     */
    public void heal(Character character) {

    }
}
