package unsw.loopmania;

public class NormalCoinStrategy implements CoinStrategy{

    @Override
    public int getSellPrice() {
        return LoopManiaWorld.getRandNum() * 5;
    }

}
