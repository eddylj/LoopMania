package unsw.loopmania;

public class DeflatedCoinStrategy implements CoinStrategy{

    @Override
    public int getSellPrice() {
        // TODO Auto-generated method stub
        return LoopManiaWorld.getRandNum();
    }
    
}
