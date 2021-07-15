package unsw.loopmania;

public class CampfireState implements BonusDamageStrategy{
    public CampfireState() {
        
    }

    @Override
    public int ApplyBonusDamge(int damage) {    
        return damage*2;
    }
    
}
