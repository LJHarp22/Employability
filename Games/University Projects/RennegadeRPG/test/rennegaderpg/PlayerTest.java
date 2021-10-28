/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class of PlayerTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class PlayerTest extends junit.framework.TestCase {
    
    Map map;
    Position position;
    Player player;
    
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        map = new Map(10, 10);
        position = new Position(map , 1, 1);
        player = new Knight("Ralph", 1, 50, position, new Melee("Mace", "Mace", 5, 5));
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
        player = null;
    }

    /**
     * Test for the accessor method getName, of class Player
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Ralph";
        String result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test for the mutator method setName, of class Player
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Roland";
        player.setName(name);
        assertEquals(player.getName(), name);
    }

    /**
     * Test for the accessor method getLevel, of class Player
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        int expResult = 1;
        int result = player.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test for the mutator method setLevel, of class Player
     */
    @Test
    public void testSetLevel() {
        System.out.println("setLevel");
        int value = 10;
        player.setLevel(value);
        assertEquals(player.getLevel(), value);
    }

    /**
     * Test for the method getExperience, of class Player
     */
    @Test
    public void testGetExperience() {
        System.out.println("getExperience");
        int expResult = 50;
        int result = player.getExperience();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method setExperience, of class Player
     */
    @Test
    public void testSetExperience() {
        System.out.println("setExperience");
        int value = 90;
        player.setExperience(value);
        assertEquals(player.getExperience(), value);
    }

    /**
     * Test for the method increaseExperience, of class Player
     */
    @Test
    public void testIncreaseExperience() {
        System.out.println("increaseExperience");
        player.increaseExperience(20);
        assertEquals(player.getExperience(), 70);
    }

    /**
     * Test for the mutator method setDefence, of class Player
     */
    @Test
    public void testSetDefence() {
        System.out.println("setDefence");
        int value = 2;
        player.setDefence(value);
        assertEquals(player.getDefence(), 2);
    }

    /**
     * Test for the accessor method getDefence, of class Player
     */
    @Test
    public void testGetDefence() {
        System.out.println("getDefence");
        int expResult = 5;
        player.setDefence(expResult);
        int result = player.getDefence();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method increaseDefence, of class Player
     */
    @Test
    public void testIncreaseDefence() {
        System.out.println("increaseDefence");
        int value = 2;
        int previous = player.getDefence();
        player.increaseDefence(value);
        // Compare previous vs the new defence value
        assertEquals(player.getDefence(), previous + value);
    }

    /**
     * Test for the method decreaseDefence, of class Player
     */
    @Test
    public void testDecreaseDefence() {
        System.out.println("decreaseDefence");
        int value = 2;
        int previous = player.getDefence();
        player.decreaseDefence(value);
        // Compare previous vs the new defence value
        assertEquals(player.getDefence(), previous - value);
    }

    /**
     * Test for the method setAttack, of class Player
     */
    @Test
    public void testSetAttack() {
        System.out.println("setAttack");
        int value = 5;
        player.setAttack(value);
        assertEquals(player.getAttack(), value);
    }

    /**
     * Test for the method getAttack, of class Player
     */
    @Test
    public void testGetAttack() {
        System.out.println("getAttack");
        int expResult = 5;
        player.setAttack(5);
        int result = player.getAttack();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method increaseAttack, of class Player
     */
    @Test
    public void testIncreaseAttack() {
        System.out.println("increaseAttack");
        int value = 5;
        int previous = player.getAttack();
        player.increaseAttack(value);
        assertEquals(player.getAttack(), previous + value);
    }

    /**
     * Test for the method decreaseAttack, of class Player
     */
    @Test
    public void testDecreaseAttack() {
        System.out.println("decreaseAttack");
        int value = 2;
        int previous = player.getAttack();
        player.decreaseAttack(value);
        assertEquals(player.getAttack(), previous - value);
    }

    /**
     * Test for the method setMagic, of class Player
     */
    @Test
    public void testSetMagic() {
        System.out.println("setMagic");
        int value = 200;
        player.setMagic(value);
        assertEquals(player.getMagic(), value);
    }

    /**
     * Test for the method getMagic, of class Player
     */
    @Test
    public void testGetMagic() {
        System.out.println("getMagic");
        player.setMagic(10);
        int expResult = 10;
        int result = player.getMagic();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method increaseMagic, of class Player
     */
    @Test
    public void testIncreaseMagic() {
        System.out.println("increaseMagic");
        int previous = player.getMagic();
        int value = 2;
        player.increaseMagic(value);
        assertEquals(player.getMagic(), previous + value);
    }

    /**
     * Test for the method decreaseMagic, of class Player
     */
    @Test
    public void testDecreaseMagic() {
        System.out.println("decreaseMagic");
        int value = 2;
        int previous = player.getMagic();
        player.decreaseMagic(value);
        assertEquals(player.getMagic(), previous - value);
    }

    /**
     * Test for the method getHealth, of class Player
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        int expResult = 100;
        int result = player.getHealth();
        assertEquals(expResult, result);    }

    /**
     * Test for the method reduceHealth, of class Player
     */
    @Test
    public void testReduceHealth() {
        System.out.println("reduceHealth");
        int value = 10;
        player.reduceHealth(value);
        assertEquals(player.getHealth(), 100 - value);
    }

    /**
     * Test for the method resetHealth, of class Player
     */
    @Test
    public void testResetHealth() {
        System.out.println("resetHealth");
        player.reduceHealth(80);
        assertEquals(player.getHealth(), 20);
        player.resetHealth();
        int expValue = 100;
        assertEquals(player.getHealth(), expValue);
    }

    /**
     * Test for the method getMana, of class Player
     */
    @Test
    public void testGetMana() {
        System.out.println("getMana");
        int expResult = 100;
        int result = player.getMana();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method increaseMana, of class Player
     */
    @Test
    public void testIncreaseMana() {
        System.out.println("increaseMana");
        player.reduceMana(50);
        int expValue = 55;
        player.increaseMana();
        assertEquals(player.getMana(), expValue);
    }

    /**
     * Test for the method reduceMana, of class Player
     */
    @Test
    public void testReduceMana() {
        System.out.println("reduceMana");
        int value = 20;
        int expValue = 80;
        player.reduceMana(value);
        assertEquals(player.getMana(), expValue);
    }

    /**
     * Test for the method getPosition, of class Player
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Position expResult = position;
        Position result = player.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test for the method moveToPosition, of class Player
     */
    @Test
    public void testMoveToPosition() {
        System.out.println("moveToPosition");
        Position expResult = new Position(map, 2, 2);
        player.moveToPosition(expResult);
        assertEquals(player.getPosition(), expResult);
    }

    /**
     * Test for the method dead, of class Player
     */
    @Test
    public void testDead() {
        System.out.println("dead");
        player.dead();
        assertFalse(player.isAlive());
    }

    /**
     * Test for the method isAlive, of class Player
     */
    @Test
    public void testIsAlive() {
        System.out.println("isAlive");
        boolean expResult = true;
        boolean result = player.isAlive();
        assertEquals(expResult, result);
    }
}
