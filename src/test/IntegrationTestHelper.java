package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;
import unsw.loopmania.PathTile;

public class IntegrationTestHelper {

    public static LoopManiaWorld createWorld(String fileName, int seed) throws FileNotFoundException{
        JSONObject json = new JSONObject(new JSONTokener(new FileReader("bin/worlds/" + fileName)));
        JSONObject goals = json.getJSONObject("goal-condition");
        List<Pair<Integer, Integer>> orderedPath = IntegrationTestHelper.loadPathTiles(json.getJSONObject("path"), 8, 14);
        int width = json.getInt("width");
        int height = json.getInt("height");

        LoopManiaWorld world = new LoopManiaWorld(width, height, orderedPath, goals, seed);

        JSONArray jsonEntities = json.getJSONArray("entities");

        // load non-path entities later so that they're shown on-top
        for (int i = 0; i < jsonEntities.length(); i++) {
            IntegrationTestHelper.loadEntity(world, jsonEntities.getJSONObject(i), orderedPath);
        }

        return world;

    }

    /**
     * load an entity into the world
     * @param world backend world object
     * @param json a JSON object to parse (different from the )
     * @param orderedPath list of pairs of x, y cell coordinates representing game path
     */
    private static void loadEntity(LoopManiaWorld world, JSONObject currentJson, List<Pair<Integer, Integer>> orderedPath) {
        String type = currentJson.getString("type");
        int x = currentJson.getInt("x");
        int y = currentJson.getInt("y");
        int indexInPath = orderedPath.indexOf(new Pair<Integer, Integer>(x, y));
        // assert indexInPath != -1;

        Entity entity = null;
        // TODO = load more entity types from the file
        switch (type) {
        case "hero_castle":
            Character character = new Character(new PathPosition(indexInPath, orderedPath));
            world.setCharacter(character);
            entity = character;
            world.placeBuildingAtStart(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y), "heros_castle");
            break;
        case "path_tile":
            throw new RuntimeException("path_tile's aren't valid entities, define the path externally.");
        default:
            world.placeBuildingAtStart(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y), type);
        // TODO Handle other possible entities
        }
        world.addEntity(entity);
    }

    /**
     * load path tiles
     * @param path json data loaded from file containing path information
     * @param width width in number of cells
     * @param height height in number of cells
     * @return list of x, y cell coordinate pairs representing game path
     */
    private static List<Pair<Integer, Integer>> loadPathTiles(JSONObject path, int width, int height) {
        if (!path.getString("type").equals("path_tile")) {
            // ... possible extension
            throw new RuntimeException(
                    "Path object requires path_tile type.  No other path types supported at this moment.");
        }
        PathTile starting = new PathTile(new SimpleIntegerProperty(path.getInt("x")), new SimpleIntegerProperty(path.getInt("y")));
        if (starting.getY() >= height || starting.getY() < 0 || starting.getX() >= width || starting.getX() < 0) {
            throw new IllegalArgumentException("Starting point of path is out of bounds");
        }
        // load connected path tiles
        List<PathTile.Direction> connections = new ArrayList<>();
        for (Object dir: path.getJSONArray("path").toList()){
            connections.add(Enum.valueOf(PathTile.Direction.class, dir.toString()));
        }

        if (connections.size() == 0) {
            throw new IllegalArgumentException(
                "This path just consists of a single tile, it needs to consist of multiple to form a loop.");
        }

        // load the first position into the orderedPath
        PathTile.Direction first = connections.get(0);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(starting.getX(), starting.getY()));

        int x = starting.getX() + first.getXOffset();
        int y = starting.getY() + first.getYOffset();

        // add all coordinates of the path into the orderedPath
        for (int i = 1; i < connections.size(); i++) {
            orderedPath.add(Pair.with(x, y));
            
            if (y >= height || y < 0 || x >= width || x < 0) {
                throw new IllegalArgumentException("Path goes out of bounds at direction index " + (i - 1) + " (" + connections.get(i - 1) + ")");
            }
            
            PathTile.Direction dir = connections.get(i);
            PathTile tile = new PathTile(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            x += dir.getXOffset();
            y += dir.getYOffset();
            if (orderedPath.contains(Pair.with(x, y)) && !(x == starting.getX() && y == starting.getY())) {
                throw new IllegalArgumentException("Path crosses itself at direction index " + i + " (" + dir + ")");
            }
        }
        // we should connect back to the starting point
        if (x != starting.getX() || y != starting.getY()) {
            throw new IllegalArgumentException(String.format(
                    "Path must loop back around on itself, this path doesn't finish where it began, it finishes at %d, %d.",
                    x, y));
        }
        return orderedPath;
    }
}
