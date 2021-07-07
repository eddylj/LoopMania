package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Gold extends StaticEntity{
    private int amount;
    
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y, int amount) {
        super(x, y);
        this.amount = amount;
        //TODO Auto-generated constructor stub
    }
    
}
