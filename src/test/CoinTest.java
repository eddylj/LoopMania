package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import unsw.loopmania.Coin;
import unsw.loopmania.Character;

public class CoinTest {

    @Test
    public void checkCoinAmount() {
        Coin c = new Coin();
        assertTrue(c.getAmount() >= 50 && c.getAmount() <= 100);
    }

    @Test
    public void checkCharacterGainGold() {
        Coin c = new Coin();
        Character character = new Character();
        assertTrue(c.shouldExist().get());
        assertEquals(0, character.getGold());
        assertEquals(c.getAmount(), character.getGold());
        assertTrue(!c.shouldExist().get());
    }


}
