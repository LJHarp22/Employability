/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class GridSquareTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class GridSquareTest extends junit.framework.TestCase {
    
    GridSquare gs;
    Occupant creep;
    Player player;
    
    /**
     * Default constructor for test class GridSquareTest
     */
    public GridSquareTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    protected void setUp() {
        gs = new GridSquare(Terrain.SNOW);
        creep = new Creature("Snake", new Position(null, 0, 0), 5, 10, 10);
        player = new Sorcerer("Luke", 1, 0, new Position(null, 0, 0), new Staff("Staff", "Scary", 0, 0, 0));
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown() {
        gs = null;
        creep = null;
        player = null;
    }

    /**
     * Test of getTerrain method, of class GridSquare
     */
    @Test
    public void testGetTerrain() {
        System.out.println("getTerrain");
        Terrain expResult = Terrain.SNOW;
        Terrain result = gs.getTerrain();
        assertEquals(expResult, result);
    }

    /**
     * Terrain mutator method setTerrain, of class GridSquare
     */
    @Test
    public void testSetTerrain() {
        System.out.println("setTerrain");
        Terrain terrain = Terrain.WATER;
        gs.setTerrain(terrain);
        assertEquals(terrain, gs.getTerrain());
    }

    /**
     * Test of hasPlayer method, of class GridSquare
     */
    @Test
    public void testHasPlayer() {
        System.out.println("hasPlayer");
        assertEquals(gs.hasPlayer(), false);
        boolean expResult = true;
        // Set player
        gs.setPlayer(player);
        boolean result = gs.hasPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlayer method, of class GridSquare
     */
    @Test
    public void testSetPlayer() {
        System.out.println("setPlayer");
        assertEquals(gs.hasPlayer(), false);
        gs.setPlayer(player);
        assertEquals(gs.hasPlayer(), true);
    }

    /**
     * String accessor for terrain string representation, of class GridSquare
     */
    @Test
    public void testGetTerrainStringRepresentation() {
        System.out.println("getTerrainStringRepresentation");
        String expResult = "^";
        String result = gs.getTerrainStringRepresentation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOccupant method, of class GridSquare
     */
    @Test
    public void testSetOccupant() {
        System.out.println("setOccupant");
        assertEquals(gs.getOccupant(), null);
        gs.setOccupant(creep);
        assertEquals(gs.getOccupant(), creep);
    }

    /**
     * Test of getOccupant accessor method, of class GridSquare
     */
    @Test
    public void testGetOccupant() {
        System.out.println("getOccupant");
        assertEquals(gs.getOccupant(), null);
        Occupant result = creep;
        gs.setOccupant(result);
        assertEquals(gs.getOccupant(), result);
    }

    /**
     * Test of removeOccupant method, of class GridSquare
     */
    @Test
    public void testRemoveOccupant() {
        System.out.println("removeOccupant");
        gs.setOccupant(creep);
        assertEquals(gs.getOccupant(), creep);
        gs.removeOccupant();
        assertEquals(gs.getOccupant(), null);
    }

    /**
     * Test of setVisible mutator method, of class GridSquare
     */
    @Test
    public void testSetVisible() {
        System.out.println("setVisible");
        assertEquals(gs.isVisible(), false);
        gs.setVisible();
        assertEquals(gs.isVisible(), true);
    }

    /**
     * Test of isVisible method, of class GridSquare
     */
    @Test
    public void testIsVisible() {
        System.out.println("isVisible");
        boolean expResult = false;
        boolean result = gs.isVisible();
        assertEquals(expResult, result);
        // Set GridSquare visible
        expResult = true;
        gs.setVisible();
        assertEquals(gs.isVisible(), true);
    }
}
