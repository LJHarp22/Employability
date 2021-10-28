package nz.ac.aut.prog2.ass3.gameModel;

/**
 * Represents the location of any item/grid square/player in the game.
 * Positions must always be valid for the particular game.
 * Valid positions include somewhere on the island or surrounding sea,
 * or the NotOnIsland position which is used for such things as in the player's backpack.
 *
 * @author Stefan Marks & Anne Philpott
 * @version 2.0 - created July 2011
 */

public class Position
{
    private int row;
    private int column;
    private Island island;
    
    /** 
     * Position for objects that are not on the island,
     * e.g., in the player's backpack.
     */
    public static final Position NotOnIsland = new Position();
            
    /**
     * Constructs a position that is not on the island (e.g., player's backpack).
     */
    private Position()
    {
        this.island = null;
        this.row    = 0;
        this.column = 0;
    }
    
    /**
     * Constructor for objects of class Position
     * Can only construct valid positions. Invalid arguments result in exceptions.
     * @param row, row value for positions
     * @param column, column value for positions
     * @param island, the island on which the position is
     */
    public Position(Island island, int row, int column)
    {
        if ( island == null )
        {
            throw new IllegalArgumentException(
                    "Island parameter cannot be null");
        }
        if ( (row < 0) || (row >= island.getNumRows()) )
        {
            throw new IllegalArgumentException(
                    "Invalid row for position (" + row + ")");
        }
        if ( (column < 0) || (column >= island.getNumRows()) )
        {
            throw new IllegalArgumentException(
                    "Invalid column for position (" + column + ")");
        }
        // parameters are valid -> save
        this.island = island;
        this.row    = row;
        this.column = column;
    }

    /**
     * Row part of position
     * @return row,  
     */
    public int getRow()
    {
       return this.row;
    }
    
    /**
     * Column part of position
     * @return column,  
     */
    public int getColumn()
    {
       return this.column;
    }
    
    /**
     * Sets the position as "not on the island".
     */
    public void removeFromIsland()
    {
        this.island = null;
        this.row    = 0;
        this.column = 0;
    }
    
    /**
     * Is this address on the island?
     * @return true if this address is on the island
     */
    public boolean isOnIsland()
    {
        return (this.island != null);
    } 


    /**
     * Creates a valid new position
     * if a move in this direction is valid from the current position.
     * It is not possible to move outside of the island.
     * It is not possible to move from a position that is not on the island.
     * @param direction the direction to move
     * @return new Position if this is a valid move, otherwise null
     */
    public Position getNewPosition(MoveDirection direction)
    {
        Position newPosition = null;
        if ( isOnIsland() )
        {
            if ( (direction == MoveDirection.NORTH) && (row > 0) )
            {
                newPosition = new Position(island, row-1, column);
            }
            else if ( (direction == MoveDirection.EAST) &&
                      (column < island.getNumColumns() - 1) )
            {
                newPosition = new Position(island, row, column + 1);
            }
            else if ( (direction == MoveDirection.SOUTH) &&
                      (row < island.getNumRows() -1) )
            {
                newPosition = new Position(island, row+1, column);
            }
            else if ( (direction == MoveDirection.WEST) && (column > 0) )
            {
                newPosition = new Position(island, row, column-1);
            }
        }
        return newPosition;
    }
  
}
