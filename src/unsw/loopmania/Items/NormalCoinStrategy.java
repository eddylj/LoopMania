package unsw.loopmania.Items;

import unsw.loopmania.LoopManiaWorld;

public class NormalCoinStrategy implements CoinStrategy{

    @Override
    public int getSellPrice() {
        return LoopManiaWorld.getRandNum() * 5;
    }

}
