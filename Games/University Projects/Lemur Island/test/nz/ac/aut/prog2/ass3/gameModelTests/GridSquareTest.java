package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Animal;
import nz.ac.aut.prog2.ass3.gameModel.Food;
import nz.ac.aut.prog2.ass3.gameModel.GridSquare;
import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.Player;
import nz.ac.aut.prog2.ass3.gameModel.Position;
import nz.ac.aut.prog2.ass3.gameModel.Terrain;
import nz.ac.aut.prog2.ass3.gameModel.Tool;


/**
 * The test class GridSquareTest.
 *
 * @author  Anne Philpott
 * @version July 2011
 */
public class GridSquareTest extends junit.framework.TestCase
{
    GridSquare emptySquare;
    GridSquare occupiedSquare;
    Island island;
    Position position;
    Food apple;

    /**
     * Default constructor for test class GridSquareTest
     */
    public GridSquareTest()
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
        emptySquare = new GridSquare(Terrain.SAND);
        occupiedSquare = new GridSquare(Terrain.FOREST);
        island = new Island(5,5);
        position = new Position(island, 0,0);
        apple = new Food(position, "apple", "A juicy red apple", 1.0, 2.0, 1.5);
        occupiedSquare.addOccupant(apple);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        emptySquare = null;
        occupiedSquare = null;
        island = null;
        position = null;
        apple = null;
    }
    
    /**
     * Test of isVisible method, of class GridSquare.
     */
    public void testIsVisibleNewSquare() {
        assertFalse( emptySquare.isVisible());
    } 
    
    /**
     * Test of isExposed method, of class GridSquare.
     */
    public void testIsExploredNewSquare() {
        assertFalse( emptySquare.isExplored());
    }
    
    
    /**
     * Test of setVisible method, of class GridSquare.
     */
    public void testSetVisible() {
        emptySquare.setVisible();
        assertTrue( emptySquare.isVisible());
    } 
    
    /**
     * Test of setExplored method, of class GridSquare.
     */
    public void testsetExplored() {
        emptySquare.setExplored();
        assertTrue(emptySquare.isExplored());
    } 
    
    /**
     * Test of hasPlayer method, of class GridSquare.
     */
   public void testHasPlayerNoPlayer() {
        assertFalse( emptySquare.hasPlayer());
    }
    
    public void testHasPlayerWithPlayer() {
        Player player = new Player(position,"Guybrush Threepwood",25.0, 10.0, 12.0);
        occupiedSquare.setPlayer(player);
        assertTrue( occupiedSquare.hasPlayer());
    }  
    
    /**
     * Tests for addOccupant
     */
   
    public void testAddOccupantWhenNotFull() {
        Tool map = new Tool(position, "map", "A treasure map", 1.0, 2.0);        
        assertTrue(occupiedSquare.addOccupant(map));        
        assertTrue(occupiedSquare.hasOccupant(map));
    } 
    
    public void testAddOccupantWhenFull() {  
        // Add another occupant
        Tool map = new Tool(position, "map", "A treasure map", 1.0, 2.0);
        occupiedSquare.addOccupant(map); 
        // Add a third occupant
        Animal scary = new Animal(position, "leo", "A scary lion",  10.0); 
        occupiedSquare.addOccupant(scary);         
        //Now the cave has three occupants it should not be possible to add another
        Animal pipkin = new Animal(position, "pipkin", "A very timid rabbit",  0.0); 
        assertFalse(occupiedSquare.addOccupant(pipkin));
        assertFalse(occupiedSquare.hasOccupant(pipkin));
    } 
    
    public void testAddOccupantDuplicate() {  
        // Attempt to add again
        assertFalse(occupiedSquare.addOccupant(apple));

    }
    
    public void testAddOccupantNull() {
        assertFalse(occupiedSquare.addOccupant(null));
    }    

        /**
     * Tests for removeOccupant
     */

    public void testRemoveOccupantWhenPresent() {
        Food apple = new Food(position, "apple", "A juicy red apple", 1.0, 2.0, 1.5);
        assertTrue(occupiedSquare.addOccupant(apple));
        assertTrue(occupiedSquare.removeOccupant(apple));
        assertFalse(occupiedSquare.hasOccupant(apple));
    }

    public void testRemoveOccupantWhenNotPresent() {
        Food apple = new Food(position, "apple", "A juicy red apple", 1.0, 2.0, 1.5);
        assertFalse(occupiedSquare.removeOccupant(apple));
    }

    public void testRemoveOccupantWhenNull() {
        Food apple = null;
        assertFalse(occupiedSquare.removeOccupant(apple));
    }
    
    /**
     * Tests for string representation
     */
    public void testGetOccupantStringRepresentationNoOccupants(){         
        occupiedSquare.setVisible();
        assertEquals("",emptySquare.getOccupantStringRepresentation() );    
    }

    public void testGetOccupantStringRepresentationSingle(){
        assertEquals("F",occupiedSquare.getOccupantStringRepresentation() );    
    }
    
    
    public void testGetOccupantStringRepresentationMultipleOccupants(){
        // Add another occupant
        Tool map = new Tool(position, "map", "A treasure map", 1.0, 2.0);
        occupiedSquare.addOccupant(map); 
        // Add a third occupant
        Animal scary = new Animal(position, "leo", "A scary lion",  10.0); 
        occupiedSquare.addOccupant(scary);          
        assertEquals("FTA",occupiedSquare.getOccupantStringRepresentation() );    
    } 
    
    /**
     * Test of Terrain methods, of class GridSquare.
     */
      
    public void testGetTerrain() {
        assertEquals(Terrain.SAND, emptySquare.getTerrain());
    }
    
    public void testSetTerrain() {
        emptySquare.setTerrain(Terrain.FOREST);
        assertEquals(Terrain.FOREST, emptySquare.getTerrain());
    }
 
}

