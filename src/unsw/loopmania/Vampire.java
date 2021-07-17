package unsw.loopmania;

import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

public class Vampire extends Enemy {



    private VampireAttackStrategy Strategy;

    public Vampire (PathPosition position) {
        super(position, 2, 3, 18, 500, 150);
        super.setType("vampire");
        Strategy = new VampireNormal();
            
    
    }

    public Vampire() {
        super(2, 3, 18, 500, 150);
        super.setType("vampire");
        Strategy = new VampireNormal();

    }

    public void setStrategy(VampireAttackStrategy Strategy) {
        this.Strategy = Strategy;
    }
    @Override
    public void attack (Hero h) {
        Strategy.attack(h, this);
    }

    public void move(CampfireBuilding campfire) {
        Pair<Integer, Integer> forward = position.getClockwisePosition();
        Pair<Integer, Integer> backward = position.getAntiClockwisePosition();
        double forwardDistance = Math.sqrt(Math.pow(forward.getValue0() - getX(), 2) + Math.pow(forward.getValue1() - getY(), 2));
        double backwardDistance = Math.sqrt(Math.pow(backward.getValue0() - getX(), 2) + Math.pow(backward.getValue1() - getY(), 2));
        if (forwardDistance > backwardDistance) {
            moveUpPath();
        }
        else {
            moveDownPath();
        }
    }
    
}
