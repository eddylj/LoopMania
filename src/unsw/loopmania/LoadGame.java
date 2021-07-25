package unsw.loopmania;

import org.json.JSONObject;
import org.json.JSONArray;

import javafx.beans.property.SimpleIntegerProperty;

public class LoadGame {
    private LoopManiaWorld world;
    private JSONObject save;
    private ItemFactory iF;
    private BuildingFactory bF;
    private EnemyFactory eF;

    public LoadGame(LoopManiaWorld world, JSONObject json) {
        this.world = world;
        this.save = json;
        this.iF = new ItemFactory();
        this.bF = new BuildingFactory();
        this.eF = new EnemyFactory();
    }

    public void loadWorld() {
        JSONObject json = save.getJSONObject("saveWorld");
        loadCharacter(json.getJSONObject("character"));
        loadBuildings(json.getJSONArray("moveBuildings"));
        loadBuildings(json.getJSONArray("cycleBuildings"));
        loadEnemies(json.getJSONArray("enemies"));
    }

    private void loadCharacter(JSONObject c) {
        Character character = world.getCharacter();

        // Deal with character stats
        character.setHealth(c.getInt("health"));
        character.gainXP(c.getInt("experience"));
        character.setCycle(c.getInt("cycles"));
        loadCharacterLevelStats(c.getJSONObject("stats"));
        
        // Deal with character's items
        equipItem(c.getJSONObject("equippedWeapon"));
        equipItem(c.getJSONObject("equippedHelmet"));
        equipItem(c.getJSONObject("equippedShield"));
        equipItem(c.getJSONObject("equippedArmour"));
        loadUnequippedItems(c.getJSONArray("unequippedItems"));

        // Deal with character's cards
        loadCards(c.getJSONArray("cards"));

        // Deal with allied soldiers
        character.setAliveSoldiers(c.getInt("aliveSoldiers"));
    }

    private void equipItem(JSONObject item) {
        if (item == null) {
            return;
        }
        Character character = world.getCharacter();
        String type = item.getString("type");
        SimpleIntegerProperty x = new SimpleIntegerProperty(item.getInt("x"));
        SimpleIntegerProperty y = new SimpleIntegerProperty(item.getInt("y"));
        int level = item.getInt("level");
        character.equip(iF.create(x, y, type, level));
    }

    private void loadUnequippedItems(JSONArray items) {
        if (items.length() == 0) {
            return;
        }
        Character character = world.getCharacter();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String type = item.getString("type");
            int level;
            if (item.has("level")) {
                level = item.getInt("level");
            }
            else {
                level = 0;
            }
            character.addUnequippedItem(type, level);
        }
    }

    private void loadCharacterLevelStats(JSONObject levels) {
        Character character = world.getCharacter();
        character.setStats("sword", levels.getInt("sword"));
        character.setStats("staff", levels.getInt("staff"));
        character.setStats("stake", levels.getInt("stake"));
        character.setStats("helmet", levels.getInt("helmet"));
        character.setStats("armour", levels.getInt("armour"));
        character.setStats("shield", levels.getInt("shield"));
    } 

    private void loadCards(JSONArray cards) {
        if (cards.length() == 0) {
            return;
        }
        Character character = world.getCharacter();
        for (int i = 0; i < cards.length(); i++) {
            JSONObject card = cards.getJSONObject(i);
            String type = card.getString("type");
            int x = card.getInt("x");
            character.loadCard(type, x);
        }
    }

    private void loadBuildings(JSONArray buildings) {
        for (int i = 0; i < buildings.length(); i++) {
            JSONObject building = buildings.getJSONObject(i);
            if (alreadyExists(building)) {
                continue;
            }
            String type = building.getString("type");
            SimpleIntegerProperty x = new SimpleIntegerProperty(building.getInt("x"));
            SimpleIntegerProperty y = new SimpleIntegerProperty(building.getInt("y"));
            Building b = (Building)bF.create(x, y, type);
            world.addBuilding(b);
        }
    }

    private boolean alreadyExists(JSONObject newBuilding) {
        JSONArray preloadedBuildings = save.getJSONArray("entities");
        for (int i = 0; i < preloadedBuildings.length(); i++) {
            JSONObject building = preloadedBuildings.getJSONObject(i);
            if (building.equals(newBuilding)) {
                return true;
            }
        }
        return false;
    }

    private void loadEnemies(JSONArray enemies) {
        for (int i = 0; i < enemies.length(); i++) {
            JSONObject enemy = enemies.getJSONObject(i);
            String type = enemy.getString("type");
            int position = enemy.getInt("index");
            Enemy e = eF.create(new PathPosition(position, world.getOrderedPath()), type);
            world.addEnemy(e);
        }
    }
}
