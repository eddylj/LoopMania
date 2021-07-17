/**
 * Functions in this file
 * Constructor 1 (random seed)
 * Constructor 2 (set seed)
 * GetRandNum
 * FillEntityList
 * getWidth
 * getHeight
 * getOrderedPath
 * getEnemies
 * getRand
 * setCharacter
 * tick
 * checkforfight
 * processEnemyloot
 * getLoot (slug)
 * addEntity
 * getNumInPath
 * empty
 * getAdjacentPathTiles
 * GenerateNumOfenemies
 * getAllEnemyTiles
 * spawnEnemiesOnCycle
 * possiblySpawnEnemies
 * killEnemy
 * runBattles
 * loadVampireCard
 * loadCard
 * removeCard
 * addUnequippedItem
 * removeUnequippedInventoryItemByCoordinates
 * runTickMoves
 * removeUnequippedInventoryItem
 * convertItemtoStaticEntity
 * getUnequippedInventoryItemEntityByCoordinates
 * removeItemByPositionInUnequippedInventoryItems
 * getFirstAvailableSlotForItem
 * shiftCardsDownFromXCoordinate
 * moveEnemies
 * moveEnemy (slug)
 * PossiblyGetSpawnPosition
 * addBuilding
 * convertCardToBuildingByCoordinates
 * spawnSlug
 * spawnVampire
 * spawnZombie
 */

package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.LoggingPermission;

import org.javatuples.Pair;
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
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    public static int seed;

    private static Random rand;

    private itemFactory iF;
    private CardFactory cF;
    private EnemyFactory eF;
    private BuildingFactory bF;
    // private BuildingFactory bf;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    private JSONObject json;
    private Composite winChecker;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    private Pair<Integer, Integer> heroCastlePosition;

//    / / TODO = add more lists for other entities, for equipped inventory items, etc...

    // TODO = expand the range of enemies
    private static List<Enemy> enemies;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    private List<BuildingOnCycle> cycleBuildings;
    private List<BuildingOnMove> moveBuildings;

    // TODO = expand the range of items
    private List<Item> unequippedInventoryItems;

    private String[] itemList;
    private String[] cardList;

    private String[] slugCards; // slugs can't drop vampire castles
    private String[] zombieCards; //     
    private String[] vampireCards; // 
    // TODO = expand the range of buildings
    // private List<VampireCastleBuilding> buildingEntities;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, JSONObject goals) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        seed = (int)System.currentTimeMillis();
        LoopManiaWorld.rand = new Random(seed);
        iF = new itemFactory();
        eF = new EnemyFactory();
        bF = new BuildingFactory();
        cF = new CardFactory();
        moveBuildings = new ArrayList<BuildingOnMove>();
        cycleBuildings = new ArrayList<BuildingOnCycle>();
        this.json = goals;
        fillEntityLists();
        // placeHerosCastle();
        spawn2slugs();
        // buildingEntities = new ArrayList<>();
    }

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     * @param seed seed determining random pattern behaviour for testing
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath, JSONObject goals, int seed) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        this.seed = seed;
        rand = new Random(seed);
        iF = new itemFactory();
        eF = new EnemyFactory();
        cF = new CardFactory();
        bF = new BuildingFactory();
        this.json = goals;
        GoalCalculator goal = new GoalCalculator(json, character);
        
        moveBuildings = new ArrayList<BuildingOnMove>();
        cycleBuildings = new ArrayList<BuildingOnCycle>();
        // placeHerosCastle();
        fillEntityLists();
        spawn2slugs();
        // buildingEntities = new ArrayList<>();
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

    public void spawn2slugs() {
        int pos1 = rand.nextInt(100) % orderedPath.size();
        int pos2 = rand.nextInt(100) % orderedPath.size();
        if (pos1 == pos2) pos2 += 1;
        spawnSlug(pos1, orderedPath);
        spawnSlug(pos2, orderedPath);
    }
    public static int getRandNum() {
        return LoopManiaWorld.rand.nextInt(100);
    }

    private void fillEntityLists() {
        itemList = new String[]{"sword", "stake", "staff", "shield", "helmet", "armour", "healthpotion"};
        cardList = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
        slugCards = new String[]{"campfire", "barracks", "tower", "trap", "village", "zombiepit"};
        zombieCards = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle"};
        zombieCards = new String[]{"campfire", "barracks", "tower", "trap", "village", "vampirecastle", "zombiepit"};
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public List<Pair<Integer, Integer>> getOrderedPath() {
        List<Pair<Integer, Integer>> copy = new ArrayList<Pair<Integer, Integer>>();
        copy.addAll(orderedPath);
        return copy;
    }
    
    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return unequippedInventoryItems;
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

    public boolean isCharacterDead() {
        return character.getHealth() <= 0;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
        heroCastlePosition = new Pair<Integer, Integer>(character.getX(), character.getY());
        GoalCalculator goal = new GoalCalculator(json, character);
        winChecker = goal.getChecker();
        // addUnequippedItem("sword", 1);
    }

    public List<Enemy> moveEntities() {
        List<Enemy> newEnemies = new ArrayList<Enemy>();
        character.moveDownPath();
        checkBuildingActions(character);
        moveEnemies();
        triggerCycleActions(newEnemies);
        return newEnemies;
        // triggerBuildingActions();
    }

    public List<Enemy> fight() {
        List<Enemy> deadEnemies = checkForFight();
        return deadEnemies;
    }

    // public List<StaticEntity> processLoot(List<Enemy> deadEnemies) {
    //     List<StaticEntity> items = processEnemyLoot(deadEnemies);
    //     return items;
    // }

    public void cleanUpFight() {
        if (checkPlayerWin()) {
            System.out.println("GAME HAS BEEN WON");
        }
        else if (checkPlayerLoss()) {
            System.out.println("GAME HAS BEEN LOST");
        }
        character.updateAlliedSoldierAmount();
    }

    public void KillEnemy(Enemy e) {
        enemies.remove(e);
    }

    public void triggerCycleActions(List<Enemy> newEnemies) {
        if (onHeroCastle()) {
            SpawnEnemiesOnCycle(newEnemies);
            character.gainCycle();
        }
    }

    public Boolean onHeroCastle() {
        Pair<Integer, Integer> characterPos = new Pair<Integer, Integer>(character.getX(), character.getY());
        return characterPos.equals(heroCastlePosition);
    }


    private List<Enemy> checkForFight() {
        List<Enemy> attacking = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            int supportRadius = e.getSupportRadius();
            double distance = Math.sqrt(Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2));
            System.out.println(String.format("%s distance is %f. Supp radius is %d", e.getType(), distance, supportRadius));
            if (distance <= supportRadius) {
                attacking.add(e);
            }
        }
        System.out.println(String.format("Attacking enemies: %d", attacking.size()));
        List<TowerBuilding> towers = getInRangeTowers();
        BattleRunner b = new BattleRunner(character, attacking, character.getSoldiers(), towers);
        return b.runBattle();
    }

    private List<TowerBuilding> getInRangeTowers() {
        List<TowerBuilding> towers = new ArrayList<TowerBuilding>();
        for (BuildingOnMove b : moveBuildings) {


            if (b.getType().equals("tower")) {
                TowerBuilding tower = (TowerBuilding) b;
                if (tower.range(character)) {
                    towers.add(tower);
                }
            }
        }
        return towers;
    }

    private boolean checkPlayerWin() {
        return winChecker.getValue();
    }

    private boolean checkPlayerLoss() {
        return character.getHealth() <= 0;
    }

    public StaticEntity processEnemyLoot(Enemy deadEnemy) {
        for (Enemy e : enemies) {
            if (e instanceof Slug) {
                return getLoot((Slug)e);
            }
            else if (e instanceof Zombie) {
                return getLoot((Zombie)e);
            }
            else if (e instanceof Vampire) {
                return getLoot((Vampire)e);
            }
        }
        return null;
    }

    private StaticEntity getLoot(Slug slug) {
        int num = rand.nextInt(100);
        StaticEntity item = null;
        // slug drops item better than current
        if (num < 15) {
            String itemType = itemList[rand.nextInt(100) % itemList.length];
            if (itemType.equals("healthpotion")) {
                item = addUnequippedItem(itemType, 0);

            }
            else if (num < 5) {
                int level = getHighestLevel(itemType) + 1;
                item = addUnequippedItem(itemType, level);
            }
            else {
                int level = getHighestLevel(itemType);
                item = addUnequippedItem(itemType, level);
            }
        }
        else if (num < 20) {
            String cardType = slugCards[rand.nextInt(100) % slugCards.length];
            item = loadCard(cardType);
        }
        return item;
    }

    private StaticEntity getLoot(Zombie zombie) {
        int num = rand.nextInt(100);
        StaticEntity item = null;
        // slug drops item better than current
        if (num < 20) {
            String itemType = itemList[rand.nextInt(100) % itemList.length];
            if (itemType.equals("healthpotion")) {
                item = addUnequippedItem(itemType, 0);
            }
            else if (num < 10) {
                int level = getHighestLevel(itemType) + 1;
                item = addUnequippedItem(itemType, level);
            }
            else {
                int level = getHighestLevel(itemType);
                item = addUnequippedItem(itemType, level);
            }
        }
        else if (num < 25) {
            String cardType = zombieCards[rand.nextInt(100) % zombieCards.length];
            item = loadCard(cardType);
        }
        return item;
    }

    private StaticEntity getLoot(Vampire vampire) {
        int num = rand.nextInt(100);
        StaticEntity item = null;
        // slug drops item better than current
        if (num < 30) { // INCRAESE BY 5 WHEN RARE ITEMS ARE ADDED
            String itemType = itemList[rand.nextInt(100) % itemList.length];
            if (itemType.equals("healthpotion")) {
                item = addUnequippedItem(itemType, 0);
            }
            else if (num < 20) {
                int level = getHighestLevel(itemType) + 1;
                item = addUnequippedItem(itemType, level);
            }
            else {
                int level = getHighestLevel(itemType);
                item = addUnequippedItem(itemType, level);
            }
        }
        else if (num < 25) {
            String cardType = vampireCards[rand.nextInt(100) % vampireCards.length];
            item = loadCard(cardType);
        }
        return item;
    }

    private int getHighestLevel(String itemType) {
        return character.getHighestLevel(itemType);
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

    private int getNumInPath(Pair<Integer, Integer> tile) {
        for (int i = 0; i < orderedPath.size(); i++) {
            if (tile.equals(orderedPath.get(i))) {
                return i;
            }
        }
        return -1;
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


    private List<Pair<Integer, Integer>> getAllEmptyTiles() {
        List<Pair<Integer, Integer>> tiles = new ArrayList<Pair<Integer, Integer>>();
        for (Pair<Integer, Integer> p : orderedPath) {
            if (empty(p)) tiles.add(p);
        }
        return tiles;
    }

    public void SpawnEnemiesOnCycle(List<Enemy> newEnemies) {
        
        // For each building, figure out how many/where to spawn enemies then spawn them
        for (BuildingOnCycle b : cycleBuildings) {
            // adjacent contains every PathTile touching building b
            List<Pair<Integer, Integer>> adjacent = getAdjacentPathTiles((StaticEntity)b);
            int numSpawn = Integer.max(b.generateNumberOfEnemies(), adjacent.size());
            if (numSpawn == 0) {
                return;
            }
            System.out.println(adjacent.size() + "\n");
            for (int i = 0; i < numSpawn; i++) {
                int tile = rand.nextInt(100) % adjacent.size();
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
            int position = rand.nextInt(100) % emptyTiles.size();
            newEnemies.add(spawnSlug(position, emptyTiles));
        }
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     * @return list of the enemies to be displayed on screen
     */
    // public List<Enemy> possiblySpawnEnemies(){
    //     // TODO = expand this very basic version
    //     Pair<Integer, Integer> pos = possiblyGetEnemySpawnPosition();
    //     List<Enemy> spawningEnemies = new ArrayList<>();
    //     if (pos != null){
    //         int indexInPath = orderedPath.indexOf(pos);
    //         Enemy enemy = new Slug(new PathPosition(indexInPath, orderedPath));
    //         enemies.add(enemy);
    //         spawningEnemies.add(enemy);
    //     }
    //     return spawningEnemies;
    // }

    // /**
    //  * kill an enemy
    //  * @param enemy enemy to be killed
    //  */
    // private void killEnemy(Enemy enemy){
    //     enemy.destroy();
    //     enemies.remove(enemy);
    // }

    /**
     * run the expected battles in the world, based on current world state
     * @return list of enemies which have been killed
     */
    // public List<Enemy> runBattles() {
    //     // TODO = modify this - currently the character automatically wins all battles without any damage!
    //     List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
    //     for (Enemy e: enemies){
    //         // Pythagoras: a^2+b^2 < radius^2 to see if within radius
    //         // TODO = you should implement different RHS on this inequality, based on influence radii and battle radii
    //         if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < 4){
    //             // fight...
    //             defeatedEnemies.add(e);
    //         }
    //     }
    //     for (Enemy e: defeatedEnemies){
    //         // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
    //         // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
    //         // due to mutating list we're iterating over
    //         killEnemy(e);
    //     }
    //     return defeatedEnemies;
    // }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard(){
        // if adding more cards than have, remove the first card...
        // if (cardEntities.size() >= getWidth()){
        //     // TODO = give some cash/experience/item rewards for the discarding of the oldest card
        //     removeCard(0);
        // }
        // VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        // cardEntities.add(vampireCastleCard);
        // return vampireCastleCard;
        return null;
    }

    public StaticEntity loadCard(String type) {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            int gold = rand.nextInt(100) + 1;
            character.gainGold(gold * 3);
            // TODO = give some cash/experience/item rewards for the discarding of the oldest card
            removeCard(0);
        }
        CardFactory cf = new CardFactory();
        // Card card = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        Card card = cf.create(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0), type);
        cardEntities.add(card);
        return (StaticEntity)card;
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * spawn a sword in the world and return the sword entity
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public StaticEntity addUnequippedItem (String type, int level){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new sword, as we know we have at least made a slot available...
        itemFactory f = new itemFactory();
        Item item = null;
        if (type.equals("healthpotion")) {
            item = f.create(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), type);
        }
        else {
            item = f.create(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), type, level);
        }
        // Item item = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), level);
        unequippedInventoryItems.add(item);
        return (StaticEntity)item;
    }

    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Item item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){ // DEAD GONE GOODBYE
        character.moveDownPath();
        moveEnemies();
    }

    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Item item){
        // item.destroy();
        unequippedInventoryItems.remove(item);
    }

    private StaticEntity convertItemToStaticEntity(Item item) {
        return (StaticEntity)item;
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    public Item getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Item e: unequippedInventoryItems){
            StaticEntity entity = convertItemToStaticEntity(e);
            if ((entity.getX() == x) && (entity.getY() == y)){
                return e;
            }
        }
        return null;
    }

    public Item getEquippedItem(int slot) {
        if (slot == 0) {
            return character.get
        }
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Item item = unequippedInventoryItems.get(index);
        ((StaticEntity)item).destroy();
        // int goldAmount = item.getReplaceCost();
        character.gainGold(item.getReplaceCost());
        unequippedInventoryItems.remove(index);
    }

    // /**
    //  * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
    //  * @param index index from 0 to length-1
    //  */
    // private void removeItemByPositionInUnequippedInventoryItems(int index){
    //     Entity item = unequippedInventoryItems.get(index);
    //     item.destroy();
    //     unequippedInventoryItems.remove(index);
    // }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveEnemies() {
        // TODO = expand to more types of enemy
        if (enemies.isEmpty()) {
            return;
        }
        for (Enemy e : enemies){
            e.move();
            System.out.println(e.getType());
            checkBuildingActions(e);
        }
    }

    public void checkBuildingActions(MovingEntity e) {
        for (BuildingOnMove b : moveBuildings) {
            b.updateOnMove(e);
        }
    }

    private void moveEnemy(Slug slug) {
        PathPosition currentPos = slug.getPosition();
        int position = currentPos.getPositionInPath();

    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    // // ! SHOULD BE UNUSED I THINK
    // private Pair<Integer, Integer> possiblyGetEnemySpawnPosition(){ 
    //     // TODO = modify this
        
    //     // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
    //     Random rand = new Random();
    //     int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
    //     // TODO = change based on spec
    //     if ((choice == 0) && (enemies.size() < 2)){
    //         List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
    //         int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
    //         // inclusive start and exclusive end of range of positions not allowed
    //         int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
    //         int endNotAllowed = (indexPosition + 3)%orderedPath.size();
    //         // note terminating condition has to be != rather than < since wrap around...
    //         for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
    //             orderedPathSpawnCandidates.add(orderedPath.get(i));
    //         }

    //         // choose random choice
    //         Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

    //         return spawnPosition;
    //     }
    //     return null;
    // }

    private void addBuilding(Building b) {
        if (b instanceof BuildingOnCycle) {
            cycleBuildings.add((BuildingOnCycle)b);
        }
        else if (b instanceof BuildingOnMove) {
            moveBuildings.add((BuildingOnMove)b);
        }
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }

        
        // now spawn building
        Building newBuilding = bF.create(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), ((StaticEntity)card).getType());
        // buildingEntities.add(newBuilding);
        addBuilding(newBuilding);

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }

    public Slug spawnSlug(int i, List<Pair<Integer, Integer>> orderedPath2) {
        EnemyFactory e = new EnemyFactory();
        Enemy slug =  e.create(new PathPosition(i, orderedPath2), "slug");
        enemies.add(slug);
        return (Slug)slug;
    }
    public void spawnVampire(int i, List<Pair<Integer, Integer>> orderedPath2) {
        EnemyFactory e = new EnemyFactory();
        Enemy vampire =  e.create(new PathPosition(i, orderedPath2), "vampire");
        enemies.add(vampire);
    }
    public void spawnZombie(int i, List<Pair<Integer, Integer>> orderedPath2) {
        EnemyFactory e = new EnemyFactory();
        Enemy zombie =  e.create(new PathPosition(i, orderedPath2), "zombie");
        enemies.add(zombie);
    }
}
