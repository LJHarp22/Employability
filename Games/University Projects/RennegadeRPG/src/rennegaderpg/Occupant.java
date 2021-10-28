package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public abstract class Occupant {
    
    private final String name;
    private final Position position;
    
    /**
     * Constructs an Occupant object
     * @param name the name of the occupant
     * @param position the position of the occupant
     */
    public Occupant(String name, Position position) {
        this.name = name;
        this.position = position;
    }
    
    /**
     * Get the occupant name
     * @return the occupant name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the position of the occupant
     * @return the position of the occupant
     */
    public Position getPosition() {
        return position;
    }
    
}
