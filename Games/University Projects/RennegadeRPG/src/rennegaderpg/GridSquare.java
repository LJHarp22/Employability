package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class GridSquare {
    
    private Terrain terrain;
    private Occupant occupant;
    private Player player;
    private boolean isVisible;
    
    /**
     * Constructs a GridSquare object
     * @param terrain the Terrain of the object
     */
    public GridSquare(Terrain terrain) {
        this.terrain = terrain;
        this.player = null;
        this.isVisible = false;
        occupant = null;
    }
    
    /**
     * Gets the object Terrain
     * @return the Terrain value
     */
    public Terrain getTerrain() {
        return terrain;
    }
    
    /**
     * Sets the object Terrain
     * @param terrain the Terrain value to be set
     */
    public void setTerrain(Terrain terrain) {
        if(terrain == null)
            throw new IllegalArgumentException("Null Parameter " + terrain);
        this.terrain = terrain;
    }

    /**
     * Checks if the object contains a Player object
     * @return true if the object contains a Player, false if not
     */
    public boolean hasPlayer() {
        return (this.player != null);
    }
    
    /**
     * Sets the player on the grid square.
     * @param player the player on the square
     * or null if there is no player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Returns a string representation of the terrain,
     * @return string that represents the terrains
     */
    public String getTerrainStringRepresentation()
    {
        String result =  terrain.getStringRepresentation();
        return result;
    }
    
    /**
     * Adds an occupant to the GridSquare
     * @param occupant the occupant to add
     */
    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }
    
    /**
     * Gets the occupant object of the Grid Square
     * @return the occupant object, or null if no object 
     */
    public Occupant getOccupant() {
        return occupant;
    }
    
    /**
     * Removes the occupant from the Grid Square
     */
    public void removeOccupant() {
        occupant = null;
    }
    
    /**
     * Marks this grid square as being visible to the player.
     */
    public void setVisible() {
        this.isVisible = true;
    }
    
    /**
     * Check if this grid square is visible to the player.
     * @return true is the grid square is visible to the player,
     *              False if not.
     */
    public boolean isVisible() {
        return isVisible;
    }
}
