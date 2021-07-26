package unsw.loopmania;
import javafx.beans.property.SimpleIntegerProperty;

public class DoggieCoin extends StaticEntity implements Item{
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

	@Override
	public boolean isWeapon() {
		return false;
	}

	@Override
	public boolean isShield() {
		return false;
	}

	@Override
	public boolean isRing() {
		return false;
	}

}
