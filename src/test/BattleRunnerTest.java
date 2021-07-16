package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import unsw.loopmania.AlliedSoldier;
import unsw.loopmania.BattleRunner;
import unsw.loopmania.Character;
import unsw.loopmania.Enemy;
import unsw.loopmania.Slug;
import unsw.loopmania.TowerBuilding;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;

public class BattleRunnerTest {

    private Character character = new Character();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<AlliedSoldier> allies = new ArrayList<AlliedSoldier>();
    private List<TowerBuilding> towers = new ArrayList<TowerBuilding>();
    private BattleRunner b = new BattleRunner(character, enemies, allies, towers);

    @Test
    public void BasicbattleTestVictory(){
        Enemy e = new Slug();
        enemies.add(e);
        List<Enemy> defeatedEnemies = b.runBattle();
        assertEquals(1,defeatedEnemies.size());
        assert(character.getHealth() < 100);
    }

    @Test
    public void BasicbattleTestDefeat(){
        Enemy e = new Slug();
        Enemy e1 = new Slug();
        Enemy e2 = new Slug();
        Enemy e3 = new Slug();
        Enemy e4 = new Slug();
        enemies.add(e);
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        b.runBattle();
        assertTrue(character.isDead());
    }

    @Test
    public void BasicbattleTestWithAlly(){
        Enemy e = new Slug();
        Enemy e1 = new Slug();
        AlliedSoldier a = new AlliedSoldier(1);
        enemies.add(e);
        enemies.add(e1);
        b.runBattle();
        assert(allies.isEmpty());
        
    }

    @Test
    public void enemyToAllyTest(){
        Enemy e = new Slug();
        b.convertEnemyToAlly(e, 1);
        assert(allies.size() > 0);
        assertTrue(enemies.isEmpty());
    }

    @Test
    public void allyToEnemy(){
        AlliedSoldier a = new AlliedSoldier(1);
        b.convertAllyToZombie(a);
        assertTrue(allies.isEmpty());
        assert(enemies.size() > 0);
    }

}
