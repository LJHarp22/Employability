package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.Position;
import nz.ac.aut.prog2.ass3.gameModel.Tool;

/**
 * The test class ToolTest.
 *
 * @author  Anne Philpott
 * @version S 2 2011
 */

public class ToolTest extends junit.framework.TestCase
{
    Tool ladder;
    Position position;
    Island island;
/**
     * Default constructor for test class ToolTest
     */
    public ToolTest()
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
        position = new Position(island, 2,3);
        ladder = new Tool(position, "ladder", "A useful rope ladder", 2.0, 3.0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        ladder = null;
        island = null;
        position = null;
    }


        /**
     * Test of getPosition method, of class Tool.
     */
    public void testGetPosition() {
        assertEquals(position, ladder.getPosition());
    }

    /**
     * Test of SetPosition method, of class Tool.
     */
    public void testSetPosition() {
        Position newPosition = new Position(island,1,2);
        ladder.setPosition(newPosition);
        assertEquals(newPosition, ladder.getPosition());
    }

    /**
     * Test of getName method, of class Tool.
     */
    public void testGetName() {
        assertEquals("ladder", ladder.getName());
    }


    // Test description accessor
    public void testGetDescription()
    {
        assertEquals("A useful rope ladder", ladder.getDescription());
    }

    // Test weight accessor
    public void testGetWeight()
    {
        assertEquals(2.0, ladder.getWeight(), 0.01);
    }

    // Test size accessor
    public void testGetSize()
    {
        assertEquals(3.0, ladder.getSize(), 0.01);
    }

    /**
     * Test of toString method, of class Item.
     */
    public void testGetStringRepresentation() {
        assertEquals("T",ladder.getStringRepresentation());
    }
}
