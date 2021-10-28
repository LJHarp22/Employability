package rennegaderpg;

import java.io.*;
import java.util.*;

/**
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public final class Game {
    
    
    private Weapon [] weapons; //The game items
    private Occupant [] occupants; //The game occupants
    private final String account;
    private Player player;
    private Map map;
    
    /**
     *
     * @param account
     */
    public Game(String account) {
        this.account = account;
        this.player = null;
        this.map = null;
        this.weapons = null;
        this.occupants = null;
        setUpGame();
    }
    
    public void setUpGame() {
        //Set up the game
        setUpMap("0.txt");
        setUpItems();
        setUpPlayer();
        setUpOccupants("occupants1.txt");
    }
    
    public void setUpPlayer() {
        //Load player from file database
        try {
            Scanner input = new Scanner(new File(account + ".txt"));
            input.nextLine();
            String playerName = input.nextLine();
            int playerVoc = input.nextInt();
            int playerLevel = input.nextInt();
            int playerExperience = input.nextInt();
            int weapon = input.nextInt();
            if (playerVoc== 0) {
                player = new Sorcerer(playerName, playerLevel, playerExperience,
                         new Position(map, 1, 13),(Staff) weapons[weapon]);
            } else if (playerVoc == 1) {
                player = new Knight(playerName, playerLevel, playerExperience,
                         new Position(map, 1, 13),(Melee) weapons[weapon]);
            } else {
                player = new Paladin(playerName, playerLevel, playerExperience,
                         new Position(map, 1, 13),(Bow) weapons[weapon]);
            }
        } catch (FileNotFoundException e) {}
        
        map.updatePlayerPosition(player);
        map.getGridSquare(player.getPosition()).setVisible();
    }
    
    public void setUpMap(String fileName) {
        //Load and setup Map from file
        try {
            Scanner input = new Scanner(new File("components/" + fileName));
            input.useDelimiter("\\s*,\\s*");

            int numRows = input.nextInt();
            int numCols = input.nextInt();
            map = new Map(numCols, numRows);

            setUpTerrain(input);

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find data file '" + fileName + "'");
        }
        
    }
    
    public void setUpTerrain(Scanner input) {
        
        for (int row = 0; row < map.getRows(); row++) {
            String terrainRow = input.next();
            for (int col = 0; col < terrainRow.length(); col++) {
                Position pos = new Position(map, row, col);
                String terrainString = terrainRow.substring(col, col + 1);
                Terrain terrain = Terrain.getTerrainFromStringRep(terrainString);
                map.getGridSquare(pos).setTerrain(terrain);
            }
        }
    }
    
    public void setUpItems() {
        //Create Game Items
        weapons = new Weapon[]{
            new Melee("Dagger", "A sharp dagger", 8, 5),
            new Melee("Short Sword", "A deadly short sword", 15, 5),
            new Melee("Long Sword", "A deadly long sword", 22, 10),
            new Melee("Fire Sword", "The swords flame burns bright", 30, 15),
            new Melee("Giant Sword", "An enormous cunning blade", 35, 10),
            new Melee("Excalibur", "King Arthur's lost blade", 40, 20),
            new Bow("Longbow", "A long bow", 15),
            new Bow("Hornbow", "A horn bow", 18),
            new Bow("Heavy Crossbow", "A crossbow", 25),
            new Bow("Yew Bow", "A featherlight bow", 32),
            new Bow("Composite Hornbow", "A heavy bow", 38),
            new Bow("Rennegade LongBow", "The lost King's bow", 43),
            new Staff("Monkey King Bar Staff", "A long staff", 15, 5, 2),
            new Staff("Staff Of Wizardry", "A great staff", 20, 10, 5),
            new Staff("Wand Of Dragonbreath", "It's on fire", 28, 10, 8),
            new Staff("Wand Of Destruction", "A wand of destruction", 30, 10, 10),
            new Staff("Staff Of Oblivion", "The staff screams for death", 30, 12, 12),
            new Staff("Merlin's Staff", "Merlin's lost staff", 32, 15, 15)
        };
    }
    
    public void setUpOccupants(String occ) {
        //Set up occupants from file
        try {
            Scanner input = new Scanner(new File("components/" + occ));
            input.useDelimiter("\\s*,\\s*");
            
            Random r = new Random();
            int npc = 0;
            if(occ.equals("occupants1.txt")) 
                npc = 1;
            
            occupants = new Occupant[input.nextInt()+npc];
            for(int i = 0; i < occupants.length; ++i) {
                if (i == occupants.length - 1 && npc == 1) {
                    occupants[i] = new NPC("Alf", new Position(map, 0, 13));
                    map.getGridSquare(new Position(map, 0, 13)).setOccupant(occupants[i]);
                } else {
                    String name = input.next();
                    Position pos = new Position(map, input.nextInt(), input.nextInt());
                    int attack = input.nextInt();
                    int experience = input.nextInt();
                    int health = input.nextInt();
                    occupants[i] = new Creature(name, pos, attack, experience, health);
                    map.getGridSquare(pos).setOccupant(occupants[i]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find data file");
        }
    }
    
    //Extra Methods
    public Map getCurrentMap() {
        return map;
    }
    
    public String getAccount() {
        return account;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public GridSquare getCurrentGridSquare() {
        return map.getGridSquare(player.getPosition());
    }
    
    public void movePlayer(MoveDirection move) {
        Position playerPos = player.getPosition();
        Position newPos = playerPos.getNewPosition(move);
        if (newPos != null) {
            player.moveToPosition(newPos);
            map.updatePlayerPosition(player);
        }
    }
    
    public Weapon getWeapon(int weapon) {
        return weapons[weapon];
    }
}
