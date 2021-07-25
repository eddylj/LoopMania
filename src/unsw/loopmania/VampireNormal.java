package unsw.loopmania;

import java.util.Objects;

public class VampireNormal implements VampireAttackStrategy{

    /**
     * Deals damage to Hero
     * @param h
     * @param v
     */
    @Override
    public void attack(Hero h, Vampire v) {
        int randomInt = LoopManiaWorld.getRandNum();
        if (h instanceof Character) {
            Character c = (Character) h;
            if ((randomInt < 20 && Objects.isNull(c.getShield())) || randomInt < 8) {
                critAttack(h, v);
            } else {
                h.takeDamage(v.getAttackDamage(), v);
            }
        } else {
            if (randomInt < 20) {
                critAttack(h, v);
            } else {
                h.takeDamage(v.getAttackDamage(), v);
            }
        }
    }
    /**
     * Deals critical damage to Hero
     * @param h
     * @param v
     */
    private void critAttack(Hero h, Vampire v) {
        int randomInt = LoopManiaWorld.getRandNum() % 10;
        h.takeDamage(v.getAttackDamage()*2 + randomInt + 1, v);
        v.setStrategy(new VampireCritical());
    }
}
