package unsw.loopmania;

public class CampfireState implements BonusDamageStrategy{
    public CampfireState() {
        
    }

    @Override
    public double ApplyBonusDamge(double damage) {    
        return damage*2;
    }
    
}
