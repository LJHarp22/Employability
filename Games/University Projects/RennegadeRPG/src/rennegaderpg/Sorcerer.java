package rennegaderpg;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Sorcerer extends Player{
    
    private Staff weapon;

    /**
     * Constructs an object of a Sorcerer, inherits Player class
     * @param name the name of the player
     * @param level the level of the player
     * @param experience the experience of the player
     * @param position the position of the player
     * @param weapon the weapon of the player
     */
    public Sorcerer(String name, int level, int experience, Position position, Staff weapon) {
        super(name,level,experience,position);
        this.weapon = weapon;
        // Sets player Attack, Defence, Magic values based on the player level
        increaseAttack(weapon.getAttack());
        increaseDefence(weapon.getDefence());
        increaseMagic(weapon.getMagic());
        increaseAttack(5 + ((level - 1) * 2));
        increaseDefence(5 + ((level - 1) * 2));
        increaseMagic(10 + ((level - 1) * 4));
    }
    
    /**
     * Get the weapon of the player
     * @return the player's weapon
     */
    public Staff getWeapon() {
        return weapon;
    }
    
    /**
     * Set the player weapon
     * @param wep the new Weapon
     */
    public void setWeapon(Staff wep) {
        //Adjust player stats
        int difference = wep.getAttack() - weapon.getAttack();
        if(difference != 0)
            increaseAttack(difference);

        difference = wep.getDefence() - weapon.getDefence();
        if(difference != 0)
            increaseDefence(difference);
        
        difference = wep.getMagic() - weapon.getMagic();
        if(difference != 0)
            increaseMagic(difference);
        this.weapon = wep;        
    }
    
    /**
     * Increases player skills on leveling up
     */
    @Override
    public void levelUP() {
        increaseAttack(2);
        increaseDefence(2);
        increaseMagic(4);
    }
}
