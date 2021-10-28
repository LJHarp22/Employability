package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Map {
    
    private final int rows;
    private final int cols;
    private GridSquare grid [][];
    public Position previousPlayerPos;
    
    /**
     * Constructs a Map object
     * @param rows the number of rows on the Map
     * @param cols the number of columns on the Map
     */
    public Map(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.previousPlayerPos = null;
        initialiseIsland();
    }
    
    /**
     * Get the value of rows
     * @return the value of rows
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Get the value of cols
     * @return the value of cols
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * Get a grid square with a particular position.
     * @param pos the position of the Grid Square
     * @return Grid Square with this position
     */
    public GridSquare getGridSquare(Position pos) {
        GridSquare result = null;
        
        result = grid[pos.getRow()][pos.getCol()];
        
        return result;
    }
    
    /**
     * Populates the island grid with GridSquare objects
     * Terrain defaults to water. Actual terrain details will be updated later.
     */ 
    private void initialiseIsland() {
        grid = new GridSquare[rows][cols];
        for (int row = 0; row < this.rows; row++) 
        {
            for (int column = 0; column < this.cols; column++) 
            {
                GridSquare square = new GridSquare(Terrain.WATER);
                grid[row][column] = square;
            }
        }
    }
    
    /** 
     * Update the grid and the visible state of the grid to reflect new position of player.
     * @param player the player
     */
    public void updatePlayerPosition(Player player)
    {
        Position pos = player.getPosition();
        getGridSquare(pos).setPlayer(player);

        if ( previousPlayerPos != null )
        {
            getGridSquare(previousPlayerPos).setPlayer(null);
        }
        setVisible(pos.getNewPosition(MoveDirection.NORTH));
        setVisible(pos.getNewPosition(MoveDirection.EAST ));
        setVisible(pos.getNewPosition(MoveDirection.SOUTH));
        setVisible(pos.getNewPosition(MoveDirection.WEST ));
        
        previousPlayerPos = pos;
    } 

    /**
     * Private convenience method to change the visibility of grid squares.
     * @param pos the position to change
     */
    private void setVisible(Position pos)
    {
        if ( (pos != null))
        {
            grid[pos.getRow()][pos.getCol()].setVisible();
        }
    }  
}
