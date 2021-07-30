package unsw.loopmania.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.ConfusedRareItem;
import unsw.loopmania.InvinciblePotion;
import unsw.loopmania.Nuke;
import unsw.loopmania.Potion;
import unsw.loopmania.Protection;
import unsw.loopmania.Shield;
import unsw.loopmania.TheOneRing;
import unsw.loopmania.TreeStump;
import unsw.loopmania.Weapon;
import unsw.loopmania.Entities.StaticEntity;

public abstract class Item extends StaticEntity{

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public Item() {
        super();
    }
    public abstract int getPrice();
    public abstract int getSellPrice();
    public abstract int getReplaceCost();

    public boolean isWeapon() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof Weapon || additional instanceof Weapon;
        }
        else {
            return this instanceof Weapon;
        }
    }
    public boolean isProtection() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof Protection || additional instanceof Protection;
        }
        else {
            return this instanceof Protection;
        }
    }
    public boolean isShield() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof Shield || additional instanceof Shield;
        }
        else {
            return this instanceof Shield;
        }
    }
    public boolean isRing() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof TheOneRing || additional instanceof TheOneRing;
        }
        else {
            return this instanceof TheOneRing;
        }
    }
    public boolean isNuke() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof Nuke || additional instanceof Nuke;
        }
        else {
            return this instanceof Nuke;
        }
    }
    public boolean isTreeStump() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof TreeStump || additional instanceof TreeStump;
        }
        else {
            return this instanceof TreeStump;
        }
    }
    public boolean isInvinciblePotion() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof InvinciblePotion || additional instanceof InvinciblePotion;
        }
        else {
            return this instanceof InvinciblePotion;
        }
    }
    public boolean isPotion() {
        if (this instanceof ConfusedRareItem) {
            Item additional = ((ConfusedRareItem)this).getAdditional();
            return this instanceof Potion || additional instanceof Potion;
        }
        else {
            return this instanceof Potion;
        }
    }


}
