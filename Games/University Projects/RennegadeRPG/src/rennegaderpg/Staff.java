package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Staff extends Weapon {
    
    private final int magic;
    private final int defence;
    
    /**
     * Constructs a Staff object
     * @param name the name of the staff
     * @param description the description of the staff
     * @param attack the attack value of the staff
     * @param defence the defence value of the staff
     * @param magic the magic value of the staff
     */
    public Staff(String name, String description, int attack, int defence, int magic) {
        super(name,description, attack);
        this.defence = defence;
        this.magic = magic;
    }
    
    /**
     * Gets the staff magic value
     * @return the staff magic
     */
    public int getMagic() {
        return magic;
    }
    
    /**
     * Gets the staff defence value
     * @return the staff defence
     */
    public int getDefence() {
        return defence;
    }
}
