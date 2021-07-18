package unsw.loopmania;



public class VampireCritical implements VampireAttackStrategy{

    private int critTurns = 3;

    /**
     * Deals damage to Hero
     * @param h
     * @param v
     */
    @Override
    public void attack(Hero h, Vampire v) {
        int randomInt = LoopManiaWorld.getRandNum() % 10;
        h.takeDamage(v.getAttackDamage() + randomInt + 1);
        critTurns--;
        if (critTurns == 0) {
            v.setStrategy(new VampireNormal());
        }
    }
}
