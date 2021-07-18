package unsw.loopmania;

public class CharacterStats {
    private int sword;
    private int stake;
    private int staff;
    private int shield;
    private int helmet;
    private int armour;

    public CharacterStats() {
        sword = 1;
        stake = 1;
        staff = 1;
        shield = 1;
        helmet = 1;
        armour = 1;
    }

    public int getHighestLevel(String type) {
        if (type.equals("sword")) {
            return sword;
        }
        else if (type.equals("stake")) {
            return stake;
        }
        else if (type.equals("staff")) {
            return staff;
        }
        else if (type.equals("shield")) {
            return shield;
        }
        else if (type.equals("helmet")) {
            return helmet;
        }
        else if (type.equals("armour")) {
            return armour;
        }
        else {
            return 0;
        }
    }

    public void updateHighestLevel(Item item) {
        String type = ((StaticEntity)item).getType();
        if (type.equals("sword") &&  sword <= ((Weapon)item).getLevel()) {
            sword = ((Weapon)item).getLevel();
        }
        else if (type.equals("stake") && stake <= ((Weapon)item).getLevel()) {
            stake = ((Weapon)item).getLevel();
        }
        else if (type.equals("staff") && staff <= ((Weapon)item).getLevel()) {
            staff = ((Weapon)item).getLevel();
        }
        else if (type.equals("shield") && shield <= ((Protection)item).getLevel()) {
            shield = ((Protection)item).getLevel();
        }
        else if (type.equals("helmet") && helmet <= ((Protection)item).getLevel()) {
            helmet = ((Protection)item).getLevel();
        }
        else if (type.equals("armour") && armour <= ((Protection)item).getLevel()) {
            armour = ((Protection)item).getLevel();
        }
    }
}
