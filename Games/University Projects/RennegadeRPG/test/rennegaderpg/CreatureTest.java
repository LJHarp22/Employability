/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class CreatureTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class CreatureTest extends junit.framework.TestCase {
    
    Creature creep;
    Position position;
    
    /**
     * Default constructor for test class CreatureTest
     */
    public CreatureTest() {
    }
    
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    protected void setUp() {
        position = new Position(null, 0, 0);
        creep = new Creature("Snake", position, 5, 10, 10);
    }
    
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown() {
        creep = null;
        position = null;
    }

    /**
     * Test of getAttack method, of class Creature
     */
    @Test
    public void testGetAttack() {
        System.out.println("getAttack");
        int expResult = 5;
        int result = creep.getAttack();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExperience method, of class Creature
     */
    @Test
    public void testGetExperience() {
        System.out.println("getExperience");
        int expResult = 10;
        int result = creep.getExperience();
        assertEquals(expResult, result);
    }

    /**
     * Test health accessor, of class Creature
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        int expResult = 10;
        int result = creep.getHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxHealth method, of class Creature
     */
    @Test
    public void testGetMaxHealth() {
        System.out.println("getMaxHealth");
        int expResult = 10;
        int result = creep.getMaxHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of reduceHealth method, of class Creature
     */
    @Test
    public void testReduceHealth() {
        System.out.println("reduceHealth");
        int value = 2;
        int expResult = 8;
        creep.reduceHealth(value);
        assertEquals(creep.getHealth(), expResult);
    }

    /**
     * Test of resetHealth method, of class Creature
     */
    @Test
    public void testResetHealth() {
        System.out.println("resetHealth");
        creep.reduceHealth(5);
        assertEquals(creep.getHealth(), 5);
        creep.resetHealth();
        assertEquals(creep.getHealth(), creep.getMaxHealth());
    }

    /**
     * Accessor test of getTime, of class Creature
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        creep.setTime(10);
        int expResult = 10;
        int result = creep.getTime();
        assertEquals(expResult, result);
    }

    /**
     * Mutator test method of setTime, of class Creature
     */
    @Test
    public void testSetTime() {
        System.out.println("setTime");
        int value = 10;
        creep.setTime(value);
        int expResult = creep.getTime();
        assertEquals(expResult, value);
    }

    /**
     * Test if the creep isAlive, of class Creature
     */
    @Test
    public void testIsAlive() {
        System.out.println("isAlive");
        boolean expResult = true;
        boolean result = creep.isAlive();
        assertEquals(expResult, result);
        creep.dead();
        assertEquals(!expResult, creep.isAlive());
    }

    /**
     * Test of alive method, of class Creature
     */
    @Test
    public void testAlive() {
        System.out.println("alive");
        creep.alive();
        assertEquals(creep.isAlive(), true);
    }

    /**
     * Test of dead method, of class Creature
     */
    @Test
    public void testDead() {
        System.out.println("dead");
        creep.dead();
        assertEquals(creep.isAlive(), false);
    }
}
