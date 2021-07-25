package unsw.loopmania;

public class InflatedCoinStrategy implements CoinStrategy{

    @Override
    public int getSellPrice() {
        return LoopManiaWorld.getRandNum() * 12;
    }
    
}
