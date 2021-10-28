package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Food;
import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.Player;
import nz.ac.aut.prog2.ass3.gameModel.Position;
import nz.ac.aut.prog2.ass3.gameModel.Terrain;
import nz.ac.aut.prog2.ass3.gameModel.Tool;

/**
 * The test class PlayerTest.
 *
 * @author  Anne Philpott
 * @version July 2011
 */
public class PlayerTest extends junit.framework.TestCase
{
   Player player;
   Position playerPosition;
   Island island;
   Food sandwich;
     /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp()
    {
        island = new Island(5,5);
        playerPosition = new Position(island, 0,0);
        player = new Player(playerPosition,"Guybrush Threepwood",25.0, 15.0, 20.0);
        sandwich = new Food(playerPosition, "sandwich", "A tasty cheese sandwich", 1.0, 2.0, 1.5);        
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        island = null;
        playerPosition = null;
        player = null;       
    }
    
    // Tests for accessor methods
    public void testGetName()
    {
        assertEquals("Guybrush Threepwood", player.getName());
    }

    public void testGetPosition()
    {
        assertEquals(playerPosition, player.getPosition());
    }
    
    public void testGetMaximumStaminaLevel()
    {
        assertEquals(25.0, player.getMaximumStaminaLevel(), 0.01);
    }  
    
    public void testGetStaminaLevel()
    {
        assertEquals(25.0, player.getStaminaLevel(), 0.01);
    }


    //Tests for reduceStamina()
    public void testReduceStamina()
    {
        player.reduceStamina(7.0);
        assertEquals(18.0, player.getStaminaLevel(), 0.01);
    } 
    
    //Test to ensure that stamina level cannot be reduced below zero
    public void testReduceStaminaPastZero()
    {
        player.reduceStamina(26.0);
        assertEquals(0.0, player.getStaminaLevel(), 0.01);
    } 
    
    //Test to ensure that stamina level cannot be reduced by a negative number
    public void testReduceStaminaByNegative()
    {
        player.reduceStamina(-1.0);
        assertEquals(25.0, player.getStaminaLevel(), 0.01);
    }
    
    //Tests for increaseStamina()   
    public void testIncreaseStamina()
    {
        player.reduceStamina(7.0);
        player.increaseStamina(4.0);
        assertEquals(22.0, player.getStaminaLevel(), 0.01);
    } 
    
    //Test to ensure that stamina level cannot be increased past maximum
    public void testIncreaseStaminaPastZero()
    {
        player.increaseStamina(4.0);
        assertEquals(25.0, player.getStaminaLevel(), 0.01);
    } 
    
    //Test to ensure that stamina level cannot be increased by a negative number
    public void testIncreaseStaminaByNegative()
    {
        player.increaseStamina(-1.0);
        assertEquals(25.0, player.getStaminaLevel(), 0.01);
    }



    // Tests for hasStaminaToMove
    public void testhasStaminaToMoveEnoughStamina()
    {
        assertTrue(player.hasStaminaToMove(Terrain.SAND));
    }

    public void testhasStaminaToMoveNotEnoughStaminaForTerrain()
    {
        assertFalse(player.hasStaminaToMove(Terrain.MOUNTAIN));
    }


    public void testhasStaminaToMoveNotEnoughStaminaFullBackpack()
    {
       // reduce stamina so under twenty required for FOREST with full backpack
        player.reduceStamina(6.0);
        Tool fullLoad = new Tool(playerPosition, "full", "A full load.", 14.5, 1.5);
        player.collect(fullLoad);
        assertFalse(player.hasStaminaToMove(Terrain.FOREST));
    }

    public void testhasStaminaToMoveNotEnoughStaminaPartlyFullBackpack()
    {
       // reduce stamina so 80% backpack load takes over limit
        player.reduceStamina(8.0);
        Tool partLoad = new Tool(playerPosition, "part", "A part load.", 12.0, 1.5);
        player.collect(partLoad);
        assertFalse(player.hasStaminaToMove(Terrain.FOREST));
    }
  
    // Player alive tests
    public void testIsAlive()
    {
        assertTrue(player.isAlive());
    }
    
    public void testKill()
    {
        player.kill();
        assertFalse(player.isAlive());
    }    

    // Tests of collect method
    public void testCollectValidItemThatFits()
    {
        assertTrue(player.collect(sandwich));
        assertTrue(player.hasItem(sandwich));
        assertEquals(1.0, player.getCurrentBackpackWeight(),0.01);
        assertEquals(2.0, player.getCurrentBackpackSize(),0.01);
        Position newPos = sandwich.getPosition();
        assertFalse(newPos.isOnIsland());
    }
    
    public void testCollectDuplicateItem()
    {
        assertTrue(player.collect(sandwich));
        assertFalse(player.collect(sandwich));
    }

    public void testCollectItemMaxWeight()
    {
        Tool maxWeight = new Tool(playerPosition, "weight", "A very heavy weight", 15.0, 1.5);
        assertTrue(player.collect(maxWeight));
        assertTrue(player.hasItem(maxWeight));        
    }
    public void testCollectItemTooHeavy()
    {
        Tool hugeWeight = new Tool(playerPosition, "weight", "A very heavy weight", 16.0, 1.5);
        assertFalse(player.collect(hugeWeight));
        assertFalse(player.hasItem(hugeWeight));        
    }
    
    public void testCollectItemTooBig()
    {
        Tool largeTool = new Tool(playerPosition, "large", "A very large tool", 1.0, 20.5);
        assertFalse(player.collect(largeTool));
        assertFalse(player.hasItem(largeTool));        
    }     

        // Tests for drop
    public void testDropValidItem()
    {
        assertTrue(player.collect(sandwich));
        assertTrue(player.hasItem(sandwich));
        assertTrue(player.drop(sandwich));
        assertEquals(0.0, player.getCurrentBackpackWeight(),0.01);
        assertEquals(0.0, player.getCurrentBackpackSize(),0.01);
        assertFalse(player.hasItem(sandwich));
    }

    public void testDropInvalidItem()
    {
        assertFalse(player.drop(sandwich));
    }

    // Tests for moveToPosition
    public void testMoveToPositionEnoughStamina()
    {
        Position newPosition = new Position(island, 0,1);
        player.moveToPosition(newPosition, Terrain.SAND);
        assertEquals(newPosition, player.getPosition());
        assertEquals(20.0, player.getStaminaLevel(), 0.01);
    }

    public void testMoveToPositionNotEnoughStamina()
    {
        Position newPosition = new Position(island, 0,1);
        player.moveToPosition(newPosition, Terrain.MOUNTAIN);
        assertEquals(playerPosition, player.getPosition());
        assertEquals(25.0, player.getStaminaLevel(), 0.01);
    }
}