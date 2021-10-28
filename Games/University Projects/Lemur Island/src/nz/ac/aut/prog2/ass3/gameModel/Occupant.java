package nz.ac.aut.prog2.ass3.gameModel;

/**
 * Abstract base class for occupants that inhabit Lemur Island.
 * 
 * @author Anne Philpott & Stefan Marks
 * @version July 2011
 */
public abstract class Occupant 
{

    private Position position;
    private String name;
    private String description;    

    /**
     * Construct an occupant for a known position & name.
     * @param position the position of the occupant
     * @param name the name of the occupant
     * @param description a longer description
     */
    public Occupant(Position position, String name, String description) 
    {
        this.position = position;
        this.name = name;
        this.description = description;        
    }
    
    /**
     * Returns the position of the occupant.
     * @return the position of the occupant
     */    
    public Position getPosition() 
    {
        return this.position;
    }
    
    /**
     * Changes the position of the occupant
     * @param pos the new position
     */
    public void setPosition(Position pos) 
    {
        this.position = pos;
    }
    
    /**
     * Gets the occupant's name
     * @return the name of the occupant
     */
    public String getName()
    {
        return this.name;
    } 

    
   /**
    * Gets the  description for the item.
    * @return the  description
    */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Gets a string representation of the occupant.
     * @return the string representation of the occupant
     */
    public abstract String getStringRepresentation();


}
