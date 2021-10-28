package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Food;
import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.Position;

/**
 * The test class FoodTest.
 *
 * @author  Anne Philpott
 * @version S 2 2001
 */
public class FoodTest extends junit.framework.TestCase
{
    Food apple;
    Position position;
    Island island;
    /**
     * Default constructor for test class FoodTest
     */
    public FoodTest()
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
        position = new Position(island, 4,4);
        apple = new Food(position, "apple", "A juicy red apple", 1.0, 2.0, 1.5);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        apple = null;
        island = null;
        position = null;
    }

    /**
     * Test of getPosition method, of class Food.
     */
    public void testGetPosition() {
        assertEquals(position, apple.getPosition());
    }

    /**
     * Test of SetPosition method, of class Food.
     */
    public void testSetPosition() {
        Position newPosition = new Position(island,1,2);
        apple.setPosition(newPosition);
        assertEquals(newPosition, apple.getPosition());
    }

    /**
     * Test of getName method, of class Food.
     */
    public void testGetName() {
        assertEquals("apple", apple.getName());
    }


    // Test description accessor
    public void testGetDescription()
    {
        assertEquals("A juicy red apple", apple.getDescription());
    }

    // Test weight accessor
    public void testGetWeight()
    {
        assertEquals(1.0, apple.getWeight(), 0.01);
    }

    // Test size accessor
    public void testGetSize()
    {
        assertEquals(2.0, apple.getSize(), 0.01);
    }

    // Test energy accessor
    public void testGetEnergy()
    {
        assertEquals(1.5, apple.getEnergy(), 0.01);
    }

    /**
     * Test of toString method, of class Item.
     */
    public void testGetStringRepresentation() {
        assertEquals("F",apple.getStringRepresentation());
    }
}
