package nz.ac.aut.prog2.ass3.gameModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the class that knows the Lemur Island game rules and state
 * and enforces those rules.
 *
 * @author Stefan Marks
 * @version 1.0 - created
 * Maintenance History
 * August 2011 Extended for stage 2. Anne Philpott
 */

public class Game
{
    private Island island;
    private Player player;

    /**
     * A new instance of Lemur island that reads data from "IslandData.txt".
     */
    public Game() 
    {   
        initialiseIslandFromFile("IslandData.txt");
    }

    /**
     * Convenience method to get a grid square that the player is currently on.
     * @return the grid square at the player position
     */
    public GridSquare getCurrentGridSquare()
    {
        return island.getGridSquare(player.getPosition());
    } 

    
    /**
     * Convenience method to get the current player position.
     * @return the player position
     */
    public Position getPlayerPosition()
    {
        return player.getPosition();
    } 

    /**
     * Convenience method to get the current player.
     * @return the player
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Convenience method to get the current island.
     * @return the island
     */
    public Island getIsland()
    {
        return island;
    }
    
    /**
     * Convenience method to get the current occupants.
     * @return the occupants visible at the current position of the player
     */
    public Occupant[] getCurrentOccupants()
    {
        return getCurrentGridSquare().getOccupants();
    }
       
    /**
     * Attempts to move the player in the specified direction
     * @param direction the direction to move
     * @returns true if the move was successful, false if it was an invalid move
     */
    public boolean playerMove(MoveDirection direction)
    {
        // what terrain is the player moving on currently
        Position playerPos = player.getPosition();
        GridSquare square = island.getGridSquare(playerPos);
        Terrain  terrain        = square.getTerrain();
        boolean  canPlayerMove  = (player.hasStaminaToMove(terrain) && player.isAlive());
        Position newPos         = playerPos.getNewPosition(direction);
        boolean  successfulMove = canPlayerMove && (newPos != null);

        if ( successfulMove )
        {
            // move the player to new position
            player.moveToPosition(newPos, terrain);
            island.updatePlayerPosition(player);
            // are there dangerous animals?
            checkForDangerousAnimals();
        }
        return successfulMove;
    }

    /**
     * Checks if the player is attacked by dangerous animals
     * on the current grid square.
     */
    private void checkForDangerousAnimals()
    {
        //check if there are dangerous animals
        for ( Occupant occ : getCurrentOccupants() )
        {
            if ( occ instanceof Animal )
            {
                Animal animal = (Animal) occ;
                if ( animal.isDangerous() )
                {
                    player.reduceStamina(animal.getDanger());
                }
            }
        }
        checkIfPlayerKilled();
    }

    /**
     * Has Player been killed?
     */
    private void checkIfPlayerKilled() {
        if (player.getStaminaLevel() <= 0)
        {
            player.kill();
        }
    }
    
    /**
     * Draws the island grid to standard output.
     */
    public void drawIsland()
    {
        island.draw();
    }


    /**
     * Loads terrain and occupant data from a file.
     * At this stage this method assumes that the data file is correct and just
     * throws an exception or ignores it if it is not.
     * @param fileName file name of the data file
     */
    private void initialiseIslandFromFile(String fileName) 
    {
        try{
            Scanner input = new Scanner(new File(fileName));
            input.useDelimiter("\\s*,\\s*");

            //First create the island
            int numRows    = input.nextInt();
            int numColumns = input.nextInt();
            island = new Island(numRows, numColumns);

            //Read and setup the terrain
            setUpTerrain(input);

            //Read and setup the player
            setUpPlayer(input);

            //Read and setup the occupants
            setUpOccupants(input);

            input.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Unable to find data file '" + fileName + "'");
        }
        catch(IOException e){
            System.err.println("Problem encountered processing file.");
        }
    }

    /**
     * Reads terrain data and creates the terrain.
     * @param input data from the file
     */
    private void setUpTerrain(Scanner input) 
    {
        for ( int row = 0 ; row < island.getNumRows() ; row++ ) 
        {
            String terrainRow = input.next();
            for ( int col = 0 ; col < terrainRow.length() ; col++ )
            {
                Position pos = new Position(island, row, col);
                String   terrainString = terrainRow.substring(col, col+1);
                Terrain  terrain = Terrain.getTerrainFromStringRepresentation(terrainString);
                island.getGridSquare(pos).setTerrain(terrain);
            }
        }
    }

    /**
     * Reads player data and creates the player.
     * @param input data from the file
     */
    private void setUpPlayer(Scanner input) 
    {
        String playerName              = input.next();
        int    playerPosRow            = input.nextInt();
        int    playerPosCol            = input.nextInt();
        double playerMaxStamina        = input.nextDouble();
        double playerMaxBackpackWeight = input.nextDouble();
        double playerMaxBackpackSize   = input.nextDouble();
        
        Position pos = new Position(island, playerPosRow, playerPosCol);
        player = new Player(pos, playerName, 
                playerMaxStamina, 
                playerMaxBackpackWeight, playerMaxBackpackSize);
        island.updatePlayerPosition(player);
    }

    /**
     * Creates occupants listed in the file and adds them to the island.
     * @param input data from the file
     */
    private void setUpOccupants(Scanner input) 
    {
        int numItems = input.nextInt();
        for ( int i = 0 ; i < numItems ; i++ ) 
        {
            String occType  = input.next();
            String occName  = input.next(); 
            String occDesc  = input.next();
            int    occRow   = input.nextInt();
            int    occCol   = input.nextInt();
            Position occPos = new Position(island, occRow, occCol);
            Occupant occ    = null;

            if ( occType.equals("T") )
            {
                double weight = input.nextDouble();
                double size   = input.nextDouble();
                occ = new Tool(occPos, occName, occDesc, weight, size);
            }
            else if ( occType.equals("F") )
            {
                double weight = input.nextDouble();
                double size   = input.nextDouble();
                double energy = input.nextDouble();
                occ = new Food(occPos, occName, occDesc, weight, size, energy);
            }
            else if ( occType.equals("A") )
            {
                double danger = input.nextDouble();
                occ = new Animal(occPos, occName, occDesc, danger);
            }

            if ( occ != null ) island.addOccupant(occPos, occ);
        }
    }    

}
