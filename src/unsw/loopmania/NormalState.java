package unsw.loopmania;

public class NormalState implements BonusDamageStrategy{
    public NormalState() {
        
    }
    /**
     * @return damage
     */
    @Override
    public double ApplyBonusDamge(double damage) {
        return damage;
    }
    
}
