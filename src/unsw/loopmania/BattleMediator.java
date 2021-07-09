package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

public class BattleMediator {
    private Character character;

    public BattleMediator(Character c) {
        this.character = c;
    }
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
    private void killEnemy(Enemy enemy){
        enemy.destroy();
        enemies.remove(enemy);
    }
}