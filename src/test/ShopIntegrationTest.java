package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileNotFoundException;

import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Shop.Shop;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.Axe;

public class ShopIntegrationTest {
    @Test
    public void PickupAndSellAxe() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("garden.json", 1);
        Character character = world.getCharacter();
        for (int i = 0; i < 8; i++) {
            world.tick();
            assertEquals(100, character.getHealth());
        }
        world.tick();
        assertEquals(90, character.getHealth());
        Shop shop = world.getShop();
        Item axe = character.getUnequippedInventoryItemEntityByCoordinates(0, 0);
        assertEquals(((Axe)axe).getLevel(), 1);
        assertEquals(character.getGold(), 100); // Killed a slug to get the axe
        shop.sell(axe);
        assertEquals(character.getGold(), axe.getSellPrice() + 100);
    }

    @Test
    public void BuyHealthPotionsTest() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("one_ring_grind.json", 1);
        world.setMode("beserker");
        Character character = world.getCharacter();
        Shop shop = world.getShop();
        for (int i = 0; i < 100; i++) {
            world.tick();
            assertFalse(world.checkPlayerLoss());
        }
        int gold = character.getGold();
        System.out.println(gold);
        shop.buy("healthpotion");
        assertEquals(gold - 100, character.getGold());
        shop.buy("healthpotion");
        assertEquals(gold - 100 - 150, character.getGold());
        shop.buy("healthpotion");
        assertEquals(gold - 100 - 150 - 200, character.getGold());
        shop.buy("healthpotion");
        assertEquals(gold - 100 - 150 - 200 - 250, character.getGold());
        shop.buy("healthpotion");
        assertEquals(gold - 100 - 150 - 200 - 250 - 300, character.getGold());
        shop.buy("healthpotion");
        assertEquals(gold - 100 - 150 - 200 - 250 - 300 - 350, character.getGold());
    }

    @Test
    public void BuyStrengthPotionsTest() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("one_ring_grind.json", 1);
        world.setMode("beserker");
        Character character = world.getCharacter();
        Shop shop = world.getShop();
        for (int i = 0; i < 100; i++) {
            world.tick();
            assertFalse(world.checkPlayerLoss());
        }
        int gold = character.getGold();
        System.out.println(gold);
        shop.buy("strengthpotion");
        assertEquals(gold - 100, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 100 - 150, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 100 - 150 - 200, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 100 - 150 - 200 - 250, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 100 - 150 - 200 - 250 - 300, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 100 - 150 - 200 - 250 - 300 - 350, character.getGold());
    }

    
}
