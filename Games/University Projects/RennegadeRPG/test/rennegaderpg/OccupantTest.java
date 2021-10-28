/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of OccupantTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class OccupantTest extends junit.framework.TestCase{
    
    Occupant occupant;
    Map map;
    Position pos;
    
    /**
     * Default constructor for test class OccupantTest
     */
    public OccupantTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        map = new Map(5, 5);
        pos = new Position(map, 0, 0);
        occupant = new NPC("Alf", pos);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        map = null;
        pos = null;
        occupant = null;
    }

    /**
     * Test of the accessor method getName, of class Occupant
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Alf";
        String result = occupant.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of the accessor method getPosition, of class Occupant
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Position result = occupant.getPosition();
        Position expResult = pos;
        assertEquals(result, expResult);
    }
}
