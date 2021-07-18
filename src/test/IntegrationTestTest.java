package test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldControllerLoader;
import unsw.loopmania.LoopManiaWorldLoader;
import unsw.loopmania.StaticEntity;

public class IntegrationTestTest {
    private int apple;

    public IntegrationTestTest() {
        apple = 3;
    }

    public static JSONObject parseJSON(String fileName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            return new JSONObject(content);
        }
        catch (IOException e) {
            return null;
        }
    }
    
    @Test
    public void workingTest() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("three_by_three_world.json", 1);
        Item sword = world.getUnequippedInventoryItemEntityByCoordinates(0, 0);
        world.equipItem(sword);
        for (int i = 0; i < 8; i++) {
            world.tick();
            assertFalse(world.checkPlayerLoss());
        }
        assertTrue(world.checkPlayerWin());
    }

    /**
     * Player walks in a 3x3 circle attacking slugs and then dies on the 37th tick.
     * When player picks up armour, player does not equip it.
     * @throws FileNotFoundException
     */
    @Test
    public void checkLossTest() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("three_by_three_world_cant_win.json", 4);
        Item sword = world.getUnequippedInventoryItemEntityByCoordinates(0, 0);
        world.equipItem(sword);
        for (int i = 0; i < 36; i++) {
            System.out.println(i);
            world.tick();
            
            assertFalse(world.checkPlayerLoss());
        }
        world.tick();
        assertTrue(world.checkPlayerLoss());
    }

    /**
     * Same test as checkLossTest except when player picks up armour, player equips it,
     * so the player survives for an extra 14 ticks (almost 2 cycles)
     * @throws FileNotFoundException
     */
    @Test
    public void checkLaterLossTest() throws FileNotFoundException {
        LoopManiaWorld world = IntegrationTestHelper.createWorld("three_by_three_world_cant_win.json", 4);
        Item sword = world.getUnequippedInventoryItemEntityByCoordinates(0, 0);
        Item other = world.getItems().get(0);
        System.out.println(((StaticEntity)sword).getType());
        System.out.println(((StaticEntity)other).getType());
        world.equipItem(sword);
        for (int i = 0; i < 36; i++) {
            List<Item> inventory = world.getItems();
            if (!inventory.isEmpty()) {
                Item item = world.getItems().get(0);
                world.equipItem(item);
            }
            world.tick();
            
            assertFalse(world.checkPlayerLoss());
        }
        world.tick();
        assertFalse(world.checkPlayerLoss());
        for (int i = 0; i < 13; i++) {
            world.tick();
            assertFalse(world.checkPlayerLoss());
        }
        world.tick();
        assertTrue(world.checkPlayerLoss());
    }
}
