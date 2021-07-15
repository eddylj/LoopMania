package unsw.loopmania;

public class CompositeLeaf implements Composite{
    private int amount;
    private Character character;
    private String type;

    public CompositeLeaf(int amount, Character character, String type) {
        this.amount = amount;
        this.character = character;
        this.type = type;
    }

    @Override
    public boolean getValue() {
        if (type.equals("gold")) {
            return character.getGold() >= amount;
        }
        else if (type.equals("experience")) {
            return character.getXP() >= amount;
        }
        else {
            return character.getCycles() >= amount;
        }
    }
    
}
