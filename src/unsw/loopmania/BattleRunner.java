package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

public class BattleRunner {
    private Character character;
    private ArrayList<Enemy> enemies;
    private ArrayList<AlliedSoldier> allies;

    public BattleRunner(Character c, ArrayList<Enemy> enemies, ArrayList<AlliedSoldier> allies) {
        this.character = c;
        this.enemies = enemies;
        this.allies = allies;
    }




    /**
     * run the expected battles in the world, based on current world state
     * @return boolean if battle is won or lost
     */
    public boolean runBattle() {
        
        return true;
    }

    public void runEnemyAttacks() {
        for (Enemy e : enemies) {
            if (!allies.isEmpty()) {
                AlliedSoldier a = allies.get(0);
                e.attack(a, this);
                if (a.isDead()) {
                    killAlly(a);
                }
            } else {
                e.attack(character);
            }
        }
    }
    public void runHeroAttacks() {
        if (!allies.isEmpty()) {
            for (AlliedSoldier a : allies) {
                Enemy e = enemies.get(0);
                a.attack(e);
                if (e.isDead()){
                    killEnemy(e);
                }
            }
        }
        Enemy e = enemies.get(0);
        character.attack(e);
    }


    private void killEnemy(Enemy enemy){
        enemy.destroy();
        enemies.remove(enemy);
    }


    public void killAlly(AlliedSoldier ally) {
        allies.remove(ally);
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard(){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // TODO = give some cash/experience/item rewards for the discarding of the oldest card
            removeCard(0);
        }
        VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(vampireCastleCard);
        return vampireCastleCard;
    }
    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */








    /**
     * run the expected battles in the world, based on current world state
     * @return list of enemies which have been killed
     */
    public List<Enemy> runBattles() {
        // TODO = modify this - currently the character automatically wins all battles without any damage!
        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
        for (Enemy e: character.getEnemies()){
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            // TODO = you should implement different RHS on this inequality, based on influence radii and battle radii
            if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < 4){
                // fight...
                defeatedEnemies.add(e);
            }
        }
        for (Enemy e: defeatedEnemies){
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
            // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }
        return defeatedEnemies;
    }
}