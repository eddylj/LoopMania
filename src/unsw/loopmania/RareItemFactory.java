package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

public class RareItemFactory {
    private List<String> rareItems;
    private RareItemFactoryStrategy createStrategy;

    public RareItemFactory(List<String> rareItems) {
        this.rareItems = rareItems;
        createStrategy = new StandardRareItemFactory(this);
    }

    public void setConfusing() {
        createStrategy = new ConfusingRareItemFactory(this);
    }

    public Item create(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        return createStrategy.create(x, y, type);
    }

    public Item createConfusing(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        if (type.equals("theonering")) {
            return createConfusingTheOneRing(x, y);
        }
        else if (type.equals("anduril")) {
            return createConfusingAnduril(x, y);
        }
        else if (type.equals("treestump")) {
            return createConfusingTreeStump(x, y);
        }
        else {
            return null;
        }
    }
    
    public Item createStandard(SimpleIntegerProperty x, SimpleIntegerProperty y, String type) {
        if (type.equals("theonering")) {
            return createTheOneRing(x, y);
        }
        else if (type.equals("anduril")) {
            return createAnduril(x, y);
        }
        else if (type.equals("treestump")) {
            return createTreeStump(x, y);
        }
        else {
            return null;
        }
    }

    private Item create(String type) {
        if (type.equals("theonering")) {
            return createTheOneRing();
        }
        else if (type.equals("anduril")) {
            return createAnduril();
        }
        else if (type.equals("treestump")) {
            return createTreeStump();
        }
        else {
            return null;
        }
    }

    private Item createTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new TheOneRing(x, y);
    }
    private Item createAnduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new Anduril(x, y, 1);
    }
    private Item createTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        return new TreeStump(x, y, 1);
    }
    private Item createTheOneRing() {
        return new TheOneRing();
    }
    private Item createAnduril() {
        return new Anduril(1);
    }
    private Item createTreeStump() {
        return new TreeStump(1);
    }
    private Item createConfusingTheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item additional = create(generateRandomRareItem("theonering"));
        return new ConfusingTheOneRing(x, y, additional);
    }
    private Item createConfusingAnduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item additional = create(generateRandomRareItem("anduril"));
        return new ConfusingAnduril(x, y, 1, additional);
    }
    private Item createConfusingTreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item additional = create(generateRandomRareItem("treestump"));
        return new ConfusingTreeStump(x, y, 1, additional);
    }
    private String generateRandomRareItem(String original) {
        List<String> copy = new ArrayList<String>();
        copy.addAll(rareItems);
        copy.remove(original);
        return copy.get(LoopManiaWorld.getRandNum() % copy.size());
    }
}