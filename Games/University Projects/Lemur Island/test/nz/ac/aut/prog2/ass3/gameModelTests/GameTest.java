package nz.ac.aut.prog2.ass3.gameModelTests;

import nz.ac.aut.prog2.ass3.gameModel.Animal;
import nz.ac.aut.prog2.ass3.gameModel.Game;
import nz.ac.aut.prog2.ass3.gameModel.GridSquare;
import nz.ac.aut.prog2.ass3.gameModel.Island;
import nz.ac.aut.prog2.ass3.gameModel.MoveDirection;
import nz.ac.aut.prog2.ass3.gameModel.Player;
import nz.ac.aut.prog2.ass3.gameModel.Position;

/**
 * The test class GameTest.
 *
 * @author  Anne & Stefan
 * @version S2 2011
 */
public class GameTest extends junit.framework.TestCase
{
    Game       game;
    Player     player;
    Position   playerPosition;
    
    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
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
        // Create a new game from the data file.
        //Player is in position 2,0 & has 100 units of stamina
        game           = new Game();
        playerPosition = game.getPlayerPosition();
        player = game.getPlayer();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        game = null;
        player = null;
        playerPosition = null;
    }

    /**
     * Test Accessor methods of Game
     */
    
    public void testGetCurrentGridSquare(){
        GridSquare gridSquare = game.getCurrentGridSquare();
        assertTrue( gridSquare.hasPlayer());
    }
    
    public void testGetPlayer(){
        String name = player.getName();
        String checkName = "Guybrush Threepwood";
        assertTrue(name.equals(checkName) );
    } 
    
    public void testGetPlayerPosition(){
        assertEquals(2, playerPosition.getRow());
        assertEquals(0, playerPosition.getColumn());
    }


    /**
     * Tests for playerMove method of Game
     * The game state for loaded game is shown below
     *
     *-----------------------------------------
     *|    |    |    |    |    |    | F  |    |
     *|~~~~|....|....|....|....|....|....|~~~~|
     *-----------------------------------------
     *|    |    |    |    |    |    |    | A  |
     *|....|....|****|~~~~|....|....|....|....|
     *-----------------------------------------
     *|P   | F  | T  |    |    |    |    |    |
     *|....|****|****|^^^^|^^^^|****|****|....|
     *-----------------------------------------
     *| F  | a  |    |    |    |    |    |    |
     *|....|....|****|****|^^^^|****|....|....|
     *-----------------------------------------
     *|    |    | A  |    |    |    |    |    |
     *|~~~~|....|....|****|****|....|....|~~~~|
     *-----------------------------------------
     *|    |    |    |    |    |    |    |    |
     *|....|....|~~~~|....|****|****|....|~~~~|
     *-----------------------------------------
     *| F  |    |    |    |    |    |    |    |
     *|....|....|~~~~|....|~~~~|....|....|....|
     *-----------------------------------------
     *|    |    |    |    |    |    |    | T  |
     *|....|~~~~|~~~~|....|~~~~|~~~~|~~~~|....|
     *-----------------------------------------
     */

    public void testPlayerMoveToInvalidPosition(){
        //Player is in position 2,0. Therefore a move West would be invalid
        assertFalse(game.playerMove(MoveDirection.WEST));
    }

    public void testPlayerMoveValidNoDamage(){
         //Add a harmless animal to player's destination
        Island island = game.getIsland();
        Position pos = new Position(island, 1,0);
        Animal hamster = new Animal(pos, "hamster", "A harmless hamster", 0.0);
        island.addOccupant(pos, hamster);
        double stamina = player.getStaminaLevel();

        //Player is in position 2,0. Therefore a move North would be valid
        assertTrue(game.playerMove(MoveDirection.NORTH));
        //And stamina is not affected by harmless animal
        assertEquals(stamina - Player.MOVE_STAMINA, player.getStaminaLevel());
    }

    public void testPlayerMoveDeadPlayer(){
        //Player is in position 2,0. Therefore a move North would be valid
        // if player was not dead.
        player.kill();
        assertFalse(game.playerMove(MoveDirection.NORTH));
    }

    public void testPlayerMoveDamageNonFatal(){
        //Add a non-fatal animal to player's destination
        Island island = game.getIsland();
        Position pos = new Position(island, 2,1);
        double spiderHarm = 5.0;
        Animal spider  = new Animal(pos, "spider", "A small vicious spider",spiderHarm);
        island.addOccupant(pos, spider);
        double stamina = player.getStaminaLevel();
        
        //Move Player to square where spider is
        assertTrue(game.playerMove(MoveDirection.EAST));
        //And stamina is reduced by move and non-fatal animal.
        assertEquals(stamina - (Player.MOVE_STAMINA + spiderHarm), player.getStaminaLevel());
        assertTrue(player.isAlive());
    }

    public void testPlayerMoveDamageFatal(){
        //Add a fatal animal to player's destination
        Island island = game.getIsland();
        Position pos = new Position(island, 3,0);
        double harm = 95.0;
        Animal fatalSnake  = new Animal(pos, "snake", "A nasty fatal snake",harm);
        island.addOccupant(pos, fatalSnake);

        //Move Player to square where spider is
        assertTrue(game.playerMove(MoveDirection.SOUTH));
        assertFalse(player.isAlive());
    }

    public void testPlayerMoveNotEnoughStamina(){

        //Move Player to different trerrain
        assertTrue(game.playerMove(MoveDirection.EAST));
        //Reduce player's stamina to less than is needed for next move
        player.reduceStamina(90.0);
        assertFalse(game.playerMove(MoveDirection.EAST));
    }

    public void testPlayerMoveUpdatesExplored(){
         //Square to north not currently explored
        Island island = game.getIsland();
        Position pos = new Position(island, playerPosition.getRow()-1,playerPosition.getColumn());
        GridSquare square = island.getGridSquare(pos);
        assertFalse(square.isExplored());

        //Move player north to unexplored square
        assertTrue(game.playerMove(MoveDirection.NORTH));
        assertTrue(square.isExplored());
    }

    public void testPlayerMoveUpdatesVisible(){
         //Square two rows north not currently visible
        Island island = game.getIsland();
        Position pos = new Position(island, playerPosition.getRow()-2,playerPosition.getColumn());
        GridSquare square = island.getGridSquare(pos);
        assertFalse(square.isVisible());

        //Move player north and sqaure to north of that becomes visible
        assertTrue(game.playerMove(MoveDirection.NORTH));
        assertTrue(square.isVisible());
    }

}
