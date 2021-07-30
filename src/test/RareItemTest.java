package test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized.Parameter;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.ConfusedRareItem;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.RareItem;
import unsw.loopmania.RareItemFactory;

public class RareItemTest {
    RareItemFactory rF;
    SimpleIntegerProperty x;
    SimpleIntegerProperty y;
    public RareItemTest() {
        rF = new RareItemFactory(getRareItemList());
        x = new SimpleIntegerProperty(0);
        y = new SimpleIntegerProperty(0);
    }
    private List<String> getRareItemList() {
        List<String> rareItems = new ArrayList<String>();
        rareItems.add("theonering");
        rareItems.add("anduril");
        rareItems.add("treestump");
        rareItems.add("nuke");
        rareItems.add("invinciblepotion");
        return rareItems;
    }

    @ParameterizedTest
    @ValueSource(strings = {"anduril", "treestump", "nuke", "theonering", "invinciblepotion"})
    public void NotConfusingTest(String input) {
        LoopManiaWorld world = new LoopManiaWorld(445);
        Item item = rF.create(x, y, input);
        assertTrue(item instanceof RareItem);
        assertFalse(item instanceof ConfusedRareItem);
    } 
}