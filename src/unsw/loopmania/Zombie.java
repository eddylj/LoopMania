package unsw.loopmania;

import java.util.Random;

public class Zombie extends Enemy {
    
    public Zombie (PathPosition position) {
        super(position, 2, 2, 16, 250, 100);
        super.setType("Zombie");
    }

    public Zombie() {
        super(2, 2, 16, 250, 100);
        super.setType("Zombie");

    }

    @Override
    public void attack(AlliedSoldier a, BattleRunner b) {
        Random r = new Random();
        int randomInt = r.nextInt(100);
        if (randomInt >= 0 && randomInt <= 9) {
            a.setHealth(0);
            b.convertAllyZombie(a);
        } else {
            a.takeDamage(this.getAttackDamage());
        }
    }

    @Override
    public void attack(Character c) {
        c.takeDamage(this.getAttackDamage());
    }

    @Override
    public void attack(Hero h, BattleRunner b) {
        if (h instanceof AlliedSoldier) {
            Random r = new Random();
            int randomInt = r.nextInt(100);
            if (randomInt >= 0 && randomInt <= 9) {
                h.setHealth(0);
                b.convertAllyZombie((AlliedSoldier)h);
            } else {
                h.takeDamage(this.getAttackDamage());
            }

        } else {
            h.takeDamage(this.getAttackDamage());
        }
        
    }
}
