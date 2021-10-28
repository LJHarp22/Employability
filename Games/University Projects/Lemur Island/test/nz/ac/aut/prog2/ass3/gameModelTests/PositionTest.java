package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.Position;

/**
 * The test class PositionTest.
 *
 * @author Anne
 * @version July 2011
 */
public class PositionTest extends junit.framework.TestCase
{
    Position onIsland;
    Position notOnIsland;
    Island island;
    /**
     * Default constructor for test class PositionTest
     */
    public PositionTest()
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
        onIsland = new Position(island, 1,2) ;
        notOnIsland = Position.NotOnIsland;
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
        onIsland = null;
        notOnIsland = null;       
    }

    // Tests new positions with valid parameters on island
    public void testPositionValidParametersOnIsland()
    {
        assertTrue(onIsland.isOnIsland());
    }

    // Tests new positions with valid parameters on island
    public void testPositionValidParameterNotOnIsland()
    {
        assertFalse(notOnIsland.isOnIsland());
    }

   //Tests to ensure correct exceptions thrpown for invalid arguments
    public void testIllegalArgumentNoIsland() throws Exception {
        try 
        {
            Position invalidPosition = new Position(null,0,0);
            fail("No exception thrown when island null.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message",expected.getMessage().indexOf("Island") >= 0);
        }
        
    }

    public void testIllegalArgumentRowNegative() throws Exception {
        try 
        {
            Position invalidPosition = new Position(island,-1,0);
            fail("No exception thrown when row negative.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message",expected.getMessage().indexOf("Invalid row") >= 0);
        }
        
    }
    
    public void testIllegalArgumentRowTooLarge() throws Exception {
        try 
        {
            Position invalidPosition = new Position(island,5,0);
            fail("No exception thrown when row too large.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message",expected.getMessage().indexOf("Invalid row") >= 0);
        }
        
    } 
    
    public void testIllegalArgumentColumnNegative() throws Exception {
        try 
        {
            Position invalidPosition = new Position(island,1,-1);
            fail("No exception thrown when column negative.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message",expected.getMessage().indexOf("Invalid column") >= 0);
        }
        
    }
    
    public void testIllegalArgumentColumnTooLarge() throws Exception {
        try 
        {
            Position invalidPosition = new Position(island,0,5);
            fail("No exception thrown when column too large.");
        }
        catch (IllegalArgumentException expected) 
        {
            assertTrue("Not expected exception message",expected.getMessage().indexOf("Invalid column") >= 0);
        }
        
    }
    
    // Test column accessor
    public void testGetColumn()
    {
        assertEquals(2, onIsland.getColumn());
    }    

    // Test row accessor
    public void testGetRow()
    {
        assertEquals(1, onIsland.getRow());
    }  

    // Test remove
    public void testRemoveFromIsland()
    {
        onIsland.removeFromIsland();
        assertFalse(onIsland.isOnIsland());
    }

}
