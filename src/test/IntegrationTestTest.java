package test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldControllerLoader;
import unsw.loopmania.LoopManiaWorldLoader;

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
        LoopManiaWorld world = IntegrationTestHelper.createWorld("world_with_complex_goal.json", 1);
        assertEquals(world.getHeight(), 14);
        assertEquals(world.getWidth(), 8);
        System.out.println(world.checkPlayerWin());
        System.out.println(world.getMaxGoal("gold"));
        System.out.println(world.getMaxGoal("cycles"));
        System.out.println(world.getMaxGoal("experience"));
    }
}
