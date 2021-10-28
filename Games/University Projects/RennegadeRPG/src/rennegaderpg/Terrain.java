package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public enum Terrain {
    
    GRASS ("!", "Lush grass"),
    DIRT ("#", "Dirt Road"),
    TREE ("T", "Tall Trees"),
    ROCK ("R", "A Boulder"),
    CAVE ("C", "A Cave"),
    SAND (".", "Sand"),
    WATER ("~", "Treacherous Waters"),
    BRIDGE ("=", "Rickety Bridge"),
    SNOW ("^", "Cold Snow"),
    MUD ("M", "Thick Mud");

    
    private final String rep;
    private final String description;
    
    /**
     * Creates a new terrain with a string representation and description
     * @param rep the terrain string representation
     * @param description the terrain description
     */
    private Terrain(String rep, String description) {
        this.rep = rep;
        this.description = description;
    }
    
    /**
     * Gets a string representation of the terrain to print on the console
     * @return string representation of the terrain
     */
    public String getStringRepresentation() {
        return rep;
    }
    
    /**
     * Gets the description of the terrain object
     * @return the terrain description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns a terrain object from the terrain string representation.
     * @param rep the string to compare
     * @return the terrain that is associated with this terrain
     */
    public static Terrain getTerrainFromStringRep(String rep) {
        Terrain result = null;
        for(Terrain t : values()) {
            if(t.getStringRepresentation().equals(rep))
                result = t;
        }
        return result;
    }
    
}
