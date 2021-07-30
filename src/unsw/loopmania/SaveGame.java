package unsw.loopmania;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import unsw.loopmania.Buildings.BuildingOnCycle;
import unsw.loopmania.Buildings.BuildingOnMove;
import unsw.loopmania.Cards.Card;
import unsw.loopmania.Enemies.Enemy;
import unsw.loopmania.Entities.Entity;
import unsw.loopmania.Entities.StaticEntity;
import unsw.loopmania.Items.DoggieCoin;
import unsw.loopmania.Items.Item;

public class SaveGame {
    private LoopManiaWorld world;
    private JSONObject save;

    public SaveGame(LoopManiaWorld world) {
        this.world = world;
        save = new JSONObject();
    }

    public void SaveWorld(String name) {
        saveCharacter();
        saveNonSpecifiedEntities();
        saveEnemies();
        saveCycleBuildings();
        saveMoveBuildings();
        save.put("seed", world.getSeed());
        save.put("healthPotionsBought", world.getHealthPotionsBought());
        save.put("strengthPotionsBought", world.getStrengthPotionsBought());
        save.put("gameMode", world.getSelectedGamemode());

        JSONObject json = world.getJSON();
        json.put("saveWorld", save);

        try {
            FileWriter file = new FileWriter(String.format("backup/%s.json", name));
            file.write(json.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCharacter() {
        Character character = world.getCharacter();
        JSONObject characterJSON = new JSONObject();
        characterJSON.put("experience", character.getXP().get());
        characterJSON.put("gold", character.getGold());
        characterJSON.put("cycles", character.getCycles().get());
        characterJSON.put("health", character.getHealth());
        characterJSON.put("bossKills", character.getBossKills());
        characterJSON.put("strengthpotionbuff", character.getStrengthPotionBuff());
        characterJSON.put("canTakeDamage", character.canTakeDamage());
        addEquippedWeapon(characterJSON);
        addEquippedArmour(characterJSON);
        addEquippedHelmet(characterJSON);
        addEquippedShield(characterJSON);
        addCharacterStats(characterJSON, character);
        characterJSON.put("aliveSoldiers", character.getAlliedSoldierCount());
        addUnequippedInventory(characterJSON, character.getInventory());
        save.put("character", characterJSON);
    }

    private void saveNonSpecifiedEntities() {
        JSONArray nonSpecifiedEntities = new JSONArray();
        List<Entity> nonSpecifiedEntitiyList = world.getNonSpecifiedEntities();
        if (nonSpecifiedEntitiyList.isEmpty()) {
            return;
        }
        for (Entity entity : nonSpecifiedEntitiyList) {
            JSONObject entityJSON = new JSONObject();
            entityJSON.put("type", entity.getType());
            entityJSON.put("x", entity.getX());
            entityJSON.put("y", entity.getY());
            nonSpecifiedEntities.put(entityJSON);
        }
        save.put("nonSpecifiedEntities", nonSpecifiedEntities);
    }

    private void saveEnemies() {
        List<Enemy> list = world.getEnemies();
        JSONArray enemies = new JSONArray();
        for (Enemy enemy : list) {
            JSONObject enemyObject = new JSONObject();
            enemyObject.put("type", enemy.getType());
            enemyObject.put("index", enemy.getIndexOfPosition());
            enemies.put(enemyObject);
        }
        save.put("enemies", enemies);
    }

    private void saveCycleBuildings() {
        JSONArray buildings = new JSONArray();
        List<BuildingOnCycle> list = world.getCycleBuildings();
        for (BuildingOnCycle building : list) {
            JSONObject buildingObject = new JSONObject();
            buildingObject.put("type", ((StaticEntity)building).getType());
            buildingObject.put("X", ((StaticEntity)building).getX());
            buildingObject.put("Y", ((StaticEntity)building).getY());
            buildings.put(buildingObject);
        }
        save.put("cycleBuildings", buildings);
    }

    private void saveMoveBuildings() {
        JSONArray buildings = new JSONArray();
        List<BuildingOnMove> list = world.getMoveBuildings();
        for (BuildingOnMove building : list) {
            JSONObject buildingObject = new JSONObject();
            buildingObject.put("type", ((StaticEntity)building).getType());
            buildingObject.put("X", ((StaticEntity)building).getX());
            buildingObject.put("Y", ((StaticEntity)building).getY());
            buildings.put(buildingObject);
        }
        save.put("moveBuildings", buildings);
    }

    private void addEquippedWeapon(JSONObject character) {
        JSONObject equippedWeapon = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(0);
        equippedWeapon.put("type", item.getType());
        equippedWeapon.put("level", ((Weapon)item).getLevel());
        equippedWeapon.put("x", item.getX());
        equippedWeapon.put("y", item.getY());
        if (item instanceof ConfusedRareItem) {
            equippedWeapon.put("additional", ((ConfusedRareItem)item).getAdditional().getType());
        }
        character.put("equippedWeapon", equippedWeapon);
    }

    private void addEquippedHelmet(JSONObject character) {
        JSONObject equippedHelmet = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(1);
        if (item != null) {
            equippedHelmet.put("type", item.getType());
            equippedHelmet.put("level", ((Protection)item).getLevel());
            equippedHelmet.put("x", item.getX());
            equippedHelmet.put("y", item.getY());
        }
        character.put("equippedHelmet", equippedHelmet);
    }

    private void addEquippedShield(JSONObject character) {
        JSONObject equippedShield = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(2);
        if (item != null) {
            equippedShield.put("type", item.getType());
            equippedShield.put("level", ((Protection)item).getLevel());
            equippedShield.put("x", item.getX());
            equippedShield.put("y", item.getY());
            if (item instanceof ConfusedRareItem) {
                equippedShield.put("additional", ((ConfusedRareItem)item).getAdditional().getType());
            }
        }
        character.put("equippedShield", equippedShield);
    }
    
    private void addEquippedArmour(JSONObject character) {
        JSONObject equippedArmour = new JSONObject();
        Item item = world.getEquippedItemByCoordinates(3);
        if (item != null) {
            equippedArmour.put("type", item.getType());
            equippedArmour.put("level", ((Protection)item).getLevel());
            equippedArmour.put("x", item.getX());
            equippedArmour.put("y", item.getY());
        }
        character.put("equippedArmour", equippedArmour);
    }

    private void addCharacterStats(JSONObject characterJSON, Character character) {
        CharacterStats cs = character.getStats();
        JSONObject stats = new JSONObject();
        stats.put("sword", cs.getHighestLevel("sword"));
        stats.put("stake", cs.getHighestLevel("stake"));
        stats.put("staff", cs.getHighestLevel("staff"));
        stats.put("shield", cs.getHighestLevel("shield"));
        stats.put("armour", cs.getHighestLevel("armour"));
        stats.put("helmet", cs.getHighestLevel("helmet"));
        characterJSON.put("stats", stats);
    }

    private void addUnequippedInventory(JSONObject character, Inventory inventory) {
        JSONArray items = new JSONArray();
        for (Item item : inventory.getunequippedInventoryItems()) {
            JSONObject itemObject = new JSONObject();
            itemObject.put("type", item.getType());
            if (item instanceof DoggieCoin) {
                itemObject.put("strategy", ((DoggieCoin)item).getStrategy());
            }
            else if (item instanceof ConfusedRareItem) {
                itemObject.put("additional", ((ConfusedRareItem)item).getAdditional().getType());
            }
            else if (item instanceof Protection) {
                itemObject.put("level", ((Protection)item).getLevel());
            }
            else if (item instanceof Weapon) {
                itemObject.put("level", ((Weapon)item).getLevel());
            }
            items.put(itemObject);
        }

        JSONArray cards = new JSONArray();
        for (Card card : inventory.getCards()) {
            JSONObject cardJSON = new JSONObject();
            cardJSON.put("type", ((StaticEntity)card).getType());
            cards.put(cardJSON);
        }

        character.put("unequippedItems", items);
        character.put("cards", cards);
    }
}
