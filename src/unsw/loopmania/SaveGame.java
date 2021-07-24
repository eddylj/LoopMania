package unsw.loopmania;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

public class SaveGame {
    private LoopManiaWorld world;
    private JSONObject save;

    public SaveGame(LoopManiaWorld world) {
        this.world = world;
        save = new JSONObject();
    }

    public void SaveWorld() {
        saveCharacter();
        saveNonSpecifiedEntities();
        saveHerosCastlePosition();
        saveEnemies();
        saveCycleBuildings();
        saveMoveBuildings();
        save.put("seed", world.getSeed());
    }

    private void saveCharacter() {
        Character character = world.getCharacter();
        JSONObject c = new JSONObject();
        c.put("experience", character.getXP());
        c.put("gold", character.getGold());
        c.put("cycles", character.getCycles());
        c.put("health", character.getHealth());
        addEquippedWeapon(c);
        addEquippedArmour(c);
        addEquippedHelmet(c);
        addEquippedShield(c);
        addCharacterState(c, character);
        c.put("aliveSoldiers", character.getAlliedSoldierCount());
        addUnequippedInventory(c, character.getInventory());
        save.put("character", c);
    }

    private void saveNonSpecifiedEntities() {
        JSONArray nonSpecifiedEntities = new JSONArray();
        for (Entity e : world.getNonSpecifiedEntities()) {
            JSONObject entity = new JSONObject();
            entity.put("type", e.getType());
            entity.put("x", e.getX());
            entity.put("y", e.getY());
            nonSpecifiedEntities.put(entity);
        }
        save.put("nonSpecifiedEntities", nonSpecifiedEntities);
    }

    private void saveHerosCastlePosition() {
        Pair<Integer, Integer> pos = world.getHerosCastlePosition();
        JSONObject heroscastle = new JSONObject();
        heroscastle.put("X", pos.getValue0());
        heroscastle.put("Y", pos.getValue1());
        save.put("herosCastlePosition", heroscastle);
    }

    private void saveEnemies() {
        List<Enemy> l = world.getEnemies();
        JSONArray enemies = new JSONArray();
        for (Enemy e : l) {
            JSONObject enemy = new JSONObject();
            enemy.put("type", e.getType());
            enemy.put("X", e.getX());
            enemy.put("Y", e.getY());
            enemies.put(enemy);
        }
        save.put("enemies", enemies);
    }

    private void saveCycleBuildings() {
        JSONArray buildings = new JSONArray();
        List<BuildingOnCycle> l = world.getCycleBuildings();
        for (BuildingOnCycle b : l) {
            JSONObject building = new JSONObject();
            building.put("type", ((StaticEntity)b).getType());
            building.put("X", ((StaticEntity)b).getX());
            building.put("Y", ((StaticEntity)b).getY());
            buildings.put(building);
        }
        save.put("cycleBuildings", buildings);
    }

    private void saveMoveBuildings() {
        JSONArray buildings = new JSONArray();
        List<BuildingOnMove> l = world.getMoveBuildings();
        for (BuildingOnMove b : l) {
            JSONObject building = new JSONObject();
            building.put("type", ((StaticEntity)b).getType());
            building.put("X", ((StaticEntity)b).getX());
            building.put("Y", ((StaticEntity)b).getY());
            buildings.put(building);
        }
        save.put("moveBuildings", buildings);
    }

    private void addEquippedWeapon(JSONObject c) {
        JSONObject equippedWeapon = new JSONObject();
        Item i = world.getEquippedItemByCoordinates(0);
        equippedWeapon.put("type", ((StaticEntity)i).getType());
        equippedWeapon.put("level", ((Weapon)i).getLevel());
        equippedWeapon.put("x", ((StaticEntity)i).getX());
        equippedWeapon.put("y", ((StaticEntity)i).getY());
        c.put("equippedWeapon", equippedWeapon);
    }

    private void addEquippedHelmet(JSONObject c) {
        JSONObject equippedHelmet = new JSONObject();
        Item i = world.getEquippedItemByCoordinates(1);
        equippedHelmet.put("type", ((StaticEntity)i).getType());
        equippedHelmet.put("level", ((Protection)i).getLevel());
        equippedHelmet.put("x", ((StaticEntity)i).getX());
        equippedHelmet.put("y", ((StaticEntity)i).getY());
        c.put("equippedHelmet", equippedHelmet);
    }

    private void addEquippedShield(JSONObject c) {
        JSONObject equippedShield = new JSONObject();
        Item i = world.getEquippedItemByCoordinates(2);
        equippedShield.put("type", ((StaticEntity)i).getType());
        equippedShield.put("level", ((Protection)i).getLevel());
        equippedShield.put("x", ((StaticEntity)i).getX());
        equippedShield.put("y", ((StaticEntity)i).getY());
        c.put("equippedShield", equippedShield);
    }
    
    private void addEquippedArmour(JSONObject c) {
        JSONObject equippedArmour = new JSONObject();
        Item i = world.getEquippedItemByCoordinates(3);
        equippedArmour.put("type", ((StaticEntity)i).getType());
        equippedArmour.put("level", ((Protection)i).getLevel());
        equippedArmour.put("x", ((StaticEntity)i).getX());
        equippedArmour.put("y", ((StaticEntity)i).getY());
        c.put("equippedArmour", equippedArmour);
    }

    private void addCharacterState(JSONObject c, Character character) {
        CharacterStats cs = character.getStats();
        JSONObject stats = new JSONObject();
        stats.put("sword", cs.getHighestLevel("sword"));
        stats.put("stake", cs.getHighestLevel("stake"));
        stats.put("staff", cs.getHighestLevel("staff"));
        stats.put("shield", cs.getHighestLevel("shield"));
        stats.put("armour", cs.getHighestLevel("armour"));
        stats.put("helmet", cs.getHighestLevel("helmet"));
        c.put("stats", stats);
    }

    private void addUnequippedInventory(JSONObject character, Inventory inventory) {
        JSONArray items = new JSONArray();
        for (Item i : inventory.getunequippedInventoryItems()) {
            JSONObject item = new JSONObject();
            item.put("type", ((StaticEntity)i).getType());
            item.put("x", ((StaticEntity)i).getX());
            item.put("y", ((StaticEntity)i).getY());
            if (i instanceof Protection) {
                item.put("level", ((Protection)i).getLevel());
            }
            else if (i instanceof Weapon) {
                item.put("level", ((Weapon)i).getLevel());
            }
            items.put(item);
        }

        JSONArray cards = new JSONArray();
        for (Card c : inventory.getCards()) {
            JSONObject card = new JSONObject();
            card.put("type", ((StaticEntity)c).getType());
            card.put("x", ((StaticEntity)c).getX());
            card.put("y", ((StaticEntity)c).getY());
            cards.put(card);
        }

        character.put("unequippedItems", items);
        character.put("cards", cards);
    }


    

}
