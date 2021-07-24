
package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    
    /**
     * 
     * @param width
     * @param height
     * @param orderedPath
     * @param json
     */
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

    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, JSONObject goals) {
        seed = (int)System.currentTimeMillis();
        LoopManiaWorld.rand = new Random(seed);
        commonConstructorMethod(width, height, orderedPath, goals);
    }

    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, JSONObject goals, int seed) {
        LoopManiaWorld.seed = seed;
        LoopManiaWorld.rand = new Random(seed);
        commonConstructorMethod(width, height, orderedPath, goals);
    }

    public LoopManiaWorld(int seed) {
        rand = new Random(seed);
    }

    /**
     * Places building that occur at start of game
     * @param x
     * @param y
     * @param type
     */
    public void placeBuildingAtStart(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        Building building = bF.create(x, y, type);
        if (building instanceof BuildingOnCycle) {
            cycleBuildings.add((BuildingOnCycle)building);
        }
        else {
            moveBuildings.add((BuildingOnMove)building);
        }
    }

    /**
     * Parse through json to get rare items
     */
    public void getRareItems() {
        JSONArray rareItemList = json.getJSONArray("rare_items");
        for (int i = 0; i < rareItemList.length(); i++) {
            rareItems.add(rareItemList.getString(i));
        }
    }

    /**
     * Spawns 2 slugs at start of game
     */
    public void spawn2slugs() {
        int pos1 = LoopManiaWorld.getRandNum() % orderedPath.size();
        int pos2 = LoopManiaWorld.getRandNum() % orderedPath.size();
        if (pos1 == pos2) pos2 += 1;
        spawnSlug(pos1, orderedPath);
        spawnSlug(pos2, orderedPath);
    }
    
    /**
     * Checks if character is dead
     * @return
     */
    public boolean isCharacterDead() {
        return character.isDead();
    }
    /**
     * Simulates one tick in game
     */
    public void tick() {
        moveEntities();
        List<Enemy> deadEnemies = fight();
        for (Enemy e : deadEnemies) {
            e.getLoot(character, width, rareItems);
        }
        cleanUpFight();
    }
    /**
     * Moves character and enemies and check if they activated any buildings
     * @return
     */
    public List<Enemy> moveEntities() {
        List<Enemy> newEnemies = new ArrayList<Enemy>();
        character.moveDownPath();
        checkBuildingActions(character);
        moveEnemies();
        triggerCycleActions(newEnemies);
        updateEnemyList();
        return newEnemies;
    }
    /**
     * Checks if any enemies died during battle
     */
    public void updateEnemyList() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            if (!e.shouldExist().get()) {
                enemies.remove(i);
            }
        }
    }
    /**
     * Checks for a fight with character and runs the battle
     * @return list of defeated enemies
     */
    public List<Enemy> fight() {
        List<Enemy> deadEnemies = checkForFight();
        return deadEnemies;
    }
    /**
     * Checks if player has won or nt and updates allied soldier amount
     */
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
    /**
     * Kills an enemy
     * @param e
     */
    public void KillEnemy(Enemy e) {
        enemies.remove(e);
    }
    /**
     * Updates cycle count after lap and spawns new enemies
     * @param newEnemies
     */
    public void triggerCycleActions(List<Enemy> newEnemies) {
        if (onHeroCastle()) {
            SpawnEnemiesOnCycle(newEnemies);
            character.gainCycle();
        }
    }
    /**
     * Checks if character is standing on hero castle
     * @return
     */
    public Boolean onHeroCastle() {
        Pair<Integer, Integer> characterPos = new Pair<Integer, Integer>(character.getX(), character.getY());
        return characterPos.equals(heroCastlePosition);
    }
    /**
     * Checks if character is in range of enemies
     * @return list of defeated enemies
     */
    private List<Enemy> checkForFight() {
        return battleRunner.checkForFight(enemies, moveBuildings);
    }
    /**
     * check if character has won
     * @return true of false if player has won
     */
    public boolean checkPlayerWin() {
        return winChecker.getValue();
    }
    /**
     * check if character has lost
     * @return true of false if player has lost
     */
    public boolean checkPlayerLoss() {
        return character.getHealth() <= 0;
    }
    /**
     * Processes loot drop from enemy
     * This function is only called by the frontend
     * @param deadEnemy
     * @return Loot
     */
    public StaticEntity processEnemyLoot(Enemy deadEnemy) {
        return deadEnemy.getLoot(character, width, rareItems);
    }
    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
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
            int numSpawn;
            if (b instanceof VampireCastleBuilding) {
                numSpawn = Integer.min(((VampireCastleBuilding)b).generateNumberOfEnemies(character.getCycles().get()), adjacent.size());
            }
            else {
                numSpawn = Integer.min(b.generateNumberOfEnemies(), adjacent.size());
            }
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

    /**
     * Check if card placement is valid
     * This function is only called by the frontend
     * @param card
     * @param x
     * @param y
     * @return boolean
     */
    public boolean isValidPlacement(Card card, int x, int y) {
        return card.canBePlaced(x, y, orderedPath);
    }

    /**
     * Equips character with item
     * @param i
     */
    public void equipItem(Item i) {
        character.equip(i);
    }
    /**
     * Gets closest campfire from Vampire
     * @param x
     * @param y
     * @return closest campfire
     */
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
     * Move all enemies. This method is called every tick.
     */
    private void moveEnemies() { 
        for (Enemy e: enemies){
            if (e instanceof Vampire) {
                ((Vampire)e).move(getClosestCampfire(e.getX(), e.getY()));
            }
            else {
                e.move();
            }
            checkBuildingActions(e);
        }
    }

    /**
     * Updates all buildings that update on move
     * @param e
     */
    public void checkBuildingActions(MovingEntity e) {
        for (BuildingOnMove b : moveBuildings) {
            b.updateOnMove(e);
        }
    }

    /**
     * Adds building to list of buildings
     * @param b
     */
    private void addBuilding(Building b) {
        if (b instanceof BuildingOnCycle) {
            cycleBuildings.add((BuildingOnCycle)b);
        }
        else if (b instanceof BuildingOnMove) {
            moveBuildings.add((BuildingOnMove)b);
        }
    }

    /**
     * Converts a dragged card into a building on the map and then removes the card afterwards
     * @param cardNodeX int: Column coordinate of card in card inventory
     * @param cardNodeY int: Row coordinate of card in card inventory
     * @param buildingNodeX int: Column coordinate of building on the map
     * @param buildingNodeY int: Row coordinate of building on the map
     */
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

    /**
     * Spawns a slug  on an empty PathTile
     * @param i int: position in ordered path
     * @param orderedPath2 List<Pair<Integer, Integer>>: the orderedPath
     * @return Slug: the slug that was just spawned
     */
    public Slug spawnSlug(int i, List<Pair<Integer, Integer>> orderedPath) {
        EnemyFactory e = new EnemyFactory();
        Enemy slug =  e.create(new PathPosition(i, orderedPath), "slug");
        enemies.add(slug);
        return (Slug)slug;
    }

    /**
     * Makes character drink potion (if possible)
     */
    public void drinkPotion() {
        character.drinkPotion();
    }

    //Getters and Setters
    //////////////////////////////////
    /**
     * Gets the width of the map
     * @return int: the width of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the map
     * @return int: the height of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Static method that generates a random number so other classes
     * that uses 'random' numbers can generate them using the same Random object
     * @return int: A random number between 0-99
     */
    public static int getRandNum() {
        return LoopManiaWorld.rand.nextInt(100);
    }

    /**
     * Gets the orderedPath that the map uses
     * @return List<Pair<Integer, Integer>>: the orderedPath
     */
    public List<Pair<Integer, Integer>> getOrderedPath() {
        List<Pair<Integer, Integer>> copy = new ArrayList<Pair<Integer, Integer>>();
        copy.addAll(orderedPath);
        return copy;
    }

    /**
     * Gets a list of enemies on the map
     * @return List<Enemy>: the list of enemies
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets a list of the character's unequipped items
     * @return List<Item>: list of the character's unequipped items
     */
    public List<Item> getItems() {
        return character.getunequippedInventoryItems();
    }

    /**
     * Gets a list of all buildings that update when an entity moves on/within range of it
     * @return List<BuildingOnMove>: a list of buildings
     */
    public List<BuildingOnMove> getMoveBuildings() {
        return moveBuildings;
    }

    /**
     * Gets a list of all buildings that update at the start of every cycle
     * @return List<BuildingOnCycle>: a list of buildings
     */
    public List<BuildingOnCycle> getCycleBuildings() {
        return cycleBuildings;
    }

    /**
     * Gets the maximum amount of a goal 'resource' (gold, experience, cycles)
     * that is needed to win the game
     * @param goal String: The particular resource in question
     * @return int: How much is needed
     */
    public int getMaxGoal(String goal) {
        return winChecker.getMax(goal);
    }

    /**
     * Gets the index of a tile in the orderedPath
     * @param tile Pair<Integer, Integer>: The tile in question
     * @return int: The tile's index
     */
    private int getNumInPath(Pair<Integer, Integer> tile) {
        for (int i = 0; i < orderedPath.size(); i++) {
            if (tile.equals(orderedPath.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets an equipped item by its index in the equipped item inventory
     * @param x int: index of item
     * @return Item: The equipped item
     */
    public Item getEquippedItemByCoordinates(int x) {
        if (x == 0) {
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
            return null;
        }
    }

    /**
     * Gets a list of all items that don't have levels
     * (e.g. HealthPotion and TheOneRing)
     * @return List<String>: a list of all items that don't have levels
     */
    public List<String> getNonLevelItems() {
        return character.getNonLevelItems();
    }

    /**
     * Gets a list of all empty (spawnable) tiles
     * @return List<Pair<Integer, Integer>>: a list of all empty tiles
     */
    private List<Pair<Integer, Integer>> getAllEmptyTiles() {
        List<Pair<Integer, Integer>> tiles = new ArrayList<Pair<Integer, Integer>>();
        for (Pair<Integer, Integer> p : orderedPath) {
            if (empty(p)) tiles.add(p);
        }
        return tiles;
    }

    /**
     * Gets the character
     * @return Character: The character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        ItemFactory iF = new ItemFactory();
        this.character = character;
        heroCastlePosition = new Pair<Integer, Integer>(character.getX(), character.getY());
        GoalCalculator goals = new GoalCalculator(json.getJSONObject("goal-condition"), character);
        winChecker = goals.getChecker();
        battleRunner.setCharacter(character);
        equipItem(iF.create(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0), "sword", 3));
    }

    /**
     * Gets the width of the unequipped inventory
     * @return int: The width
     */
    public static int getunequippedInventoryWidth() {
        return Inventory.getunequippedInventoryWidth();
    }

    /**
     * Gets the height of the unequipped inventory
     * @return int: The height
     */
    public static int getunequippedInventoryHeight() {
        return Inventory.getunequippedInventoryHeight();
    }

    /**
     * Gets an unequipped item from the character's inventory by its coordinates
     * @param nodeX int: The column coordinate of the item in the character's unequipped item inventory
     * @param nodeY int: The row coordinate of the item in the character's unequipped item inventory
     * @return Item: The item at the coordinates
     */
    public Item getUnequippedInventoryItemEntityByCoordinates(int nodeX, int nodeY) {
        return character.getUnequippedInventoryItemEntityByCoordinates(nodeX, nodeY);
    }

    /**
     * Adds an unequipped item to the character's inventory
     * @param type String: The type of item to add
     * @param i int: The level of the item
     * @return StaticEntity: The item added
     */
    public StaticEntity addUnequippedItem(String type, int level) {
        return character.addUnequippedItem(type, level);
    }

    /**
     * Removes an unequipped item to the character's inventory by its coordinates
     * @param nodeX int: The column coordinate of the item in the character's unequipped item inventory
     * @param nodeY int: The row coordinate of the item in the character's unequipped item inventory
     */
    public void removeUnequippedInventoryItemByCoordinates(int nodeX, int nodeY) {
        character.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }

    /**
     * Gets a card from the character's card inventory from its index
     * @param x int: index of the card
     * @return Card: The card
     */
    public Card getCardByCoordinate(int nodeX) {
        return character.getCardByCoordinate(nodeX);
    }

    /**
     * Sets the seed that is used to generate random numbers
     * @param seed int: The seed to be used
     */
    public static void setSeed(int seed) {
        rand = new Random(seed);
    }
    
    /**
     * Gets the character's cycles
     * @return int: The character's cycles
     */
    public IntegerProperty getCycles() {
        return character.getCycles();
    }

    /**
     * Gets the character's gold
     * @return int: The character's gold
     */
    public IntegerProperty getGold() {
        return character.getGoldProperty();
    }

    /**
     * Gets the character's experience
     * @return int: The character's experience
     */
    public IntegerProperty getXP() {
        return character.getXP();
    }

    /**
     * Gets the character's health
     * @return IntegerProperty: The character's health
     */
    public IntegerProperty getHealth() {
        return character.getHealthProperty();
    }


///////////////////
//Functions for testing

/**
 * Integration tests aren't working on Gradle, so this is the only way to spawn a vampire
 * because without using the LoopManiaWorldLoader, it's impossible to load in a 
 */
    public Enemy spawnVampire() {
        EnemyFactory eF = new EnemyFactory();
        Enemy vamp = eF.create(new PathPosition(0, orderedPath), "vampire");
        enemies.add(vamp);
        return vamp;
    }
}