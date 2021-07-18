
package test;

import unsw.loopmania.Character;
import unsw.loopmania.Shop;
import unsw.loopmania.Item;
import unsw.loopmania.Sword;
import unsw.loopmania.HealthPotion;
import unsw.loopmania.Weapon;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ShopTest {
    @Test
    public void buyWeaponTest() {
        Character c = new Character();
        Shop shop = new Shop(c);
        c.gainGold(1000);
        Item weapon = shop.buy("sword");
        Item sword = new Sword(2);
        assertEquals(((Weapon)weapon).getLevel(), ((Weapon)sword).getLevel());
        assertEquals(((Weapon)weapon).getType(), ((Weapon)sword).getType());
        assertEquals(c.getGold(), 650);        
    }

    @Test
    public void buyPotionTest() {
        Character c = new Character();
        Shop shop = new Shop(c);
        c.gainGold(1000);
        Item boughItem = shop.buy("healthpotion");
        Item potion = new HealthPotion();
        assertEquals(((HealthPotion)potion).getType(), ((HealthPotion)boughItem).getType());
        assertEquals(c.getGold(), 900);        
    }

    @Test
    public void checkBuyPriceTest() {
        Character c = new Character();
        Shop shop = new Shop(c);
        assertEquals(shop.getBuyPrice("sword"), 350);
        assertEquals(shop.getBuyPrice("staff"), 700);
        assertEquals(shop.getBuyPrice("stake"), 350);
        assertEquals(shop.getBuyPrice("helmet"), 400);
        assertEquals(shop.getBuyPrice("armour"), 400);
        assertEquals(shop.getBuyPrice("shield"), 400);
        assertEquals(shop.getBuyPrice("healthpotion"), 100);
    }
}