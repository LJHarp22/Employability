package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Animal;
import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.Position;

/**
 * The test class IslandTest.
 *
 * @author  Anne Philpott
 * @version July 2011
 */
public class IslandTest extends junit.framework.TestCase
{
    Island testIsland;
    Position onIsland;
    Position notOnIsland;
    Animal snake; 
    /**
     * Default constructor for test class IslandTest
     */
    public IslandTest()
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
        testIsland = new Island(6,5);
        onIsland = new Position(testIsland, 1,0); 
        notOnIsland = Position.NotOnIsland;
        snake = new Animal(onIsland, "sss", "Slithering snake",  7.0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
      testIsland  = null;
    }
      
    /**
     * Test of GetNumRows method, of class Island.
     */
    public void testGetNumRows() {
        assertEquals(6, testIsland.getNumRows());
    }  
    
    /**
     * Test of GetNumColumns method, of class Island.
     */
    public void testGetNumColumns() {
        assertEquals(5, testIsland.getNumColumns());
    }
    
    /**
     * Tests for adding occupant to island
     */
    
    public void testAddOccupantOnIslandValidOccupant() {
        assertTrue( testIsland.addOccupant(onIsland, snake));      
    }
    
    public void testAddOccupantNotOnIsland() {
        assertFalse( testIsland.addOccupant(notOnIsland, snake));      
    } 
    
    public void testAddOccupantNull() {
        assertFalse( testIsland.addOccupant(onIsland, null));      
    }


    /**
     * Tests for removing occupant from island
     */

    public void testRemoveOccupantOnIslandValidOccupant() {
        assertTrue( testIsland.addOccupant(onIsland, snake));
        assertTrue( testIsland.removeOccupant(onIsland, snake));
        assertFalse(snake.getPosition().isOnIsland());
    }

    public void testRemoveOccupantPositionNotOnIsland() {
        assertTrue( testIsland.addOccupant(onIsland, snake));
        assertFalse( testIsland.removeOccupant(notOnIsland, snake));
    }


    public void testRemoveOccupantNotAtPosition() {
        Position another = new Position(testIsland, 0,0);
        Animal differentSnake = new Animal(another, "sss", "Slithering snake",  7.0);
        assertFalse( testIsland.removeOccupant(onIsland, differentSnake));
    }

}
