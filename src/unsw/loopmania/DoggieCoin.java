package unsw.loopmania;
import javafx.beans.property.SimpleIntegerProperty;

public class DoggieCoin extends StaticEntity implements Item{
     
    CoinStrategy  strategy;

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setType("doggiecoin");
        strategy = new NormalCoinStrategy();
    }

    @Override
    public int getPrice() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getSellPrice() {
        // TODO Auto-generated method stub
        return strategy.getSellPrice();
    }

    @Override
    public int getReplaceCost() {
        // TODO Auto-generated method stub
        return (int) (getSellPrice() * 0.4);
    }
    
    public void setInflatedCoin() {
        this.strategy = new InflatedCoinStrategy();
    }

    public void setDeflatedCoin() {
        this.strategy = new DeflatedCoinStrategy();
    }

}
