/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;
/**
 * The test class of MapTest
 *
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class MapTest extends junit.framework.TestCase {
    
    Map map;
    
    /**
     * Default constructor for test class MapTest
     */
    public MapTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        map = new Map(5, 10);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        map = null;
    }

    /**
     * Test of the getRows method, of class Map
     */
    @Test
    public void testGetRows() {
        System.out.println("getRows");
        int expResult = 5;
        int result = map.getRows();
        assertEquals(expResult, result);
    }

    /**
     * Test of the getCols method, of class Map
     */
    @Test
    public void testGetCols() {
        System.out.println("getCols");
        int expResult = 10;
        int result = map.getCols();
        assertEquals(expResult, result);
    }

    /**
     * Test of the getGridSquare method, of class Map
     */
    @Test
    public void testGetGridSquare() {
        System.out.println("getGridSquare");
        GridSquare expResult = null;
        Position pos = new Position(map, 0, 0);
        GridSquare result = map.getGridSquare(pos);
        assertFalse(expResult == result);
    }

    /**
     * The test to update the player position on the map, of class Map
     */
    @Test
    public void testUpdatePlayerPosition() {
        System.out.println("updatePlayerPosition");
        Player player = new Sorcerer("Roland", 1, 50, new Position(map, 0, 0), new Staff("Staff", "Scared", 5, 5, 5));
        Position pos = new Position(map, 1, 0);
        // Update new position
        player.moveToPosition(pos);
        map.updatePlayerPosition(player);
        assertEquals(map.getGridSquare(pos).hasPlayer(), true);
        assertEquals(map.getGridSquare(new Position(map, 0, 0)).hasPlayer(), false);
    }
}
