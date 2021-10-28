package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public abstract class Player {
    
    private String name;
    private Position position;    
    private int level;
    private int experience;
    private int defence;
    private int attack;
    private int magic;
    private int health;
    private int mana;
    private boolean isAlive;
    
    /**
     * Constructs a Player object
     * @param name the name of the player
     * @param level the level of the player
     * @param experience the experience of the player
     * @param position the position of the player
     */
    public Player(String name, int level, int experience, Position position) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.health = 100;
        this.mana = 100;
        this.position = position;
        this.isAlive = true;
    }
    
    /**
     * Get the player name
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the player name
     * @param name the name of the player to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the player level
     * @return player level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Set the player level
     * @param value the value of the players level
     */
    public void setLevel(int value) {
        if(value < 1)
            throw new IllegalArgumentException("Invalid Level Input " + value);
        level = value;
    }
    
    /**
     * Get the player experience
     * @return the player experience
     */
    public int getExperience() {
        return experience;
    }
    
    /**
     * Set the player experience
     * @param value the experience value set
     */
    public void setExperience(int value) {
        if(value < 0)
            throw new IllegalArgumentException("Negative Value " + value);
        experience = value;
    }
    
    /**
     * Increases the player experience
     * @param value the amount to increase player experience
     */
    public void increaseExperience(int value) {
        experience += value;
    }
    
    /**
     * Set the player defence
     * @param value the defence value to be set
     */
    public void setDefence(int value) {
        defence = value;
    }
    
    /**
     * Get the player defence
     * @return player defence
     */
    public int getDefence() {
        return defence;
    }
    
    /**
     * Increase player defence
     * @param value the defence value to increase
     */
    public void increaseDefence(int value) {
        defence += value;
    }
    
    /**
     * Decrease player defence
     * @param value the defence value to decrease
     */
    public void decreaseDefence(int value) {
        defence -= value;
    }
    
    /**
     * Set player attack
     * @param value the attack value set
     */
    public void setAttack(int value) {
        attack = value;
    }
    
    /**
     * Get player attack
     * @return player attack
     */
    public int getAttack() {
        return attack;
    }
    
    /**
     * Increase player attack
     * @param value the attack value increased
     */
    public void increaseAttack(int value) {
        attack += value;
    }
    
    /**
     * Decrease player attack
     * @param value the defence value decreased
     */
    public void decreaseAttack(int value) {
        attack -= value;
    }
    
    /**
     * Set player magic level
     * @param value the magic level to be set
     */
    public void setMagic(int value) {
        magic = value;
    }
    
    /**
     * Get the player magic level
     * @return player magic 
     */
    public int getMagic() {
        return magic;
    }
    
    /**
     * Increase player magic
     * @param value the magic value to increase
     */
    public void increaseMagic(int value) {
        magic += value;
    }
    
    /**
     * Decrease player magic
     * @param value the magic value to decrease
     */
    public void decreaseMagic(int value) {
        magic -= value;
    }
    
    /**
     * Get the player health
     * @return player health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Reduces the player health by the value
     * @param value the value to decrease the players health
     */
    public void reduceHealth(int value) {
        health -= value;
        if(health < 0) {
            health = 0;
            isAlive = false;
        }
    }
    
    /**
     * Resets the players health
     */
    public void resetHealth() {
        health = 100;
    }
    
    /**
     * Get the players mana
     * @return player mana
     */
    public int getMana() {
        return mana;
    }
    
    /**
     * Increases player mana
     */
    public void increaseMana() {
        //Every step the player has increased mana up to 100
        mana += 5;
        if(mana > 100)
            mana = 100;
    }
    
    /**
     * Reduces the player mana
     * @param value the amount to be reduced
     */
    public void reduceMana(int value) {
        mana -= value;
    }
    
    /**
     * Get the player position
     * @return player position
     */
    public Position getPosition() {
        return position;
    }
    
    /**
     * Moves the player to the new position
     * @param position the new position
     */
    public void moveToPosition(Position position) {
        this.position = position;
    }
    
    /**
     * Kills the player
     */
    public void dead() {
        this.isAlive = false;
    }
    
    /**
     * Checks if the player is alive
     * @return true if player is alive, false if not
     */
    public boolean isAlive() {
        return isAlive;
    }
        
    /**
     * The method for player level increase
     */
    public abstract void levelUP();
}
