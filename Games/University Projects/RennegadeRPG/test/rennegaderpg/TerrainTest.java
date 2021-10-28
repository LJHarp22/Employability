/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of TerrainTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class TerrainTest extends junit.framework.TestCase {
    
    Terrain terrain;
    
    /**
     * Default constructor for test class TerrainTest
     */
    public TerrainTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        terrain = Terrain.SNOW;
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        terrain = null;
    }

    /**
     * Test for the accessor method getStringRepresentation, of class Terrain
     */
    @Test
    public void testGetStringRepresentation() {
        System.out.println("getStringRepresentation");
        String expResult = "^";
        String result = terrain.getStringRepresentation();
        assertEquals(expResult, result);
    }

    /**
     * Test for the accessor method getDescription, of class Terrain
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "Cold Snow";
        String result = terrain.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method, getTerrainFromStringRep, of class Terrain
     */
    @Test
    public void testGetTerrainFromStringRep() {
        System.out.println("getTerrainFromStringRep");
        String rep = "^";
        Terrain expResult = Terrain.SNOW;
        Terrain result = Terrain.getTerrainFromStringRep(rep);
        assertEquals(expResult, result);
    }
    
}
