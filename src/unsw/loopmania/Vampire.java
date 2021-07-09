package unsw.loopmania;

public class Vampire extends Enemy {

    private int critTurns;
    
    public Vampire (PathPosition position) {
        super(position, 2, 3, 18, 500, 150);
    }

    public Vampire() {
        super(2, 3, 18, 500, 150);
    }

    @Override
    public boolean attack (Character character) {
        return true;
    }

    @Override
    public boolean attack (AlliedSoldier ally) {
        return true;
    }
    
}
