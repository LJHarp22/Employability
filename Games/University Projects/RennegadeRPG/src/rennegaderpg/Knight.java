package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Knight extends Player {
    
    private Melee weapon;
    
    /**
     * Constructs a Knight object, inherits Player class
     * @param name the name of the player
     * @param level the level of the player
     * @param experience the experience of the player
     * @param position the position of the player
     * @param weapon the weapon of the player
     */
    public Knight(String name, int level, int experience, Position position, Melee weapon) {
        super(name,level,experience,position);
        this.weapon = weapon;
        // Sets player Attack, Defence, Magic values based on the player level
        increaseAttack(weapon.getAttack());
        increaseDefence(weapon.getDefence());
        increaseAttack(8 + ((level - 1) * 3));
        increaseDefence(10 + ((level - 1) * 3));
        increaseMagic(2 + ((level - 1) * 2));
    }
    
    /**
     * Get the weapon of the player
     * @return the player's weapon
     */
    public Melee getWeapon() {
        return weapon;
    }
    
    /**
     * Set the player weapon
     * @param wep the new Weapon
     */
    public void setWeapon(Melee wep) {
        //Adjust player stats
        int difference = wep.getAttack() - weapon.getAttack();
        if(difference != 0)
            increaseAttack(difference);
        difference = wep.getDefence() - weapon.getDefence();
        if(difference != 0)
            increaseDefence(difference);
        this.weapon = wep;
    }

    /**
     * Increases player skills on leveling up
     */
    @Override
    public void levelUP() {
        increaseAttack(3);
        increaseDefence(3);
        increaseMagic(2);       
    }
}
