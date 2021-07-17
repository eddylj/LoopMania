package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BattleRunner {
    private Character character;
    ArrayList<Enemy> defeatedEnemies;
    List<Enemy> enemies;
    List<AlliedSoldier> allies;
    List<TowerBuilding> towers;

    public BattleRunner() {
        
    }

    public void setCharacter(Character c) {
        this.character = c;
    }


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
        this.enemies = enemies;
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

    private void killConvertedEnemies() {
        // for (AlliedSoldier a : allies) {
        for (int i = allies.size() - 1; i >= 0; i--) {
            AlliedSoldier a = allies.get(i);
            if (a instanceof convertedEnemy) {
                killAlly(a);
            }
        }
    }

    private void revivecharacter(Character c) {
        c.setHealth(100);
    }

    public void convertAllyToZombie(AlliedSoldier a) {
        EnemyFactory f = new EnemyFactory();
        Enemy z =  f.create("zombie");
        enemies.add(z);
        // Collections.sort(enemies, new EnemyComparator());
    }

    public void convertEnemyToAlly(Enemy enemy) {
        List<AlliedSoldier> allies = getAllies();
        enemies.remove(enemy);
        HeroFactory a = new HeroFactory();
        convertedEnemy c = (convertedEnemy) a.create(enemy);
        character.addAlliedSoldier((AlliedSoldier)c);
        
    }

    private List<AlliedSoldier> getAllies() {
        return character.getAlliedSoldiers();
    }


    private void runEnemyAttacks() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
        // for (int i = 0; i < enemies.size(); i++) {
            System.out.println(String.format("%d %d", enemies.size(), i));
            Enemy e = enemies.get(i);
        // for (Enemy e : enemies) {
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
                if (character.isDead() && character.hasring()) {
                    revivecharacter(character);
                }
            }
        }
    }
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



    // class EnemyComparator implements Comparator<Enemy> {
  
    //     // override the compare() method
    //     public int compare(Enemy e2, Enemy e1) {
    //         String t1 = e1.getType();
    //         String t2 = e2.getType();
    //         if (t1.equals(t2)) {
    //             return 0;
    //         }
    //         else if (t1.equals("Vampire")) {
    //             return 1;
    //         }
    //         else if (t2.equals("Vampire")) {
    //             return -1;
    //         }
    //         else if (t1.equals("Zombie")) {
    //             return 1;
    //         }
    //         else if (t2.equals("Zombie")) {
    //             return -1;
    //         } else {
    //             return 0;
    //         }
    //     }
    // }



}