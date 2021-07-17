package unsw.loopmania;

import java.util.Objects;

public class VampireNormal implements VampireAttackStrategy{


    public void attack(Hero h, Vampire v) {
        int randomInt = LoopManiaWorld.getRandNum();
        if (h instanceof Character) {
            Character c = (Character) h;
            if ((randomInt < 20 && Objects.isNull(c.getShield())) || randomInt < 8) {
                critAttack(h, v);
            } else {
                h.takeDamage(v.getAttackDamage());
            }
        } else {
            if (randomInt < 20) {
                critAttack(h, v);
            } else {
                h.takeDamage(v.getAttackDamage());
            }
        }
    }

    private void critAttack(Hero h, Vampire v) {
        int randomInt = LoopManiaWorld.getRandNum() % 10;
        h.takeDamage(v.getAttackDamage()*2 + randomInt + 1);
        v.setStrategy(new VampireCritical());
    }
}
