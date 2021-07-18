package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;


/**
 * Mediator pattern that runs the battle. BattleRunner retains no information
 * about the world state or fight and is only called when a battle occurs.
 * This class is used to seperate the battle running logic from other classes
 * to keep them simpler and more relevant.
 * @author Group FRIDGE
 */
public class BattleRunner {
    private Character character;
    ArrayList<Enemy> defeatedEnemies;
    List<Enemy> enemies;
    List<AlliedSoldier> allies;
    List<TowerBuilding> towers;

    /**
     * Empty constructor for BattleRunner class because it doesn't retain
     * any knowledge about the world state.
     */
    public BattleRunner() {
        
    }

    /**
     * Setter to set the character once the fight happens
     * @param c Character: The character
     */
    public void setCharacter(Character c) {
        this.character = c;
    }

    /**
     * Setter to set the list of enemies involved in the fight
     * @param enemies List<Enemy>: The enemies involved in the fight
     */
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    
    /**
     * Checks whether a fight will happen given the current position of the character and the enemies.
     * If no fight will happpen, runBattle will be passed an empty list and end immediately.
     * BattleRunner does not distinguish between supporting and attacking enemies. Each tick, checkForFight
     * is called to determine whether a battle takes place
     * @param enemies List<Enemy>: List of all enemy locations
     * @param moveBuildings List<BuildingOnMove>: List of all activate-on-move buildings (to get all towers in range)
     * @return List<Enemy>: List of enemies that died in the fight (Empty if no battle takes place)
     */
    public List<Enemy> checkForFight(List<Enemy> enemies, List<BuildingOnMove> moveBuildings) {
        List<Enemy> attacking = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            int battleRadius = e.getBattleRadius();
            int supportRadius = e.getSupportRadius();
            double distance = Math.sqrt(Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2));
            System.out.println(String.format("%s distance is %f. Supp radius is %d", e.getType(), distance, supportRadius));
            if (distance <= battleRadius) {
                attacking.add(e);
            } else if (distance <= supportRadius) {
                attacking.add(e);
            }
        }
        System.out.println(String.format("Attacking enemies: %d", attacking.size()));
        List<TowerBuilding> towers = getInRangeTowers(moveBuildings);
        
        return runBattle(attacking, character.getAlliedSoldiers(), towers);
    }


    /**
     * Gets all towers in range of character that can participate in the battle
     * @param moveBuildings List<BuildingOnMove>: A list of all activate-on-move buildings on the map
     * @return List<TowerBuilding>: A list of all tower buildings participating in the battle
     */
    private List<TowerBuilding> getInRangeTowers(List<BuildingOnMove> moveBuildings) {
        List<TowerBuilding> towers = new ArrayList<TowerBuilding>();
        for (BuildingOnMove b : moveBuildings) {
            if (b.getType().equals("tower")) {
                TowerBuilding tower = (TowerBuilding) b;
                if (tower.isInRange(character)) {
                    towers.add(tower);
                }
            }
        }
        return towers;
    }



    /**
     * run the expected battles in the world, based on current world state
     * @return boolean if battle is won or lost
     */
    public ArrayList<Enemy> runBattle(List<Enemy> enemies, List<AlliedSoldier> allies, List<TowerBuilding> towers) {
        defeatedEnemies = new ArrayList<Enemy>();
        this.enemies = enemies;
        this.allies = allies;
        this.towers = towers;
        while (!character.isDead() && !enemies.isEmpty()) {
            runHeroAttacks();
            runEnemyAttacks();
            if (!enemies.isEmpty()) {
                System.out.println(String.format("%s has %d health", enemies.get(0).getType(), enemies.get(0).getHealth()));
                
            }
            else {
                System.out.println("All enemies are dead");
            }
            System.out.println(String.format("Character has %d health", character.getHealth()));
            checkTrancedEnemies();
        }
        killConvertedEnemies();
        return defeatedEnemies;
    }

    /**
     * Checks list of allies and converts any converted enemies (due to staff)
     * back into enemies if needed. When exiting a trance, converted enemies
     * retain the same amount of health that they had before they were converted.
     */
    public void checkTrancedEnemies() {
        for (int i = allies.size() - 1; i >= 0; i--) {
            AlliedSoldier a = allies.get(i);
            if (a instanceof convertedEnemy) {
                if (((convertedEnemy)a).canExitTrance()) {
                    Enemy original = ((convertedEnemy)a).getEnemy();
                    enemies.add(original);
                    killAlly(a);
                }
            }
        }
    }

    /**
     * Called after the battle has ended. Any enemies that end the battle
     * tranced/converted by the staff die.
     */
    private void killConvertedEnemies() {
        // for (AlliedSoldier a : allies) {
        for (int i = allies.size() - 1; i >= 0; i--) {
            AlliedSoldier a = allies.get(i);
            if (a instanceof convertedEnemy) {
                killAlly(a);
            }
        }
    }

    /**
     * Revives the player if they have The One Ring in their inventory. 
     * When the player is revived, the battle continues without stopping.
     * @param c Character: The character
     */
    private void revivecharacter(Character c) {
        c.setHealth(100);
    }

    /**
     * Converts an allied soldier (or converted enemy) that has been
     * attacked by a zombie with critical bite into a zombie, and then 
     * adds it to the enemies list. The new zombie won't attack this round.
     * @param a AlliedSoldier that has been bitten
     */
    public void convertAllyToZombie(AlliedSoldier a) {
        EnemyFactory f = new EnemyFactory();
        Enemy z =  f.create("zombie");
        enemies.add(z);
    }

    /**
     * Deals with the staff converting an enemy into an allied soldier (convertedEnemy).
     * The enemy is saved to retain its health once the trance ends
     * @param enemy Enemy: Enemy that has been tranced
     */
    public void convertEnemyToAlly(Enemy enemy) {
        enemies.remove(enemy);
        HeroFactory a = new HeroFactory();
        convertedEnemy c = (convertedEnemy) a.create(enemy);
        character.addAlliedSoldier((AlliedSoldier)c);
        
    }

    /**
     * Deals with enemy attacks. Every enemy attacks one after another.
     * If there are any allied Soldiers alive, they get attacked first 
     * (before the player). Enemies attack in order of oldest to youngest.
     */
    private void runEnemyAttacks() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            System.out.println(String.format("%d %d", enemies.size(), i));
            Enemy e = enemies.get(i);
            if (!allies.isEmpty()) {
                AlliedSoldier a = allies.get(0);
                if (e instanceof Zombie) {
                    e.attack(a, this);
                } else {
                    e.attack(a);
                }
                if (a.isDead()) {
                    killAlly(a);
                }
            } 
            else {
                e.attack(character, this);
                if (character.isDead() && character.hasRing()) {
                    revivecharacter(character);
                }
            }
        }
    }

    /**
     * Runs the hero attacks. Tower attacks first, then allied soldiers attack
     * and then the character attacks. Heros attack the oldest enemy first.
     */
    private void runHeroAttacks() {
        for (TowerBuilding t : towers) {
            if (!enemies.isEmpty()) {
                Enemy e = enemies.get(0);
                t.attack(e);
                postFight(e);
            }
        }
        for (AlliedSoldier a : allies) {
            if (!enemies.isEmpty()) {
                Enemy e = enemies.get(0);
                a.attack(e);
                postFight(e);
            }
        }
        if (!enemies.isEmpty()) {
            Enemy e = enemies.get(0);
            character.attack(e, this);
            postFight(e);
        }
    
    }

    /**
     * Deals with any potential enemy deaths. BattleRunnner saves a list of all
     * enemies that have died and updates it with every death.
     * @param e Enemy: enemy that has potentially died
     */
    private void postFight(Enemy e){
        if (e.isDead()){
            killEnemy(e);
            defeatedEnemies.add(e);
        }
    }

    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    private void killEnemy(Enemy enemy){
        enemy.destroy();
        enemies.remove(enemy);
        System.out.println("Enemy has died");
    }

    /**
     * kill an ally
     * @param enemy enemy to be killed
     */
    private void killAlly(AlliedSoldier ally) {
        allies.remove(ally);
    }
}