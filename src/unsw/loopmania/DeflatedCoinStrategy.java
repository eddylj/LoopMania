package unsw.loopmania;

public class DeflatedCoinStrategy implements CoinStrategy{

    @Override
    public int getSellPrice() {
        return LoopManiaWorld.getRandNum();
    }
    
}
