package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Position {
    
    private final int row;
    private final int col;
    private Map map;
    
    /**
     * Constructs a Position object
     * @param map the map of the object
     * @param row the row of the object
     * @param col the column of the object
     */
    public Position(Map map, int row, int col) {
        this.map = map;
        this.row = row;
        this.col = col;
    }
    
    /**
     * Sets the Map object of the Position
     * @param map the set Map object
     */
    public void setMap(Map map) {
        this.map = map;
    }
    
    /**
     * Gets the map object of the Position
     * @return the map of the object;
     */
    public Map getMap() {
        return map;
    }
    
    /**
     * Gets the row of the object
     * @return the object Row
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Gets the column of the object
     * @return the object column
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Gets the new Position object
     * @param direction the movement direction
     * @return the new Position, or null if the position is invalid
     */
    public Position getNewPosition(MoveDirection direction)
    {
        Position newPosition = null;
        if ((direction == MoveDirection.NORTH) && (row > 0)) {
            newPosition = new Position(map, row - 1, col);
        } else if ((direction == MoveDirection.EAST)
                && (col < map.getCols() - 1)) {
            newPosition = new Position(map, row, col + 1);
        } else if ((direction == MoveDirection.SOUTH)
                && (row < map.getRows() - 1)) {
            newPosition = new Position(map, row + 1, col);
        } else if ((direction == MoveDirection.WEST) && (col > 0)) {
            newPosition = new Position(map, row, col - 1);
        }
        return newPosition;
    }
    
}
