package unsw.loopmania.Items;
import javafx.beans.property.SimpleIntegerProperty;

public class DoggieCoin extends Item{
    public static final int NOPRICE = 0;
    private CoinStrategy strategy;

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        super.setType("doggiecoin");
        strategy = new NormalCoinStrategy();
    }

    @Override
    public int getPrice() {
        return NOPRICE;
    }

    @Override
    public int getSellPrice() {
        return strategy.getSellPrice();
    }

    @Override
    public int getReplaceCost() {
        return (int) (getSellPrice() * 0.4);
    }
    
    public void setInflatedCoin() {
        this.strategy = new InflatedCoinStrategy();
    }

    public void setDeflatedCoin() {
        this.strategy = new DeflatedCoinStrategy();
    }

    public String getStrategy() {
        if (strategy instanceof InflatedCoinStrategy) {
            return "inflated";
        }
        else if (strategy instanceof DeflatedCoinStrategy) {
            return "deflated";
        }
        else {
            return "normal";
        }
    }
}
