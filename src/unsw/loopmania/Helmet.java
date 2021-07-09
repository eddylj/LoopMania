package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Helmet extends Protection {
    
    public Helmet(int level, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(level, 400*(1+(level-1)*15/100), x, y);
    }
    
    public Helmet(int level) {
        super(level, 400*(1+(level-1)*15/100));
    }


    @Override
    public int protect(int damage) {
        //Will implement the drop by the scalar value
        return 0;
    }
    
}
