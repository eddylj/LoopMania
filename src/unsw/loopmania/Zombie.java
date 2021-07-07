package unsw.loopmania;

public class Zombie extends Enemy{
    
    public Zombie (PathPosition position) {
        super(position, 2, 2, 16, 250, 100);
    }

    @Override
    public boolean attack (AlliedSoldier ally) {
        return true;
    }
}
