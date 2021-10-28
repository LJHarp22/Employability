package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Paladin extends Player {
    
    private Bow weapon;
    
    /**
     * Constructs a Paladin object, inherits Player class
     * @param name the name of the player
     * @param level the level of the player
     * @param experience the experience of the player
     * @param position the position of the player
     * @param weapon the weapon of the player
     */
    public Paladin(String name, int level, int experience, Position position, Bow weapon) {
        super(name,level,experience, position);
        this.weapon = weapon;
        // Sets player Attack, Defence, Magic values based on the player level
        increaseAttack(weapon.getAttack());
        increaseAttack(12 + ((level - 1) * 4));
        increaseDefence(5 + ((level - 1) * 2));
        increaseMagic(3 + ((level - 1) * 2));
    }
    
    /**
     * Set the player weapon
     * @param wep the new Weapon
     */
    public void setWeapon(Bow wep) {
        //Adjust player stats
        int difference = wep.getAttack() - weapon.getAttack();
        if(difference > 0)
            increaseAttack(difference);
        else
            decreaseAttack(difference);
        this.weapon = wep;
    }
    
    /**
     * Get the weapon of the player
     * @return the player's weapon
     */
    public Bow getWeapon() {
        return weapon;
    }

    /**
     * Increases player skills on leveling up
     */
    @Override
    public void levelUP() {
        increaseAttack(4);
        increaseDefence(2);
        increaseMagic(2);
    }
}
