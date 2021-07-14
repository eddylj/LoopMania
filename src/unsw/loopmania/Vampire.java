package unsw.loopmania;

import java.util.Random;

public class Vampire extends Enemy {



    private VampireAttackStrategy Strategy;

    public Vampire (PathPosition position) {
        super(position, 2, 3, 18, 500, 150);
        super.setType("Vampire");
        Strategy = new VampireNormal();
            
    
    }

    public Vampire() {
        super(2, 3, 18, 500, 150);
        super.setType("Vampire");
        Strategy = new VampireNormal();

    }



    public void setStrategy(VampireAttackStrategy Strategy) {
        this.Strategy = Strategy;
    }
    @Override
    public void attack (Hero h, BattleRunner b) {
        Strategy.attack(h, this);
    }



    
}
