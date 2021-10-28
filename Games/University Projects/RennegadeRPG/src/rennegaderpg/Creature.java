package rennegaderpg;

/**
 * 
 * @author Luke Harper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Creature extends Occupant {
    
    private final int attack;
    private final int experience;
    private final int maxHealth;
    private int time;              //Used to calculate creature spawn time
    private int health;    
    private boolean isAlive;
    
    /**
     * Constructs a Creature object
     * @param name the name of the object
     * @param position the position of the object
     * @param attack the attack of the creature
     * @param experience the experience points value
     * @param health the health of the object
     */
    public Creature(String name, Position position, int attack, int experience,
            int health) {
        
        super(name, position);
        this.attack = attack;
        this.experience = experience;
        this.health = health;
        this.maxHealth = health;
        this.time = 0;
        this.isAlive = true;
    }
    
    /**
     * Gets the attack of the object
     * @return the objects attack value
     */
    public int getAttack() {
        return attack;
    }
    
    /**
     * Gets the experience of the object
     * @return the objects experience value
     */
    public int getExperience() {
        return experience;
    }
    
    /**
     * Gets the health of the object
     * @return the objects health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Gets the objects maximum health
     * @return the objects maximum health
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * Reduces the objects health
     * @param value the amount of health to reduce
     */
    public void reduceHealth(int value) {
        if(value < 0) 
            throw new IllegalArgumentException("Negative Value " + value );
        health -= value;
        //DID THE OBJECT DIE?
        if(health <= 0) {
            isAlive = false;
            health = 0;
        }
    }
    
    /**
     * Sets the objects health to the maximum
     */
    public void resetHealth() {
        health = maxHealth;
    }
    
    /**
     * Gets the object time
     * @return the time of the object
     */
    public int getTime() {
        return time;
    }
    
    /**
     * Sets the object time
     * @param value the time value
     */
    public void setTime(int value) {
        this.time = value;
    }
    
    /**
     * Checks the object is alive or not
     * @return true if alive or false if not
     */
    public boolean isAlive() {
        return isAlive;
    }
    
    /**
     * Set the object isAlive to true
     */
    public void alive() {
        isAlive = true;
    }
    
    /**
     * Set the object isAlive to false
     */
    public void dead() {
        isAlive = false;
    }
}
