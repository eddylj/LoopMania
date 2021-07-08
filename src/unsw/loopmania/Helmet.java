package unsw.loopmania;

public class Helmet extends Protection {
    
    public Helmet(int level, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(level, 400*(1+(level-1)*15/100), x, y);
    }

    @Override
    public int protect(int damage) {
        //Will implement the drop by the scalar value
        return 0;
    }
    
}
