package unsw.loopmania;
import java.util.Random;

public class VampireNormal implements VampireAttackStrategy{


    public void attack(Hero h, Vampire v) {
        Random r = new Random();
        int randomInt = r.nextInt(100);
        if (randomInt >= 0 && randomInt <= 20) {
            randomInt = r.nextInt(10);
            h.takeDamage(v.getAttackDamage()*2 + randomInt);
            v.setStrategy(new VampireCritical());
        } else {
            h.takeDamage(v.getAttackDamage());
        }
    }
}
