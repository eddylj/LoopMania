package unsw.loopmania.Items;

import unsw.loopmania.LoopManiaWorld;

public class InflatedCoinStrategy implements CoinStrategy{

    @Override
    public int getSellPrice() {
        return LoopManiaWorld.getRandNum() * 12;
    }
    
}
