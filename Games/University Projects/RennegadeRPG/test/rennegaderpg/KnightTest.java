/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rennegaderpg;

import org.junit.*;

/**
 * The test class KnightTest
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class KnightTest extends junit.framework.TestCase {
    
    Knight knight;
    Melee melee;
    
    /**
     * Default constructor for test class KnightTest
     */
    public KnightTest() {
    }
    
    /**
     * Sets up the test fixture
     * 
     * Called before every test case method
     */
    @Override
    public void setUp() {
        melee = new Melee("Melee", "Dagger", 5, 5);
        knight = new Knight("Knight", 1, 50, new Position(null, 0, 0), melee);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    public void tearDown() {
        knight = null;
        melee = null;
    }

    /**
     * Test of the getWeapon accessor method, of class Knight
     */
    @Test
    public void testGetWeapon() {
        System.out.println("getWeapon");
        Melee expResult = melee;
        Melee result = knight.getWeapon();
        assertEquals(expResult, result);
    }

    /**
     * Test of the setWeapon mutator method, of class knight
     */
    @Test
    public void testSetWeapon() {
        System.out.println("setWeapon");
        Melee wep = new Melee("Mace", "A club", 10, 10);
        knight.setWeapon(wep);
        assertEquals(knight.getWeapon(), wep);
    }

    /**
     * Test of the levelUp method, of class Knight
     */
    @Test
    public void testLevelUP() {
        System.out.println("levelUP");
        int atk = knight.getAttack();
        int def = knight.getDefence();
        int mag = knight.getMagic();
        knight.levelUP();
        // Check Knight stats after level up.
        assertEquals(atk + 3, knight.getAttack());
        assertEquals(def + 3, knight.getDefence());
        assertEquals(mag + 2, knight.getMagic());
    }
    
}
