package unsw.loopmania;

import java.util.Objects;


public class VampireNormal implements VampireAttackStrategy{

    /**
     * Deals damage to Hero
     * @param h
     * @param v
     */
    @Override
    public void attack(Hero hero, Vampire vampire) {
        int randomInt = LoopManiaWorld.getRandNum();
        if (hero instanceof Character) {
            Character c = (Character) hero;
            if ((randomInt < 20 && Objects.isNull(c.getShield())) || randomInt < 8) {
                critAttack(hero, vampire);
            } else {
                hero.takeDamage(vampire.getAttackDamage(), vampire);
            }
        } else {
            if (randomInt < 20) {
                critAttack(hero, vampire);
            } else {
                hero.takeDamage(vampire.getAttackDamage(), vampire);
            }
        }
    }
    /**
     * Deals critical damage to Hero
     * @param h
     * @param v
     */
    private void critAttack(Hero hero, Vampire vampire) {
        int randomInt = LoopManiaWorld.getRandNum() % 10;
        hero.takeDamage(vampire.getAttackDamage()*2 + randomInt + 1, vampire);
        vampire.setStrategy(new VampireCritical());
    }
}
