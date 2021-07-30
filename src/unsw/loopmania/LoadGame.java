package unsw.loopmania;

import org.json.JSONObject;

import java.util.List;

import org.json.JSONArray;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.*;
import unsw.loopmania.Factories.*;
import unsw.loopmania.Heroes.Character;
import unsw.loopmania.Items.*;
import unsw.loopmania.Shop.*;
import unsw.loopmania.Enemies.*;

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
        loadCharacter(json.getJSONObject("character"), json);
        loadBuildings(json.getJSONArray("moveBuildings"));
        loadBuildings(json.getJSONArray("cycleBuildings"));
        loadEnemies(json.getJSONArray("enemies"));
        loadShop(json);
    }

    private void loadCharacter(JSONObject characterJSON, JSONObject json) {
        Character character = world.getCharacter();

        // Deal with character stats
        character.setHealth(characterJSON.getInt("health"));
        character.gainXP(characterJSON.getInt("experience"));
        character.setCycle(characterJSON.getInt("cycles"));
        character.gainGold(characterJSON.getInt("gold"));
        character.setBossKills(characterJSON.getInt("bossKills"));
        character.setBuff(characterJSON.getDouble("strengthpotionbuff"));
        if (characterJSON.getBoolean("canTakeDamage")) character.makeInvincible();
        loadCharacterLevelStats(characterJSON.getJSONObject("stats"));
        
        // Deal with character's items
        equipItem(characterJSON.getJSONObject("equippedWeapon"), "weapon");
        equipItem(characterJSON.getJSONObject("equippedHelmet"), "helmet");
        equipItem(characterJSON.getJSONObject("equippedShield"), "shield");
        equipItem(characterJSON.getJSONObject("equippedArmour"), "armour");

        world.setMode(json.getString("gameMode"));

        loadUnequippedItems(characterJSON.getJSONArray("unequippedItems"));

        // Deal with character's cards
        loadCards(characterJSON.getJSONArray("cards"));

        // Deal with allied soldiers
        character.setAliveSoldiers(characterJSON.getInt("aliveSoldiers"));
    }

    private void equipItem(JSONObject item, String slot) {
        if (item.isEmpty()) {
            return;
        }
        Character character = world.getCharacter();
        String type = item.getString("type");
        SimpleIntegerProperty x = new SimpleIntegerProperty(item.getInt("x"));
        SimpleIntegerProperty y = new SimpleIntegerProperty(item.getInt("y"));
        List<String> rareItems = world.getRareItemsList();
        Item equipped = null;
        if (rareItems.contains(type)) {
            RareItemFactory rF = new RareItemFactory(rareItems);
            if (item instanceof ConfusedRareItem) {
                String additional = item.getString("addtional");
                equipped = rF.create(x, y, type, additional);
            }
            else {
                equipped = rF.create(x, y, type);
            }
        }
        else {
            int level = item.getInt("level");
            equipped = iF.create(x, y, type, level);
        }
        character.equip(equipped, slot);
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
            if (item.has("additional")) {
                String additional = item.getString("additional");
                character.addUnequippedConfusedItem(type, additional);
            }
            else {
                if (item.has("level")) {
                    level = item.getInt("level");
                }
                else {
                    level = 0;
                }
                character.addUnequippedItem(type, level);
            }
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
        int width = world.getWidth();
        for (int i = 0; i < cards.length(); i++) {
            JSONObject card = cards.getJSONObject(i);
            String type = card.getString("type");
            character.loadCard(type, width);
        }
    }

    private void loadBuildings(JSONArray buildings) {
        for (int i = 0; i < buildings.length(); i++) {
            JSONObject building = buildings.getJSONObject(i);
            // Some buildings are already loaded into the gam
            // via the JSON entities object
            if (alreadyExists(building)) {
                continue;
            }
            String type = building.getString("type");
            SimpleIntegerProperty x = new SimpleIntegerProperty(building.getInt("X"));
            SimpleIntegerProperty y = new SimpleIntegerProperty(building.getInt("Y"));
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

    private void loadShop(JSONObject json) {
        Shop shop = world.getShop();
        shop.setHealthPotionsBought(json.getInt("healthPotionsBought"));
        shop.setStrengthPotionsBought(json.getInt("strengthPotionsBought"));
    }
}
