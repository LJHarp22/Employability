package nz.ac.aut.prog2.ass3.gameModel;

/**
 * This class represents a tool that can be found on the island
 * and gives the player any sort of advantage. 
 * 
 * @author Stefan Marks
 * @version July 2011
 */

public class Tool extends Item 
{
    /**
     * Construct a tool with known attributes.
     * @param pos the position of the tool
     * @param name the name of the tool
     * @param description a longer description of the tool
     * @param weight the weight of the tool
     * @param size the size of the tool
     */
    public Tool(Position pos, String name, String description, double weight, double size) 
    {
        super(pos, name, description, weight, size);
    }
    
    @Override
    public String getStringRepresentation() 
    {
        return "T";
    }
}
