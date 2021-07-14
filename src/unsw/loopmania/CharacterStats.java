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
    }

    public int getHighest(String type) {
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

    public void updateHighest(Item item) {
        String type = item.getType();
        if (type.equals("sword")) {
            sword += 1;
        }
        else if (type.equals("stake")) {
            stake += 1;
        }
        else if (type.equals("staff")) {
            staff += 1;
        }
        else if (type.equals("shield")) {
            shield += 1;
        }
        else if (type.equals("helmet")) {
            helmet += 1;
        }
        else if (type.equals("armour")) {
            armour += 1;
        }
    }
}
