package nz.ac.aut.prog2.ass3.gameModel;

/**
 * A class to represent the world in which the game is played.
 * @author Anne Philpott & Stefan Marks
 * @version Stage 1
 */
public class Island
{
    private int numRows;
    private int numColumns;
    private GridSquare[][] islandGrid;
    private Position previousPlayerPos;

   /**
    * Initial island constructor.
    * @param numRows the amount of rows on the island
    * @param numColumns the amount of columns on the island
    */
    public Island( int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.previousPlayerPos = null;
        initialiseIsland();
    }

    /**
     * Get the value of numRows
     * @return the value of numRows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Get the value of numColumns
     * @return the value of numColumns
     */
    public int getNumColumns() {
        return numColumns;
    }
    
    /**
     * Get a grid square with a particular position.
     * @param position of the square
     * @return Square with this position
     */
    public GridSquare getGridSquare(Position pos)
    {
        GridSquare result = null;
        if ( pos.isOnIsland() )
        {
            result = islandGrid[pos.getRow()][pos.getColumn()];
        }
        return result;
    }    
  
    /**
     * Private convenience method to change the visibility of grid squares.
     * @param pos the position to change
     */
    private void setVisible(Position pos)
    {
        if ( (pos != null) && pos.isOnIsland() )
        {
            islandGrid[pos.getRow()][pos.getColumn()].setVisible();
        }
    }    

    /** Update the grid and the explored & visible state of the grid to reflect new position of player.
     * @param the player
     */
    public void updatePlayerPosition(Player player)
    {
        // the grid with the player on it is now explored...
        Position pos = player.getPosition();
        getGridSquare(pos).setExplored();
        //... and has the player on it
        getGridSquare(pos).setPlayer(player);

        // remove player from previous island
        if ( previousPlayerPos != null )
        {
            getGridSquare(previousPlayerPos).setPlayer(null);
        }

        // add visibility to all new adjacent squares
        setVisible(pos.getNewPosition(MoveDirection.NORTH));
        setVisible(pos.getNewPosition(MoveDirection.EAST ));
        setVisible(pos.getNewPosition(MoveDirection.SOUTH));
        setVisible(pos.getNewPosition(MoveDirection.WEST ));

        // remember the new player position
        previousPlayerPos = pos;
        
    }
    
    /**
     * Attempts to add an occupant to a specified position on the island.
     * @param pos the position to add the occupant
     * @param occupant the occupant to add
     * @return true if occupant was successfully added, false if not
     */
    public boolean addOccupant(Position pos, Occupant occupant)
    {
        boolean success = false;
        if( pos.isOnIsland()&& occupant != null )
        {
            GridSquare gs = getGridSquare(pos);
            success = gs.addOccupant(occupant);
        }
        if ( success )
        { 
            //update the occupants address
            occupant.setPosition(pos);
        }
        return success;
    }    

        /**
     * Attempts to remove an occupant from a specified position on the island.
     * @param pos the position where to remove the occupant
     * @param occupant the occupant to remove
     * @return true if occupant was successfully removed, false if not
     */
    public boolean removeOccupant(Position pos, Occupant occupant)
    {
        boolean success = false;
        if( pos.isOnIsland()&& occupant != null  )
        {
            GridSquare gs = getGridSquare(pos);
            success = gs.removeOccupant(occupant);
        }
        if ( success )
        {
            //update the occupants address to the "not on island position"
            occupant.setPosition(Position.NotOnIsland);
        }
        return success;
    }
    
    /**
     * Produces a textual representation of the island on the console.
     * This exists mainly for debugging purposes.
     */
    public void draw() 
    {
        final int CELL_SIZE = 4;
        
        // create the horizontal line as a string
        String horizontalLine = "-";
        for ( int col = 0; col < this.numColumns; col++ ) {
            for ( int i = 0 ; i < CELL_SIZE ; i++ ) {
                horizontalLine += "-";
            }            
            horizontalLine += "-";
        }
        
        // print the content
        for ( int row = 0; row < this.numRows; row++ ) 
        { 
            String rowOccupant = "|";
            String rowTerrain  = "|";
            for ( int col = 0; col < this.numColumns; col++ ) 
            {
                GridSquare g = islandGrid[row][col];
                // create string with occupants
                String cellOccupant = g.hasPlayer() ? "P" : " ";
                cellOccupant       += g.getOccupantStringRepresentation();
                for ( int i = cellOccupant.length() ; i < CELL_SIZE ; i++ ) {
                    cellOccupant += " ";
                }
                rowOccupant += cellOccupant + "|";
                
                // create string with terrain
                String cellTerrain = "";
                for ( int i = 0 ; i < CELL_SIZE ; i++ ) {
                    cellTerrain += g.getTerrainStringRepresentation();
                }
                rowTerrain += cellTerrain + "|";
            }
            System.out.println(horizontalLine);
            System.out.println(rowOccupant);
            System.out.println(rowTerrain);
        }
        System.out.println(horizontalLine);
    }

    /**
     * Populates the island grid with GridSquare objects
     * Terrain defaults to water. Actual terrain details will be updated later.
     */
    private void initialiseIsland() {
        islandGrid = new GridSquare[numRows][numColumns];
        for (int row = 0; row < this.numRows; row++) 
        {
            for (int column = 0; column < this.numColumns; column++) 
            {
                GridSquare square = new GridSquare(Terrain.WATER);
                islandGrid[row][column] = square;
            }
        }
    }

}
