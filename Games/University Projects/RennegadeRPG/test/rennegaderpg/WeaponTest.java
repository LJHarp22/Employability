/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of WeaponTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class WeaponTest extends junit.framework.TestCase {
    
    Weapon weapon;
    
    /**
     * Default constructor for test class WeaponTest
     */
    public WeaponTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        weapon = new Bow("Bow", "A bow", 10);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        weapon = null;
    }

    /**
     * Test for the method getName, of class Weapon
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Bow";
        String result = weapon.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method getDescription, of class Weapon
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "A bow";
        String result = weapon.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * test for the method getAttack, of class Weapon
     */
    @Test
    public void testGetAttack() {
        System.out.println("getAttack");
        int expResult = 10;
        int result = weapon.getAttack();
        assertEquals(expResult, result);
    }
}
