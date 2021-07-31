package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Shop.Shop;
import unsw.loopmania.Items.Item;
import unsw.loopmania.Items.Axe;
import unsw.loopmania.Items.DoggieCoin;
import unsw.loopmania.Items.Staff;
import unsw.loopmania.Enemies.Slug;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Enemies.Doggie;

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
        assertEquals(gold - 150, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 150 - 200, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 150 - 200 - 250, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 150 - 200 - 250 - 300, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 150 - 200 - 250 - 300 - 350, character.getGold());
        shop.buy("strengthpotion");
        assertEquals(gold - 150 - 200 - 250 - 300 - 350 - 400, character.getGold());
    }

    /**
     * When player gets invincibilty potion, player doesn't drink it and dies to doggy
     * @throws FileNotFoundException
     */
    @Test
    public void DieToDoggyTest() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("2_by_2.json", 3);
        Character character = world.getCharacter();
        for (int i = 0; i < 83; i++) {
            world.tick();
            System.out.println(i);
            assertFalse(world.checkPlayerLoss());
        }
        world.tick();
        assertTrue(world.checkPlayerLoss());
    }

    /**
     * Player equips staff instead of sword and is able to defeat doggie on cycle 20
     * @throws FileNotFoundException
     */
    @Test
    public void SellDoggyCoinTest() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("2_by_2.json", 3);
        Character character = world.getCharacter();
        Shop shop = world.getShop();
        for (int i = 0; i < 63; i++) {
            System.out.println(i);
            world.tick();
            assertFalse(world.checkPlayerLoss());            
        }
        Item item = world.getUnequippedInventoryItemEntityByCoordinates(0, 0);
        assertTrue(item instanceof Staff);
        world.equipItem(item, "weapon");
        for (int i = 0; i < 30; i++) {
            world.tick();
            assertFalse(world.checkPlayerLoss());
        }
        Item doggiecoin = world.getUnequippedInventoryItemEntityByCoordinates(2, 1);
        assertTrue(doggiecoin instanceof DoggieCoin);
        assertEquals(6661, character.getGold());
        shop.sell(doggiecoin);
        assertEquals(6746, character.getGold());
        assertFalse(world.checkPlayerLoss());
    }

    

    
}
