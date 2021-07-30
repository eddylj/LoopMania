package unsw.loopmania.Items;

import unsw.loopmania.LoopManiaWorld;

public class DeflatedCoinStrategy implements CoinStrategy{

    @Override
    public int getSellPrice() {
        return LoopManiaWorld.getRandNum();
    }
    
}
