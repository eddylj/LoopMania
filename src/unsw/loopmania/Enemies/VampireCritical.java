package unsw.loopmania.Enemies;

import unsw.loopmania.Heroes.Hero;
import unsw.loopmania.LoopManiaWorld;

public class VampireCritical implements VampireAttackStrategy{

    private int critTurns = 3;

    /**
     * Deals damage to Hero
     * @param h
     * @param v
     */
    @Override
    public void attack(Hero hero, Vampire vampire) {
        int randomInt = LoopManiaWorld.getRandNum() % 10;
        hero.takeDamage(vampire.getAttackDamage() + randomInt + 1, vampire);
        critTurns--;
        if (critTurns == 0) {
            vampire.setStrategy(new VampireNormal());
        }
    }
}
