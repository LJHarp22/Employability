/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of MeleeTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class MeleeTest extends junit.framework.TestCase {
    
    Melee melee;
    
    /**
     * Default constructor for test class MeleeTest
     */
    public MeleeTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        melee = new Melee ("Club", "A Club", 5, 10);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        melee = null;
    }

    /**
     * Test of the getDefence method, of class Melee
     */
    @Test
    public void testGetDefence() {
        System.out.println("getDefence");
        int expResult = 10;
        int result = melee.getDefence();
        assertEquals(expResult, result);
    }
    
}
