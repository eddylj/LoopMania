package unsw.loopmania;

public class Slug extends Enemy{
    
    public Slug (PathPosition position) {
        super(position, 1, 1, 10, 100, 50);
    }

    public Slug() {
        super(1, 1, 10, 100, 50);
    }
}
