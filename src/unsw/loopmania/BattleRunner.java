package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BattleRunner {
    private Character character;
    private List<Enemy> enemies;
    private ArrayList<Enemy> defeatedEnemies;
    private List<AlliedSoldier> allies;
    private List<TowerBuilding> towers;
    private Random rand;

    public BattleRunner(Character c, List<Enemy> enemies, List<AlliedSoldier> allies, List<TowerBuilding> towers) {
        this.character = c;
        this.enemies = enemies;
        this.allies = allies;
        this.towers = towers;
    }




    /**
     * run the expected battles in the world, based on current world state
     * @return boolean if battle is won or lost
     */
    public ArrayList<Enemy> runBattle() {
        while (!character.isDead() && !enemies.isEmpty()) {
            runHeroAttacks();
            runEnemyAttacks();
        }
        return defeatedEnemies;
    }

    public int getRandNum(int range) {
        return rand.nextInt(range);
    }

    private void revivecharacter(Character c) {
        c.setHealth(100);
    }

    public void convertAllyZombie(AlliedSoldier a) {
        EnemyFactory f = new EnemyFactory();
        Enemy z =  f.create("zombie");
        enemies.add(z);
        Collections.sort(enemies, new EnemyComparator());
    }

    public void convertEnemyAlly(Enemy enemy,int cycle) {
        enemies.remove(enemy);
        HeroFactory a = new HeroFactory();
        convertedEnemy c = (convertedEnemy) a.create(enemy, cycle);
        allies.add(0, ((AlliedSoldier)c));
        
    }
    public void runEnemyAttacks() {
        for (Enemy e : enemies) {
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
    public void runHeroAttacks() {
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
    }

    /**
     * kill an ally
     * @param enemy enemy to be killed
     */
    public void killAlly(AlliedSoldier ally) {
        allies.remove(ally);
    }



    class EnemyComparator implements Comparator<Enemy> {
  
        // override the compare() method
        public int compare(Enemy e1, Enemy e2) {
            String t1 = e1.getType();
            String t2 = e2.getType();
            if (t1.equals(t2)) {
                return 0;
            }
            else if (t1.equals("Vampire"))
                return 1;
            else if (t2.equals("Vampire"))
                return -1;
            else if (t1.equals("Zombie"))
                return 1;
            else if (t1.equals("Zombie"))
                return -1;
            return 0;
        }
    }



}