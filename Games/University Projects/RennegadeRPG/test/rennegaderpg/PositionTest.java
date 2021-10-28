/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of PositionTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class PositionTest extends junit.framework.TestCase {
    
    Map map;
    Position position;
    
    /**
     * Default constructor for test class PositionTest
     */
    public PositionTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        map = new Map (5, 5);
        position = new Position(map, 1, 4);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */  
    @Override
    public void tearDown() {
        map = null;
        position = null;
    }

    /**
     * Test for the method setMap, of class Position
     */
    @Test
    public void testSetMap() {
        System.out.println("setMap");
        Map expResult = new Map (10, 10);
        position.setMap(expResult);
        assertEquals(position.getMap(), expResult);
    }

    /**
     * Test for the accessor method getMap, of class Position
     */
    @Test
    public void testGetMap() {
        System.out.println("getMap");
        Map expResult = map;
        Map result = position.getMap();
        assertEquals(expResult, result);
    }

    /**
     * Test for the accessor method getRow, of class Position
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        int expResult = 1;
        int result = position.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test for the accessor method getCol, of class Position
     */
    @Test
    public void testGetCol() {
        System.out.println("getCol");
        int expResult = 4;
        int result = position.getCol();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method getNewPosition, of class Position
     *              Valid new Position
     */
    @Test
    public void testGetNewPositionValid() {
        System.out.println("getNewPosition");
        MoveDirection direction = MoveDirection.NORTH;
        Position result = position.getNewPosition(direction);
        assertFalse(position == result);
        assertFalse(position == null);
    }
    
    /**
     * Test for the method getNewPosition, of class Position
     *              Invalid new Position, returns null
     */
    @Test
    public void testGetNewPositionInvalid() {
        System.out.println("getNewPosition");
        MoveDirection direction = null;
        Position result = position.getNewPosition(direction);
        assertEquals(result, null);
    }    
}
