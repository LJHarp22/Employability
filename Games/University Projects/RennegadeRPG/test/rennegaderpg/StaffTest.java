/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of StaffTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class StaffTest extends junit.framework.TestCase{
    
    Staff staff;
    
    /**
     * Default constructor for test class StaffTest
     */
    public StaffTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        staff = new Staff("Staff", "Scared", 5, 10, 5);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        staff = null;
    }

    /**
     * Test for the method getMagic, of class Staff
     */
    @Test
    public void testGetMagic() {
        System.out.println("getMagic");
        int expResult = 5;
        int result = staff.getMagic();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method getDefence, of class Staff
     */
    @Test
    public void testGetDefence() {
        System.out.println("getDefence");
        int expResult = 10;
        int result = staff.getDefence();
        assertEquals(expResult, result);
    }
    
}
