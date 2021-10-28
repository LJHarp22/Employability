/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class GameTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class GameTest extends junit.framework.TestCase {
    
    Game game;
    
    /**
     * Default constructor for test class GameTest
     */
    public GameTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        game = new Game("test");
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        game = null;
    }

    /**
     * Test of getCurrentMap method, of class Game
     */
    @Test
    public void testGetCurrentMap() {
        System.out.println("getCurrentMap");
        Map result = game.getCurrentMap();
        assertFalse(result == null);
    }

    /**
     * Test of getAccount method, of class Game
     */
    @Test
    public void testGetAccount() {
        System.out.println("getAccount");
        String expResult = "test";
        String result = game.getAccount();
        assertEquals(expResult, result);
    }

    /**
     * Player accessor method getPlayer, of class Game
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        Player result = game.getPlayer();
        assertFalse(result == null);
    }

    /**
     * GridSquare accessor method getCurrentGridSquare, of class game
     */
    @Test
    public void testGetCurrentGridSquare() {
        System.out.println("getCurrentGridSquare");
        GridSquare expResult = game.getCurrentMap().getGridSquare(game.getPlayer().getPosition());
        GridSquare result = game.getCurrentGridSquare();
        assertEquals(expResult, result);
    }

    /**
     * Test the movePlayer method, of class Game
     */
    @Test
    public void testMovePlayer() {
        System.out.println("movePlayer");
        MoveDirection move = MoveDirection.NORTH;
        Position prev = game.getPlayer().getPosition();
        game.movePlayer(move);
        assertTrue(prev != game.getPlayer().getPosition());
    }

    /**
     * Test the getWeapon method, of class Game
     */
    @Test
    public void testGetWeapon() {
        System.out.println("getWeapon");
        int weapon = 0;
        Melee result = (Melee) game.getWeapon(weapon);
        // Compare weapon
        assertEquals(result.getName(), "Dagger");
        assertEquals(result.getDescription(), "A sharp dagger");
        assertEquals(result.getAttack(), 8);
        assertEquals(result.getDefence(), 5);
    }
    
}
