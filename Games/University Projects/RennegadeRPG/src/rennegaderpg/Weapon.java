package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public abstract class Weapon {
    
    private final int attack;
    private final String name;
    private final String description;
    
    /**
     * Constructs a weapon object
     * @param name the name of the weapon
     * @param description the description of the weapon
     * @param attack the attack of the weapon
     */
    public Weapon(String name, String description, int attack) {
        this.name = name;
        this.description = description;
        this.attack = attack;
    }
    
    /**
     * Get the weapon name
     * @return the name of the object
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the weapon description
     * @return the description of the object
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Get the weapon attack
     * @return the objects attack value
     */
    public int getAttack() {
        return attack;
    }
    
}
