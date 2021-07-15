package unsw.loopmania;

public class VampireNormal implements VampireAttackStrategy{


    public void attack(Hero h, Vampire v) {
        int randomInt = LoopManiaWorld.getRandNum();
        if (randomInt < 20) {
            randomInt = LoopManiaWorld.getRandNum() % 10;
            h.takeDamage(v.getAttackDamage()*2 + randomInt + 1);
            v.setStrategy(new VampireCritical());
        } else {
            h.takeDamage(v.getAttackDamage());
        }
    }
}
