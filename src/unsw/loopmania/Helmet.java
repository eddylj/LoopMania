package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Helmet extends Protection {
    
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y, int level) {
        super(level, 400*(1+(level-1)*15/100), x, y);
        super.setType("helmet");
    }
    
    public Helmet(int level) {
        super(level, 400*(1+(level-1)*15/100));
        super.setType("helmet");
    }


    @Override
    public int protect(int damage) {
        //Will implement the drop by the scalar value
        return 0;
    }
    
}
