package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Melee extends Weapon{
    
    private final int defence;

    /**
     * Constructs a Melee object, inherits Weapon class
     * @param name the name of the object
     * @param description the description of the object
     * @param attack the attack value of the object
     * @param defence the defence value of the object
     */
    public Melee(String name, String description, int attack, int defence) {
        super(name,description, attack);
        this.defence = defence;
    }
    
    /**
     * Get the defence value
     * @return the defence value of the Melee weapon
     */
    public int getDefence() {
        return defence;
    }
}
