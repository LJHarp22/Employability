package nz.ac.aut.prog2.ass3.gameModel;

/**
 *  Animal represents Animals in Lemur Island game.
 * 
 * @author Anne Philpott & Stefan Marks
 * @version July 2011
 */
public class Animal extends Occupant
{
    private double danger;

    /**
     * Constructor for objects of class Animal
     */
    public Animal(Position pos, String name, String description,  double danger) 
    {
        super(pos, name, description);
        this.danger = danger;
    } 
    
    /**
     * Is this animal dangerous
     * @return true if danger >0
     */
    public boolean isDangerous() {
        return (this.danger > 0);
    }
    
    /**
     * Gets the danger level
     * @return the danger
     */
    public double getDanger() {
        return this.danger;
    }

    @Override
    public String getStringRepresentation() 
    {
        if ( isDangerous() ) return "A";
        else                 return "a";
    }    
}
