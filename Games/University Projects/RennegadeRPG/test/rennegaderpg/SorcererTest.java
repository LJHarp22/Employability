/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of SorcererTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class SorcererTest extends junit.framework.TestCase {
    
    Staff staff;
    Sorcerer player;
    
    /**
     * Default constructor for test class SorcererTest
     */
    public SorcererTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        staff = new Staff("Staff", "Scare", 5, 5, 5);
        player = new Sorcerer("Merlin", 1, 50, new Position(null, 0, 0), staff);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        staff = null;
        player = null;
    }

    /**
     * Test for the accessor method getWeapon, of class Sorcerer
     */
    @Test
    public void testGetWeapon() {
        System.out.println("getWeapon");
        Staff expResult = staff;
        Staff result = player.getWeapon();
        assertEquals(expResult, result);
    }

    /**
     * Test for the mutator method setWeapon, of class Sorcerer
     */
    @Test
    public void testSetWeapon() {
        System.out.println("setWeapon");
        Staff wep = new Staff("Crusader", "Crusade", 10, 10, 10);
        player.setWeapon(wep);
        assertEquals(player.getWeapon(), wep);
    }

    /**
     * Test of the levelUp method, of class Sorcerer
     */
    @Test
    public void testLevelUP() {
        System.out.println("levelUP");
        int atk = player.getAttack();
        int def = player.getDefence();
        int mag = player.getMagic();
        // Check Sorcerer stats after level up.
        player.levelUP();
        assertEquals(player.getAttack(), atk + 2);
        assertEquals(player.getDefence(), def + 2);
        assertEquals(player.getMagic(), mag + 4);
    }
    
}
