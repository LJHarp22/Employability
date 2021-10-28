package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Animal;
import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.Position;



/**
 * The test class AnimalTest.
 *
 * @author  Anne Philpott
 * @version S 2 2011
 */
public class AnimalTest extends junit.framework.TestCase
{
    Animal dangerous;
    Animal harmless;
    Position position;
    Island island;
    /**
     * Default constructor for test class AnimalTest
     */
    public AnimalTest()
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
        dangerous = new Animal(position, "leo", "A scary lion",  10.0);
        harmless = new Animal(position, "Freda", "A friendly dog", 0.0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        dangerous = null;
        harmless =  null;
        island = null;
        position = null;
    }

        /**
     * Test of getPosition method, of class Animal.
     */
    public void testGetPosition() {
        assertEquals(position, harmless.getPosition());
    }

    /**
     * Test of updateAddress method, of class Animal
     */
    public void testSetPosition() {
        Position newPosition = new Position(island,1,2);
        dangerous.setPosition(newPosition);
        assertEquals(newPosition, dangerous.getPosition());
    }

    /**
     * Test of getName method, of class Occupant.
     */
    public void testGetName() {
        assertEquals("leo", dangerous.getName());
    }


    // Test description accessor
    public void testGetDescription()
    {
        assertEquals("A friendly dog", harmless.getDescription());
    }

    // Test danger accessor
    public void testGetDanger()
    {
        assertEquals(10.0, dangerous.getDanger(), 0.01);
    }

    // Tests for isDangerous
    public void testIsDangerousWhenDangerous()
    {
        assertTrue(dangerous.isDangerous());
    }

    public void testIsDangerousNotDangerous()
    {
        assertFalse(harmless.isDangerous());
    }
    /**
     * Tests of getStringRepresentation method.
     */
    public void testGetStringRepresentationDangerous() {
        assertEquals("A",dangerous.getStringRepresentation());
    }

    public void testGetStringRepresentationNotDangerous() {
        assertEquals("a",harmless.getStringRepresentation());
    }
}
