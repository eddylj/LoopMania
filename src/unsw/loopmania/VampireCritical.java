package unsw.loopmania;

import java.util.Random;


public class VampireCritical implements VampireAttackStrategy{

    private int critTurns = 3;

    public void attack(Hero h, Vampire v) {
        Random r = new Random();
        int randomInt = r.nextInt(10);
        h.takeDamage(v.getAttackDamage() + randomInt);
        critTurns--;
        if (critTurns == 0) {
            v.setStrategy(new VampireNormal());
        }
    }
}
