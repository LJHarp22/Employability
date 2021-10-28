/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of PaladinTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class PaladinTest extends junit.framework.TestCase {
    
    Paladin paladin;
    Bow bow;
    
    /**
     * Default constructor for test class PaladinTest
     */
    public PaladinTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        bow = new Bow("Longbow", "A bow", 10);
        paladin = new Paladin("Ralph", 1, 50, new Position(null, 0, 0), bow);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */    
    @Override
    public void tearDown() {
        bow = null;
        paladin = null;
    }
    
    /**
     * Test of the setWeapon mutator method, of class Paladin
     */
    @Test
    public void testSetWeapon() {
        System.out.println("setWeapon");
        assertEquals(paladin.getWeapon(), bow);
        // Set new weapon
        Bow instance = new Bow("Ralph's bow", "My bow", 22);
        paladin.setWeapon(instance);
        assertEquals(paladin.getWeapon(), instance);
    }

    /**
     * Test of the getWeapon accessor method, of class Paladin
     */
    @Test
    public void testGetWeapon() {
        System.out.println("getWeapon");
        Bow expResult = bow;
        Bow result = paladin.getWeapon();
        assertEquals(expResult, result);
    }

    /**
     * Test of the levelUp method, of class Paladin
     */
    @Test
    public void testLevelUP() {
        System.out.println("levelUP");
        int atk = paladin.getAttack();
        int def = paladin.getDefence();
        int mag = paladin.getMagic();
        // Check paladin stats after level up.
        paladin.levelUP();
        assertEquals(paladin.getAttack(), atk + 4);
        assertEquals(paladin.getDefence(), def + 2);
        assertEquals(paladin.getMagic(), mag + 2);
    }
    
}
