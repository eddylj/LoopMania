// package test;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.Test;

// import org.javatuples.Pair;
// import org.json.JSONObject;
// import org.json.JSONArray;


// import unsw.loopmania.Character;
// //import unsw.loopmania.Enemy;
// import unsw.loopmania.LoopManiaWorld;
// //import unsw.loopmania.LoopManiaWorldControllerLoader;
// //import unsw.loopmania.LoopManiaWorldLoader;
// import unsw.loopmania.MovingEntity;
// import unsw.loopmania.PathPosition;
// //import unsw.loopmania.PathTile;
// //import unsw.loopmania.VampireCastleBuilding;

// public class MovingIntegrationTest {
//     private JSONObject setting;
    
//     public MovingIntegrationTest() {
        
//         JSONObject goals = new JSONObject();
//         setting = new JSONObject();
//         goals.put("goal", "gold");
//         goals.put("quantity", 9000);
//         JSONArray rareItem = new JSONArray();
//         setting.put("rare_items", rareItem);
//         setting.put("goal-condition",goals);
//     }

//     private List<Pair<Integer, Integer>> createPath() {
//         List<Pair<Integer, Integer>> l = new ArrayList<Pair<Integer, Integer>>();
//         l.add(new Pair<Integer, Integer>(0, 0));
//         l.add(new Pair<Integer, Integer>(0, 1));
//         l.add(new Pair<Integer, Integer>(0, 2));
//         l.add(new Pair<Integer, Integer>(1, 2));
//         l.add(new Pair<Integer, Integer>(2, 2));
//         l.add(new Pair<Integer, Integer>(2, 1));
//         l.add(new Pair<Integer, Integer>(2, 0));
//         l.add(new Pair<Integer, Integer>(1, 0));
//         return l;
//     }

//     private void assertPos(MovingEntity e, int x, int y) {
//         assertEquals(e.getX(), x);
//         assertEquals(e.getY(), y);
//     }

//     /*
//     private void assertDifferentPositions(Pair<Integer, Integer> pos1, Pair<Integer, Integer> pos2, Pair<Integer, Integer> pos3) {
//         assertNotEquals(pos1, pos2);
//         assertNotEquals(pos1, pos3);
//         assertNotEquals(pos2, pos3);
//     }*/

//     @Test
//     public void checkExistsTest() {
//         // RIGHT, RIGHT, DOWN, DOWN, LEFT, LEFT, UP, UP
//         List<Pair<Integer, Integer>> l = createPath();
//         LoopManiaWorld d = new LoopManiaWorld(10, 10, l, setting);
//         assertEquals(d.getWidth(), 10);
//         assertEquals(d.getHeight(), 10);
//     }

//     @Test
//     public void DoFullLoop() {
//         List<Pair<Integer, Integer>> l = createPath();
//         LoopManiaWorld d = new LoopManiaWorld(5, 5, l, setting);
//         PathPosition characterPosition = new PathPosition(0, l);
//         Character c = new Character(characterPosition);
//         d.setCharacter(c);

//         assertPos(c, 0, 0);
//         d.tick();
//         assertPos(c, 0, 1);
//         d.tick();
//         assertPos(c, 0, 2);
//         d.tick();
//         assertPos(c, 1, 2);
//         d.tick();
//         assertPos(c, 2, 2);
//         d.tick();
//         assertPos(c, 2, 1);
//         d.tick();
//         assertPos(c, 2, 0);
//         d.tick();
//         assertPos(c, 1, 0);
//         d.tick();
//         assertPos(c, 0, 0);
//     }
// /*
//     @Test
//     public void SlugMovementTest() {
//         List<Pair<Integer, Integer>> l = createPath();
//         LoopManiaWorld d = new LoopManiaWorld(5, 5, l, goals);
//         Enemy slug = d.spawnSlug(3, l);
//         assertPos(slug, 1, 2);
//         Pair<Integer, Integer> oldPos = new Pair<Integer, Integer>(slug.getX(), slug.getY());
//         for (int i = 0; i < 100; i++) {
//             d.tick();
//             Pair<Integer, Integer> newPos = new Pair<Integer, Integer>(slug.getX(), slug.getY());
//             // make sure slug has moved!
//             assertNotEquals(oldPos, newPos);
//             oldPos = newPos;
//         }
//     }
    
    
//     @Test
//     public void ZombieMovementTest() {
//         List<Pair<Integer, Integer>> l = createPath();
//         LoopManiaWorld d = new LoopManiaWorld(5, 5, l, goals);
//         //worldStateHelper h = new worldStateHelper(d);
//         Enemy zombie = d.spawnZombie(3, l);
//         assertPos(zombie, 1, 2);
//         Pair<Integer, Integer> oldPos = new Pair<Integer, Integer>(zombie.getX(), zombie.getY());
//         for (int i = 0; i < 100; i++) {
//             d.moveEntities();
//             Pair<Integer, Integer> newPos = new Pair<Integer, Integer>(zombie.getX(), zombie.getY());
//             // make sure zombie moves every second move!
//             if (i % 2 == 0) {
//                 assertEquals(oldPos, newPos);
//             }
//             else {
//                 assertNotEquals(oldPos, newPos);
//             }
//             oldPos = newPos;
//         }
//     }
    
//     @Test
//     public void VampireMovementTest() {
//         List<Pair<Integer, Integer>> l = createPath();
//         LoopManiaWorld d = new LoopManiaWorld(5, 5, l);
//         worldStateHelper h = new worldStateHelper(d);
//         Enemy vampire = d.spawnVampire(3, h.getOrderedPath());
//         assertPos(vampire, 1, 2);
//         Pair<Integer, Integer> oldPos = new Pair<Integer, Integer>(vampire.getX(), vampire.getY());
//         for (int i = 0; i < 100; i++) {
//             d.tick();
//             Pair<Integer, Integer> newPos = new Pair<Integer, Integer>(vampire.getX(), vampire.getY());
//             // make sure vampire has moved!
//             assertNotEquals(oldPos, newPos);
//             oldPos = newPos;
//         }
//     }
    
//     @Test
//     public void MultipleEnemyMovementTest() {
//         List<Pair<Integer, Integer>> l = createPath();
//         LoopManiaWorld d = new LoopManiaWorld(5, 5, l);
//         worldStateHelper h = new worldStateHelper(d);
//         Enemy vampire = d.spawnVampire(3, h.getOrderedPath());
//         Enemy zombie = d.spawnZombie(4, h.getOrderedPath());
//         Enemy slug = d.spawnSlug(5, h.getOrderedPath());
//         assertPos(vampire, 1, 2);
//         assertPos(zombie, 2, 2);
//         assertPos(slug, 2, 1);
//         for (int i = 0; i < 100; i++) {
//             d.tick();
//             Pair<Integer, Integer> vampirePos = new Pair<Integer, Integer>(vampire.getX(), vampire.getY());
//             Pair<Integer, Integer> zombiePos = new Pair<Integer, Integer>(zombie.getX(), zombie.getY());
//             Pair<Integer, Integer> slugPos = new Pair<Integer, Integer>(slug.getX(), slug.getY());
//             // make sure enemies don't step on eachother!
//             assertDifferentPositions(vampirePos, zombiePos, slugPos);
//         }
//     }

//     @Test
//     public void BuildingMovementTest() {
//         List<Pair<Integer, Integer>> l = createPath();
//         LoopManiaWorld d = new LoopManiaWorld(5, 5, l);
//         worldStateHelper h = new worldStateHelper(d);
//         d.loadVampireCard();
//         VampireCastleBuilding castle = d.convertCardToBuildingByCoordinates(0, 0, 1, 1);
//         Pair<Integer, Integer> currentPos = new Pair<Integer, Integer>(castle.getX(), castle.getY());
//         for (int i = 0; i < 100; i ++) {
//             d.tick();
//             Pair<Integer, Integer> newPos = new Pair<Integer, Integer>(castle.getX(), castle.getY());
//             assertEquals(newPos, currentPos);
//         }
//     }
//     */


// }
