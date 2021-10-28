package nz.ac.aut.prog2.ass3.gameModel;


/**
 * This class represents an item that can be found on the island.
 * 
 * @author Stefan Marks & Anne Philpott 
 * @version July 2011
 */
public  abstract class Item extends Occupant
{
    private double weight;
    private double size;

    /**
     * Construct an item with known attributes.
     * @param pos the position of the item
     * @param name the name of the item
     * @param description a longer description of the item
     * @param weight the weight of the item
     * @param size the size of the item. 
     *        A size of 0 indicates an item that cannot be carried.
     */
    public Item(Position pos, String name, String description,
                double weight, double size) 
    {
        super(pos, name, description);
        this.weight = weight;
        this.size = size;
    }
    
    /**
     * Gets the weight of the item
     * @return the weight of the item
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Gets the size of the item
     * @return the size of the item
     */
    public double getSize() {
        return this.size;
    }    

}
