package unsw.loopmania;

public class Slug extends Enemy{
    

    public Slug (PathPosition position) {
        super(position, 1, 1, 0, 100, 50);
        super.setType("slug");
    }


    public Slug() {
        super(1, 1, 10, 100, 50);
    }

}
