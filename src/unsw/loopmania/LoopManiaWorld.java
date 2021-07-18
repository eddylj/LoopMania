
package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.LoggingPermission;
import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    public static int seed;
    private static Random rand;
    private BuildingFactory bF;
    private int width;
    private int height;
    private JSONObject json;
    private List<String> rareItems;
    private Composite winChecker;
    private List<Entity> nonSpecifiedEntities;
    private Character character;
    private Pair<Integer, Integer> heroCastlePosition;
    private static List<Enemy> enemies;
    private List<BuildingOnCycle> cycleBuildings;
    private List<BuildingOnMove> moveBuildings;
    private BattleRunner battleRunner;

    private List<Pair<Integer, Integer>> orderedPath;

    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, JSONObject json) {
        seed = (int)System.currentTimeMillis();
        LoopManiaWorld.rand = new Random(seed);
        commonConstructorMethod(width, height, orderedPath, json);
    }

    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, JSONObject goals, int seed) {
        LoopManiaWorld.seed = seed;
        LoopManiaWorld.rand = new Random(seed);
        commonConstructorMethod(width, height, orderedPath, goals);
    }

    private void commonConstructorMethod(int width, int height, List<Pair<Integer, Integer>> orderedPath, JSONObject json) {
        this.width = width;
        this.height = height;
        bF = new BuildingFactory();
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        this.orderedPath = orderedPath;
        moveBuildings = new ArrayList<BuildingOnMove>();
        cycleBuildings = new ArrayList<BuildingOnCycle>();
        battleRunner = new BattleRunner();
        this.json = json;
        rareItems = new ArrayList<String>();
        spawn2slugs();
        getRareItems();
    }

    public LoopManiaWorld(int seed) {
        rand = new Random(seed);
    }

    public static void setSeed(int seed) {
        rand = new Random(seed);
    }
    
    public void placeBuildingAtStart(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        Building building = bF.create(x, y, type);
        if (building instanceof BuildingOnCycle) {
            cycleBuildings.add((BuildingOnCycle)building);
        }
        else {
            moveBuildings.add((BuildingOnMove)building);
        }
    }

    public void getRareItems() {
        JSONArray rareItemList = json.getJSONArray("rare_items");
        for (int i = 0; i < rareItemList.length(); i++) {
            rareItems.add(rareItemList.getString(i));
        }
    }

    public void spawn2slugs() {
        int pos1 = LoopManiaWorld.getRandNum() % orderedPath.size();
        int pos2 = LoopManiaWorld.getRandNum() % orderedPath.size();
        if (pos1 == pos2) pos2 += 1;
        spawnSlug(pos1, orderedPath);
        spawnSlug(pos2, orderedPath);
    }
    
    public int getCycles() {
        return character.getCycles();
    }

    public int getGold() {
        return character.getGold();
    }

    public int getXP() {
        return character.getXP();
    }

    public int getHealth() {
        return character.getHealth();
    }

    public boolean isCharacterDead() {
        return character.isDead();
    }

    public void tick() {
        moveEntities();
        List<Enemy> deadEnemies = fight();
        for (Enemy e : deadEnemies) {
            e.getLoot(character, width, rareItems);
        }
        cleanUpFight();
    }

    public List<Enemy> moveEntities() {
        List<Enemy> newEnemies = new ArrayList<Enemy>();
        character.moveDownPath();
        checkBuildingActions(character);
        moveEnemies();
        triggerCycleActions(newEnemies);
        updateEnemyList();
        return newEnemies;
    }

    public void updateEnemyList() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            if (!e.shouldExist().get()) {
                enemies.remove(i);
            }
        }
    }

    public List<Enemy> fight() {
        List<Enemy> deadEnemies = checkForFight();
        return deadEnemies;
    }

    public void cleanUpFight() {
        if (checkPlayerWin()) {
            System.out.println("GAME HAS BEEN WON");
        }
        else if (checkPlayerLoss()) {
            System.out.println("GAME HAS BEEN LOST");
        }
        updateEnemyList();
        character.updateAlliedSoldierAmount();
    }

    public void KillEnemy(Enemy e) {
        enemies.remove(e);
    }

    public void triggerCycleActions(List<Enemy> newEnemies) {
        if (onHeroCastle()) {
            SpawnEnemiesOnCycle(newEnemies);
        }
    }

    public Boolean onHeroCastle() {
        Pair<Integer, Integer> characterPos = new Pair<Integer, Integer>(character.getX(), character.getY());
        return characterPos.equals(heroCastlePosition);
    }

    private List<Enemy> checkForFight() {
        return battleRunner.checkForFight(enemies, moveBuildings);
    }

    public boolean checkPlayerWin() {
        return winChecker.getValue();
    }

    public boolean checkPlayerLoss() {
        return character.getHealth() <= 0;
    }

    public StaticEntity processEnemyLoot(Enemy deadEnemy) {
        return deadEnemy.getLoot(character, width, rareItems);
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    private boolean empty(Pair<Integer, Integer> p) {
        if (!enemies.isEmpty()) {
            for (Enemy e : enemies) {
                if (e.getX() == p.getValue0() && e.getY() == p.getValue1()) {
                    return false;
                }
            }
        }
        if (character.getX() == p.getValue0() && character.getY() == p.getValue1()) {
            return false;
        }
        return true;
    }

    private List<Pair<Integer, Integer>> getAdjacentPathTiles(StaticEntity b) {
        List<Pair<Integer, Integer>> adjacent = new ArrayList<Pair<Integer, Integer>>();
        for (Pair<Integer, Integer> p : orderedPath) {
            if (!empty(p)) continue;

            // If path is above building
            if (p.getValue0() == b.getX() && p.getValue1() == b.getY() - 1) {
                adjacent.add(p);
            }
            // If path is below building
            else if (p.getValue0() == b.getX() && p.getValue1() == b.getY() + 1) {
                adjacent.add(p);
            }
            // If path is left of building
            else if (p.getValue0() == b.getX() - 1 && p.getValue1() == b.getY()) {
                adjacent.add(p);
            }
            // If path is right of building
            else if (p.getValue0() == b.getX() + 1 && p.getValue1() == b.getY()) {
                adjacent.add(p);
            }
        }
        return adjacent;
    }

    public void SpawnEnemiesOnCycle(List<Enemy> newEnemies) {
        // For each building, figure out how many/where to spawn enemies then spawn them
        for (BuildingOnCycle b : cycleBuildings) {
            // adjacent contains every PathTile touching building b
            List<Pair<Integer, Integer>> adjacent = getAdjacentPathTiles((StaticEntity)b);
            int numSpawn = Integer.min(b.generateNumberOfEnemies(), adjacent.size());
            if (numSpawn == 0) {
                return;
            }
            System.out.println(adjacent.size() + "\n");
            for (int i = 0; i < numSpawn; i++) {
                int tile = LoopManiaWorld.getRandNum() % adjacent.size();
                int positioninPath = getNumInPath(adjacent.get(tile));
                Enemy e = b.spawnEnemy(new PathPosition(positioninPath, orderedPath));
                adjacent.remove(tile);
                enemies.add(e);
                newEnemies.add(e);
            }
        }
        // Spawn 2 slugs every cycle
        List<Pair<Integer, Integer>> emptyTiles = getAllEmptyTiles();
        for (int i = 0; i < 2; i++) {
            int position = LoopManiaWorld.getRandNum() % emptyTiles.size();
            newEnemies.add(spawnSlug(position, emptyTiles));
        }
    }


    public StaticEntity loadCard(String type, int width) {
        return character.loadCard(type, width);
    }


    public boolean isValidPlacement(Card card, int x, int y) {
        return card.canBePlaced(x, y, orderedPath);
    }

    public void equipItem(Item i) {
        character.equip(i);
    }

    private CampfireBuilding getClosestCampfire(int x, int y) {
        CampfireBuilding closest = null;
        double closestDistance = getHeight(); // max distance
        for (BuildingOnMove b : moveBuildings) {
            if (b instanceof CampfireBuilding) {
                double distance = Math.sqrt(Math.pow(((StaticEntity)b).getX() - x, 2) + Math.pow(((StaticEntity)b).getY() - y, 2));
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closest = (CampfireBuilding)b;
                }
            }
        }
        return closest;
    }

    /**
     * move all enemies
     */
    private void moveEnemies() { // removed if null
        for (Enemy e: enemies){
            if (e instanceof Vampire) {
                ((Vampire)e).move(getClosestCampfire(e.getX(), e.getY()));
            }
            else {
                e.move();
            }
            System.out.println(e.getType());
            checkBuildingActions(e);
        }
    }

    public void checkBuildingActions(MovingEntity e) {
        for (BuildingOnMove b : moveBuildings) {
            b.updateOnMove(e);
        }
    }

    private void addBuilding(Building b) {
        if (b instanceof BuildingOnCycle) {
            cycleBuildings.add((BuildingOnCycle)b);
        }
        else if (b instanceof BuildingOnMove) {
            moveBuildings.add((BuildingOnMove)b);
        }
    }

    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = character.getMatchingCard(cardNodeX, cardNodeY);
        // now spawn building
        Building newBuilding = bF.create(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), ((StaticEntity)card).getType());
        // buildingEntities.add(newBuilding);
        addBuilding(newBuilding);

        // destroy the card
        character.destroyCard(card, cardNodeX);

        return newBuilding;
    }

    public Slug spawnSlug(int i, List<Pair<Integer, Integer>> orderedPath2) {
        EnemyFactory e = new EnemyFactory();
        Enemy slug =  e.create(new PathPosition(i, orderedPath2), "slug");
        enemies.add(slug);
        return (Slug)slug;
    }

    public void drinkPotion() {
        character.drinkPotion();
    }

    //Getters and Setters
    //////////////////////////////////
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public static int getRandNum() {
        return LoopManiaWorld.rand.nextInt(100);
    }
    public List<Pair<Integer, Integer>> getOrderedPath() {
        List<Pair<Integer, Integer>> copy = new ArrayList<Pair<Integer, Integer>>();
        copy.addAll(orderedPath);
        return copy;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return character.getunequippedInventoryItems();
    }

    public Random getRand() {
        return rand;
    }

    public List<BuildingOnMove> getMoveBuildings() {
        return moveBuildings;
    }

    public List<BuildingOnCycle> getCycleBuildings() {
        return cycleBuildings;
    }

    public int getMaxGoal(String goal) {
        return winChecker.getMax(goal);
    }

    private int getNumInPath(Pair<Integer, Integer> tile) {
        for (int i = 0; i < orderedPath.size(); i++) {
            if (tile.equals(orderedPath.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public Item getEquippedItemByCoordinates(int x) {
        if (x == 0) {
            System.out.println("Getting equipped weapon");
            return character.getWeapon();
        }
        else if (x == 1) {
            return character.getHelmet(); 
        }
        else if (x == 2) {
            return character.getShield();
        }
        else if (x == 3) {
            return character.getArmour();
        }
        else {
            System.out.println("No equipped weapon at " + x);
            return null;
        }
    }

    public List<String> getNonLevelItems() {
        return character.getNonLevelItems();
    }

    private List<Pair<Integer, Integer>> getAllEmptyTiles() {
        List<Pair<Integer, Integer>> tiles = new ArrayList<Pair<Integer, Integer>>();
        for (Pair<Integer, Integer> p : orderedPath) {
            if (empty(p)) tiles.add(p);
        }
        return tiles;
    }
    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        itemFactory iF = new itemFactory();
        this.character = character;
        // character.equip(iF.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), "sword", 1));
        heroCastlePosition = new Pair<Integer, Integer>(character.getX(), character.getY());
        GoalCalculator goals = new GoalCalculator(json.getJSONObject("goal-condition"), character);
        winChecker = goals.getChecker();
        battleRunner.setCharacter(character);
        equipItem(iF.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), "sword", 1));
        // System.out.println();
    }

    public static int getunequippedInventoryWidth() {
        return Inventory.getunequippedInventoryWidth();
    }

    public static int getunequippedInventoryHeight() {
        return Inventory.getunequippedInventoryHeight();
    }

    public Item getUnequippedInventoryItemEntityByCoordinates(int nodeX, int nodeY) {
        return character.getUnequippedInventoryItemEntityByCoordinates(nodeX, nodeY);
    }

    public StaticEntity addUnequippedItem(String type, int i) {
        return character.addUnequippedItem(type, i);
    }

    public void removeUnequippedInventoryItemByCoordinates(int nodeX, int nodeY) {
        character.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }

    public Card getCardByCoordinate(int nodeX) {
        return character.getCardByCoordinate(nodeX);
    }
}
