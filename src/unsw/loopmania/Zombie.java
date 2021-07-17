package unsw.loopmania;


public class Zombie extends Enemy {
    private boolean canMove;

    public Zombie (PathPosition position) {
        super(position, 2, 2, 18, 250, 100);
        super.setType("zombie");
        canMove = false;
    }

    public Zombie() {
        super(2, 2, 18, 250, 100);
        super.setType("zombie");
        canMove = false;
    }

    @Override
    public void attack(Hero h, BattleRunner b) {
        if (h instanceof AlliedSoldier) {
            int randomInt = LoopManiaWorld.getRandNum();
            if (randomInt >= 0 && randomInt <= 9) {
                h.setHealth(0);
                b.convertAllyToZombie((AlliedSoldier)h);
            } else {
                h.takeDamage(this.getAttackDamage());
            }

        } else {
            h.takeDamage(this.getAttackDamage());
        }
    }

    @Override
    public void move() {
        if (canMove) {
            super.move();
            canMove = false;
        }
        else {
            canMove = true;
        }
    }
}
