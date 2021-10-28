package RennegadeGUI;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import rennegaderpg.*;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public final class RennegadeGUI extends javax.swing.JFrame {

    private final Game game;
    private final Player player;
    private int moves; // Monitors player movement for monster respawns
    private ArrayList <Occupant> respawns; //Controls monster respawns
    
    /**
     * Creates a new JFrame for Rennegade RPG.
     * 
     * @param game the game object to display in this frame
     */
    public RennegadeGUI(Game game) {
        this.setPreferredSize(new Dimension(1250,700));
        this.game = game;
        this.player = game.getPlayer();
        this.moves = 0;
        this.respawns = new ArrayList <>();
        initComponents();
        
        createGridPanels();
        update();
        
        name2.setText(game.getPlayer().getName());
        if(game.getPlayer() instanceof Sorcerer) 
            vocation2.setText("Sorcerer");
        if(game.getPlayer() instanceof Knight) 
            vocation2.setText("Knight");
        if(game.getPlayer() instanceof Paladin) 
            vocation2.setText("Paladin");
        JOptionPane.showMessageDialog(null, "Welcome to Rennegade Isle!" + 
                            "\nBe sure to talk with Alf the blacksmith.");
    }
    
    /**
     * Creates the game grid square panels
     */
    public void createGridPanels() {
        int rows = game.getCurrentMap().getRows();
        int cols = game.getCurrentMap().getCols();
        
        pnlgrid.removeAll();
        pnlgrid.setLayout(new GridLayout(rows, cols));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                GridSquarePanel panel = new GridSquarePanel(game, row, col);
                pnlgrid.add(panel);
            }
        }      
    }
    
    /**
     * Creature attack method
     * @param mob the creature object attacking the player
     */
    public void counterAttack(Creature mob) {
        // Damage calculator
        Random di = new Random();
        int multiplier = di.nextInt(6);
        // Paladin specialised as distance fighter, with increased chance to dodge an attack
        if(player instanceof Paladin && multiplier == 1) 
                multiplier -= 1;
        
        if (multiplier > 0) {
            multiplier = (multiplier * mob.getAttack())
                    / (int) (player.getDefence() / 2);
            player.reduceHealth(multiplier);
            jTextArea1.setText(jTextArea1.getText() + "\nPlayer lost " + multiplier + " Hitpoints");
        }
    }
    
    /**
     * Updates the state of the UI based on the state of the game.
     */
    public void update() { 
        // Slain monster
        if(game.getCurrentGridSquare().getOccupant() instanceof Creature) {
            Creature creep = (Creature) game.getCurrentGridSquare().getOccupant();
            if(!creep.isAlive()) {
                // Player update
                player.increaseExperience(creep.getExperience());
                player.resetHealth();
                // Respawns
                creep.setTime(moves + 10);
                respawns.add(creep);
                game.getCurrentGridSquare().setOccupant(null);
                log.setText(log.getText() + "\n> You gained " + creep.getExperience() + " experience points");
            }
        }
        
        // Respawns
        for(Occupant occ : respawns) {
            Creature creep = (Creature) occ;
            if(creep.getTime() == moves) {
                creep.setTime(0);
                creep.alive();
                creep.resetHealth();
                game.getCurrentMap().getGridSquare(creep.getPosition()).setOccupant(occ);
            }
        }
        
        // Player died, Game exit
        if(player.getHealth() <= 0)
            player.dead();
        if(!player.isAlive()) {
            JOptionPane.showMessageDialog(null, "YOU DIED!");
            System.exit(0);
        }
        
        //Level & Stat increase
        int previousLevel = player.getLevel();
        int playerLevel = 1;
        int [] levels = new int [] {100, 200, 400, 1000, 2400, 4800, 9600, 18000,
            32000, 52000, 75000, 102000, 135000, 170000, 210000, 265000, 337500,
        400000, 500000};
        for(int i = 0; i < levels.length; ++i) {
            if(player.getExperience() >= levels[i]) 
                playerLevel++;
        }
        player.setLevel(playerLevel);
        while(previousLevel != player.getLevel()) {
            player.levelUP();
            ++previousLevel;
            if(previousLevel == player.getLevel())
                log.setText(log.getText() + "\n> Congratulations ** You Reached Level " + player.getLevel());
        }
        
        // UI GridSquarePanel Updates
        for(Component component: pnlgrid.getComponents()) {
            GridSquarePanel gsp = (GridSquarePanel) component;
            gsp.update();
        }

        // Disable & enable move action buttons
        int row = player.getPosition().getRow();
        int col = player.getPosition().getCol();
        if(row == 0 || game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row -1, col)).getTerrain() == Terrain.WATER ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row -1, col)).getTerrain() == Terrain.TREE ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row -1, col)).getTerrain() == Terrain.ROCK){
           north.setEnabled(false);
        }
        else
            north.setEnabled(true);
        if(row == 14 || game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row +1, col)).getTerrain() == Terrain.WATER ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row +1, col)).getTerrain() == Terrain.TREE ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row +1, col)).getTerrain() == Terrain.ROCK) {
           south.setEnabled(false);
        }
        else 
            south.setEnabled(true);
        if(col == 0 || game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row, col - 1)).getTerrain() == Terrain.WATER ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row, col - 1)).getTerrain() == Terrain.TREE ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row, col - 1)).getTerrain() == Terrain.ROCK) {
           west.setEnabled(false);
        }
        else 
            west.setEnabled(true);
        if(col == 14 || game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row, col + 1)).getTerrain() == Terrain.WATER ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row, col + 1)).getTerrain() == Terrain.TREE ||
           game.getCurrentMap().getGridSquare(new Position(game.getCurrentMap(), row, col + 1)).getTerrain() == Terrain.ROCK) {
           east.setEnabled(false);
        }
        else 
            east.setEnabled(true);
        
        // Player information & detail update
        hp.setValue(player.getHealth());
        hp.setString(player.getHealth()+"%");
        mp.setValue(player.getMana());
        mp.setString(player.getMana()+"%");
        hp2.setValue(player.getHealth());
        hp2.setString(player.getHealth()+"%");
        mp2.setValue(player.getMana());
        mp2.setString(player.getMana()+"%");
        level2.setText(Integer.toString(player.getLevel()));
        experience2.setText(Integer.toString(player.getExperience()));
        location2.setText("X: " + player.getPosition().getCol() + " || Y: " + player.getPosition().getRow());
        mlvl2.setText(Integer.toString(player.getMagic()));
        fight2.setText(Integer.toString(player.getAttack()));
        def2.setText(Integer.toString(player.getDefence()));
        if(player instanceof Sorcerer) {
            Sorcerer p = (Sorcerer) player;
            wep2.setText(p.getWeapon().getName());
        }
        if(player instanceof Paladin) {
            Paladin p = (Paladin) player;
            wep2.setText(p.getWeapon().getName());
        }
        if(player instanceof Knight) {
            Knight p = (Knight) player;
            wep2.setText(p.getWeapon().getName());
        }
        
        // Combat JPanel updating
        if(player.getHealth() == 100) 
            heal.setEnabled(false);
        else
            heal.setEnabled(true);
        
        if (game.getCurrentGridSquare().getOccupant() instanceof Creature) {
            Creature mob = (Creature) game.getCurrentGridSquare().getOccupant();
            
            hp3.setMaximum(mob.getMaxHealth());
            hp3.setValue(mob.getHealth());
            // Player Attack option visibility
            if (player.getLevel() >= 6) {
                magicattack.setEnabled(true);
            } else {
                magicattack.setEnabled(false);
            }
            if (player.getLevel() >= 10) {
                specialattack.setEnabled(true);
            } else {
                specialattack.setEnabled(false);
            }
        }
        else {
            // Reset field values to default
            mob2.setText("");
            mobatk2.setText("");
            mobhp2.setText("");
            mobxp2.setText("");
            jPanel2.setVisible(true);
            jPanel1.setVisible(false);
            jTextArea1.setText(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlgrid = new javax.swing.JPanel();
        details = new javax.swing.JPanel();
        north = new javax.swing.JButton();
        south = new javax.swing.JButton();
        east = new javax.swing.JButton();
        west = new javax.swing.JButton();
        hp = new javax.swing.JProgressBar();
        mp = new javax.swing.JProgressBar();
        name = new javax.swing.JLabel();
        vocation = new javax.swing.JLabel();
        location = new javax.swing.JLabel();
        level = new javax.swing.JLabel();
        experience = new javax.swing.JLabel();
        name2 = new javax.swing.JLabel();
        vocation2 = new javax.swing.JLabel();
        location2 = new javax.swing.JLabel();
        experience2 = new javax.swing.JLabel();
        level2 = new javax.swing.JLabel();
        actionbar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fight1 = new javax.swing.JLabel();
        def1 = new javax.swing.JLabel();
        wep2 = new javax.swing.JLabel();
        fight2 = new javax.swing.JLabel();
        def2 = new javax.swing.JLabel();
        wep1 = new javax.swing.JLabel();
        Inspect = new javax.swing.JButton();
        mobhp = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mob = new javax.swing.JLabel();
        mobatk = new javax.swing.JLabel();
        attack = new javax.swing.JButton();
        mobhp2 = new javax.swing.JLabel();
        mob2 = new javax.swing.JLabel();
        mobatk2 = new javax.swing.JLabel();
        mobxp1 = new javax.swing.JLabel();
        mobxp2 = new javax.swing.JLabel();
        mlvl1 = new javax.swing.JLabel();
        mlvl2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        meleeattack = new javax.swing.JButton();
        magicattack = new javax.swing.JButton();
        specialattack = new javax.swing.JButton();
        heal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        hp2 = new javax.swing.JProgressBar();
        mp2 = new javax.swing.JProgressBar();
        hp3 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        console = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        client = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1250, 700));
        getContentPane().setLayout(null);

        pnlgrid.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlgrid.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(pnlgrid);
        pnlgrid.setBounds(30, 30, 690, 400);

        details.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        details.setLayout(null);

        north.setText("North");
        north.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northActionPerformed(evt);
            }
        });
        details.add(north);
        north.setBounds(60, 20, 60, 24);

        south.setText("South");
        south.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southActionPerformed(evt);
            }
        });
        details.add(south);
        south.setBounds(60, 50, 60, 24);

        east.setText("East");
        east.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastActionPerformed(evt);
            }
        });
        details.add(east);
        east.setBounds(110, 30, 60, 24);

        west.setText("West");
        west.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westActionPerformed(evt);
            }
        });
        details.add(west);
        west.setBounds(10, 30, 60, 24);

        hp.setStringPainted(true);
        details.add(hp);
        hp.setBounds(200, 20, 240, 19);

        mp.setStringPainted(true);
        details.add(mp);
        mp.setBounds(200, 50, 240, 19);

        name.setText("Player Name :");
        details.add(name);
        name.setBounds(10, 100, 120, 14);

        vocation.setText("Player Vocation :");
        details.add(vocation);
        vocation.setBounds(10, 120, 120, 14);

        location.setText("Player Location :");
        details.add(location);
        location.setBounds(10, 140, 120, 14);

        level.setText("Player Level :");
        details.add(level);
        level.setBounds(10, 160, 120, 14);

        experience.setText("Player Experience :");
        details.add(experience);
        experience.setBounds(10, 180, 170, 14);
        details.add(name2);
        name2.setBounds(200, 100, 240, 20);
        details.add(vocation2);
        vocation2.setBounds(200, 120, 240, 20);
        details.add(location2);
        location2.setBounds(200, 140, 240, 20);
        details.add(experience2);
        experience2.setBounds(200, 180, 240, 20);
        details.add(level2);
        level2.setBounds(200, 160, 240, 20);

        getContentPane().add(details);
        details.setBounds(740, 30, 470, 210);

        actionbar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        actionbar.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        fight1.setText("Fighting Skill :");
        jPanel2.add(fight1);
        fight1.setBounds(10, 70, 100, 14);

        def1.setText("Defensive Rating :");
        jPanel2.add(def1);
        def1.setBounds(10, 100, 170, 14);

        wep2.setPreferredSize(new java.awt.Dimension(290, 16));
        jPanel2.add(wep2);
        wep2.setBounds(160, 40, 290, 20);

        fight2.setPreferredSize(new java.awt.Dimension(290, 16));
        fight2.setSize(new java.awt.Dimension(290, 16));
        jPanel2.add(fight2);
        fight2.setBounds(160, 70, 290, 16);

        def2.setPreferredSize(new java.awt.Dimension(290, 16));
        def2.setSize(new java.awt.Dimension(290, 16));
        jPanel2.add(def2);
        def2.setBounds(160, 100, 290, 16);

        wep1.setText("Weapon :");
        jPanel2.add(wep1);
        wep1.setBounds(10, 40, 120, 14);

        Inspect.setText("Inspect");
        Inspect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InspectActionPerformed(evt);
            }
        });
        jPanel2.add(Inspect);
        Inspect.setBounds(120, 170, 210, 40);

        mobhp.setText("Health :");
        jPanel2.add(mobhp);
        mobhp.setBounds(10, 350, 120, 14);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Creature Details");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(160, 220, 120, 30);

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Player Details");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(170, 10, 120, 30);

        mob.setText("Name :");
        jPanel2.add(mob);
        mob.setBounds(10, 260, 130, 14);

        mobatk.setText("Attack :");
        jPanel2.add(mobatk);
        mobatk.setBounds(10, 290, 120, 14);

        attack.setText("ATTACK!");
        attack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackActionPerformed(evt);
            }
        });
        jPanel2.add(attack);
        attack.setBounds(320, 280, 140, 60);
        jPanel2.add(mobhp2);
        mobhp2.setBounds(100, 350, 210, 20);
        jPanel2.add(mob2);
        mob2.setBounds(100, 260, 210, 20);
        jPanel2.add(mobatk2);
        mobatk2.setBounds(100, 290, 210, 20);

        mobxp1.setText("Experience :");
        jPanel2.add(mobxp1);
        mobxp1.setBounds(10, 320, 120, 14);
        jPanel2.add(mobxp2);
        mobxp2.setBounds(100, 320, 210, 20);

        mlvl1.setText("Magic Level :");
        jPanel2.add(mlvl1);
        mlvl1.setBounds(10, 130, 150, 14);

        mlvl2.setPreferredSize(new java.awt.Dimension(290, 16));
        mlvl2.setSize(new java.awt.Dimension(290, 16));
        jPanel2.add(mlvl2);
        mlvl2.setBounds(160, 130, 290, 16);

        actionbar.add(jPanel2);
        jPanel2.setBounds(0, 0, 470, 380);

        jPanel1.setLayout(null);

        meleeattack.setText("Melee Attack");
        meleeattack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meleeattackActionPerformed(evt);
            }
        });
        jPanel1.add(meleeattack);
        meleeattack.setBounds(320, 20, 130, 24);

        magicattack.setText("Magic Attack");
        magicattack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                magicattackActionPerformed(evt);
            }
        });
        jPanel1.add(magicattack);
        magicattack.setBounds(320, 60, 130, 24);

        specialattack.setText("Special Attack");
        specialattack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specialattackActionPerformed(evt);
            }
        });
        jPanel1.add(specialattack);
        specialattack.setBounds(320, 100, 130, 24);

        heal.setText("Heal");
        heal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                healActionPerformed(evt);
            }
        });
        jPanel1.add(heal);
        heal.setBounds(320, 140, 130, 24);

        jLabel1.setText("Player Health :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 210, 160, 14);

        jLabel2.setText("Player Mana :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 250, 90, 14);

        jLabel3.setText("Creature Health :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 290, 120, 14);

        hp2.setStringPainted(true);
        jPanel1.add(hp2);
        hp2.setBounds(200, 210, 250, 19);

        mp2.setStringPainted(true);
        jPanel1.add(mp2);
        mp2.setBounds(200, 250, 250, 19);

        hp3.setStringPainted(true);
        jPanel1.add(hp3);
        hp3.setBounds(200, 290, 250, 19);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 290, 150);

        actionbar.add(jPanel1);
        jPanel1.setBounds(0, 0, 470, 380);

        getContentPane().add(actionbar);
        actionbar.setBounds(740, 260, 470, 380);

        console.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        console.setLayout(null);

        save.setText("Save Game");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        console.add(save);
        save.setBounds(190, 140, 91, 24);

        exit.setText("Exit Game");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        console.add(exit);
        exit.setBounds(380, 140, 110, 24);

        log.setColumns(20);
        log.setRows(5);
        jScrollPane2.setViewportView(log);

        console.add(jScrollPane2);
        jScrollPane2.setBounds(30, 50, 620, 80);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Console Log");
        console.add(jLabel4);
        jLabel4.setBounds(300, 10, 120, 30);

        getContentPane().add(console);
        console.setBounds(30, 450, 690, 190);

        client.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RennegadeGUI/client.png"))); // NOI18N
        client.setPreferredSize(new java.awt.Dimension(1250, 700));
        client.setSize(new java.awt.Dimension(1250, 700));
        getContentPane().add(client);
        client.setBounds(0, 0, 1250, 700);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void eastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eastActionPerformed
        // Move player east, and update UI
        game.movePlayer(MoveDirection.EAST);
        player.increaseMana();
        ++moves;
        update();
    }//GEN-LAST:event_eastActionPerformed

    private void southActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_southActionPerformed
        // Move player south, and update UI
        game.movePlayer(MoveDirection.SOUTH);
        player.increaseMana();
        ++moves;
        update();
    }//GEN-LAST:event_southActionPerformed

    private void westActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_westActionPerformed
        // Move player west, and update UI
        game.movePlayer(MoveDirection.WEST);
        player.increaseMana();
        ++moves;
        update();
    }//GEN-LAST:event_westActionPerformed

    private void northActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_northActionPerformed
        // Move player east, and update UI
        game.movePlayer(MoveDirection.NORTH);
        player.increaseMana();
        ++moves;
        update();
    }//GEN-LAST:event_northActionPerformed

    private void meleeattackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meleeattackActionPerformed
        //Deal melee damage, based on player stats
        int damage = 0;
        Random di = new Random();
        Creature creep = (Creature) game.getCurrentGridSquare().getOccupant();

        damage = (player.getAttack() * di.nextInt(6)) / 10;
        creep.reduceHealth(damage);

        jTextArea1.setText(jTextArea1.getText() + "\nPlayer did " + damage + " hitpoints in damage to " + creep.getName());
        
        // Monster counter attack if alive
        if (creep.getHealth() > 0) 
            counterAttack(creep);
        else
            creep.dead();
        // Update game UI
        update();
    }//GEN-LAST:event_meleeattackActionPerformed

    private void magicattackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_magicattackActionPerformed
        int damage = 0;
        boolean success = false;
        Random di = new Random();
        Creature creep = (Creature) game.getCurrentGridSquare().getOccupant();
        // Damage multiplier and calculator
        // At the cost of Mana the player can make a magic attack.
        if(player instanceof Sorcerer && player.getMana() >= 10) {
            player.reduceMana(10);
            damage = player.getMagic();
            jTextArea1.setText(jTextArea1.getText() + "\nFireblast did " + damage + " hitpoints in damage");
            success = true;
        }
        if(player instanceof Paladin && player.getMana() >= 15) {
            player.reduceMana(15);
            while(damage < 3)
                damage = di.nextInt(6);
            damage = (player.getAttack() * damage) / 6;
            jTextArea1.setText(jTextArea1.getText() + "\nCritical hit! Player did " + damage + " hitpoints in damage");
            success = true;
        }
        if(player instanceof Knight && player.getMana() >= 20) {
            player.reduceMana(20);
            damage = (player.getAttack() + player.getMagic() * 2) - di.nextInt(9);
            jTextArea1.setText(jTextArea1.getText() + "\nSkull crusher did " + damage + " hitpoints in damage");
            success = true;
        }
        creep.reduceHealth(damage);
        // Player has insufficient mana & Creature counter attack if knight or sorcerer
        if(!success)
            jTextArea1.setText( jTextArea1.getText() + "\nInsufficient Mana.");
        else 
            if (player instanceof Sorcerer || player instanceof Knight && creep.getHealth() > 0)
                counterAttack(creep);
        if(creep.getHealth() <= 0)
            creep.dead();
        // Update game UI
        update();
    }//GEN-LAST:event_magicattackActionPerformed

    private void specialattackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialattackActionPerformed
        int damage = 0;
        boolean success = false;
        Random di = new Random();
        Creature creep = (Creature) game.getCurrentGridSquare().getOccupant();
        // Special attack multiplier and calculator
        // Player needs sufficient mana. 
        // Damage is calculated based on player stats and a random int generator
        if(player instanceof Sorcerer && player.getMana() >= 60) {
            player.reduceMana(60);
            while(damage < 6) 
                damage = di.nextInt(10);
            damage = damage * player.getMagic();
            jTextArea1.setText(jTextArea1.getText() + "\nUltimate Explosion did " + damage + " hitpoints in damage");
            creep.reduceHealth(damage);
            success = true;
        }
        if(player instanceof Paladin && player.getMana() >= 40) {
            player.reduceMana(40);
            jTextArea1.setText(jTextArea1.getText() + "\n** Rapid Fire **");
            int n = 0;
            // Rapid fire, Fires 3 magic arrows
            while (n < 3) {
                damage = 0;
                while (damage < 4) {
                    damage = di.nextInt(6);
                }
                damage = (player.getAttack() * damage) / 6 + player.getMagic();
                creep.reduceHealth(damage);
                jTextArea1.setText(jTextArea1.getText() + "\nPlayer did " + damage + " hitpoints in damage");
                ++n;
            }
            success = true;
        }
        if(player instanceof Knight && player.getMana() >= 60) {
            player.reduceMana(60);
            while(damage < 5) 
                damage = di.nextInt(8);
            damage = damage * (player.getMagic() + player.getAttack());
            creep.reduceHealth(damage);
            jTextArea1.setText(jTextArea1.getText() + "\nBerserk did " + damage + " hitpoints in damage");
            success = true;
        }
        // Insufficient mana, and Creature counter attack if alive
        if(!success)
            jTextArea1.setText( jTextArea1.getText() + "\nInsufficient Mana.");
        else
            if(creep.getHealth() > 0)
                counterAttack(creep);
            else
                creep.dead();
        // Update game UI
        update();
    }//GEN-LAST:event_specialattackActionPerformed

    private void healActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_healActionPerformed
        // Player Heal method
        int current = player.getHealth();
        boolean success = false;
        // Player must have sufficient mana, if so health is reset to player max health of 100
        if (player instanceof Sorcerer
                && player.getMana() >= 10) {
            player.resetHealth();
            player.reduceMana(10);
            success = true;
        } else if (player instanceof Knight
                && player.getMana() >= 20) {
            player.resetHealth();
            player.reduceMana(20);
            success = true;
        } else if (player instanceof Paladin
                && player.getMana() >= 15) {
            player.resetHealth();
            player.reduceMana(15);
            success = true;
        } else {
            jTextArea1.setText(jTextArea1.getText() + "\nInsufficient Mana.");
        }
        if (success) {
            jTextArea1.setText(jTextArea1.getText() + "\nPlayer Healed " + ((int) 100 - current) + " Hitpoints.");
        }
        // Update game UI
        update();
    }//GEN-LAST:event_healActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // Quit game
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void attackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackActionPerformed
        // Attack Creature , Panel visibility
        if (game.getCurrentGridSquare().getOccupant() instanceof Creature) {
            jPanel2.setVisible(false);
            jPanel1.setVisible(true);
            Creature creep = (Creature) game.getCurrentGridSquare().getOccupant();
            // Creature always has full health at the beginning of the fight. 
            // Update game UI
            creep.resetHealth();
            update();
        }
    }//GEN-LAST:event_attackActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // Save player game
        try {
            // Get player password, charName, and default weapon for save file
            Scanner input = new Scanner(new File(game.getAccount() + ".txt"));
            String password = input.nextLine();
            String charName = input.nextLine();
            input.nextInt();
            input.nextInt();
            input.nextInt();
            int weapon = input.nextInt();
            // Save and update file database
            BufferedWriter out = new BufferedWriter(new FileWriter(game.getAccount() + ".txt"));
            int voc = 0;
            if(player instanceof Knight)
                voc = 1;
            if(player instanceof Paladin)
                voc = 2;
            out.write(password + "\n");
            out.write(charName + "\n");
            out.write(voc + "\n");
            out.write(player.getLevel() + "\n");
            out.write(player.getExperience() + "\n");
            out.write(weapon + "\n");
            out.close();
            log.setText(log.getText() + "\n> Game Saved");
        } catch (IOException ex) {}
    }//GEN-LAST:event_saveActionPerformed

    private void InspectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InspectActionPerformed
        // Inspect at Gridsquare at player's position
        String message = "";
        // If a creep is occupies the GridSquare update Creature UI details
        // Else inspect the terrain at the GridSquare and get the terrain description
        if (game.getCurrentGridSquare().getOccupant() instanceof Creature) {
            Creature creep = (Creature) game.getCurrentGridSquare().getOccupant();
            mob2.setText(creep.getName());
            mobatk2.setText(Integer.toString(creep.getAttack()));
            mobxp2.setText(Integer.toString(creep.getExperience()));
            mobhp2.setText(Integer.toString(creep.getHealth()));
            message = "You see a " + creep.getName();
        }
        else
            message = "You see " + game.getCurrentGridSquare().getTerrain().getDescription();
        // Cave map! (EXTENSION)
        // The player must be level 10 to enter the cave
        if (game.getCurrentGridSquare().getTerrain() == Terrain.CAVE
                && game.getCurrentGridSquare().getOccupant() == null) {
            if (player.getLevel() >= 10) {
                // Set up new map, and create/update new gridpanels
                game.setUpMap("1.txt");
                createGridPanels();
                // Move player to new map, and set up the new occupants
                player.moveToPosition(new Position(game.getCurrentMap(), 0, 0));
                game.getCurrentMap().updatePlayerPosition(player);
                game.getCurrentGridSquare().setVisible();
                game.setUpOccupants("occupants2.txt");
                // Clear respawn list so as not to interfere with occupant spawning
                respawns = new ArrayList<>();
            }
            else
                message = "You hear a deep rumbling beneath you.. You dare not enter the cave";
        }
        // Weapon advancements and tracking
        // The player can visit Alf the NPC every few levels to receive a weapon upgrade
        if (game.getCurrentGridSquare().getOccupant() instanceof NPC) {
            Weapon weapon = null;
            // Upgrade player weapons 
            if (player.getLevel() >= 18) {
                if (player instanceof Sorcerer) {
                    Sorcerer play = (Sorcerer) player;
                    play.setWeapon((Staff) game.getWeapon(17));
                    weapon = (Weapon) game.getWeapon(17);
                }
                if (player instanceof Knight) {
                    Knight play = (Knight) player;
                    play.setWeapon((Melee) game.getWeapon(5));
                    weapon = (Weapon) game.getWeapon(5);
                }
                if (player instanceof Paladin) {
                    Paladin play = (Paladin) player;
                    play.setWeapon((Bow) game.getWeapon(11));
                    weapon = (Weapon) game.getWeapon(11);
                }
            } else if (player.getLevel() >= 15) {
                if (player instanceof Sorcerer) {
                    Sorcerer play = (Sorcerer) player;
                    play.setWeapon((Staff) game.getWeapon(16));
                    weapon = (Weapon) game.getWeapon(16);
                }
                if (player instanceof Knight) {
                    Knight play = (Knight) player;
                    play.setWeapon((Melee) game.getWeapon(4));
                    weapon = (Weapon) game.getWeapon(4);
                }
                if (player instanceof Paladin) {
                    Paladin play = (Paladin) player;
                    play.setWeapon((Bow) game.getWeapon(10));
                    weapon = (Weapon) game.getWeapon(10);
                }
            } else if (player.getLevel() >= 12) {
                if (player instanceof Sorcerer) {
                    Sorcerer play = (Sorcerer) player;
                    play.setWeapon((Staff) game.getWeapon(15));
                    weapon = (Weapon) game.getWeapon(15);
                }
                if (player instanceof Knight) {
                    Knight play = (Knight) player;
                    play.setWeapon((Melee) game.getWeapon(3));
                    weapon = (Weapon) game.getWeapon(3);
                }
                if (player instanceof Paladin) {
                    Paladin play = (Paladin) player;
                    play.setWeapon((Bow) game.getWeapon(9));
                    weapon = (Weapon) game.getWeapon(9);
                }
            } else if (player.getLevel() >= 8) {
                if (player instanceof Sorcerer) {
                    Sorcerer play = (Sorcerer) player;
                    play.setWeapon((Staff) game.getWeapon(14));
                    weapon = (Weapon) game.getWeapon(14);
                }
                if (player instanceof Knight) {
                    Knight play = (Knight) player;
                    play.setWeapon((Melee) game.getWeapon(2));
                    weapon = (Weapon) game.getWeapon(2);
                }
                if (player instanceof Paladin) {
                    Paladin play = (Paladin) player;
                    play.setWeapon((Bow) game.getWeapon(8));
                    weapon = (Weapon) game.getWeapon(8);
                }

            } else if (player.getLevel() >= 3) {
                if (player instanceof Sorcerer) {
                    Sorcerer play = (Sorcerer) player;
                    play.setWeapon((Staff) game.getWeapon(13));
                    weapon = (Weapon) game.getWeapon(13);
                }
                if (player instanceof Knight) {
                    Knight play = (Knight) player;
                    play.setWeapon((Melee) game.getWeapon(1));
                    weapon = (Weapon) game.getWeapon(1);
                }
                if (player instanceof Paladin) {
                    Paladin play = (Paladin) player;
                    play.setWeapon((Bow) game.getWeapon(7));
                    weapon = (Weapon) game.getWeapon(7);
                }

            } else 
                // Player already had that weapon
                message = "You Already have that weapon";
            // Weapon was successfully upgraded, Display notification and information to the player
            if(weapon != null && !weapon.getName().equals(wep2.getText())) {
                JOptionPane.showMessageDialog(null, "You received " + weapon.getName());
                
                message = "You received a " + weapon.getName() + "\n> You see " + weapon.getDescription() + " (ATK: ";
                if(weapon instanceof Staff) {
                    Staff wep = (Staff) weapon;
                    message += wep.getAttack() + " DEF: " + wep.getDefence() + " MLVL: " + wep.getMagic() + ")";
                }
                if(weapon instanceof Bow) {
                    Bow wep = (Bow) weapon;
                    message += wep.getAttack() + ")";
                }
                if(weapon instanceof Melee) {
                    Melee wep = (Melee) weapon;
                    message += wep.getAttack() + " DEF: " + wep.getDefence() + ")";
                }
            }
        }
        // Update game UI
        log.setText(log.getText() + "\n> " + message);
        update();
    }//GEN-LAST:event_InspectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Inspect;
    private javax.swing.JPanel actionbar;
    private javax.swing.JButton attack;
    private javax.swing.JLabel client;
    private javax.swing.JPanel console;
    private javax.swing.JLabel def1;
    private javax.swing.JLabel def2;
    private javax.swing.JPanel details;
    private javax.swing.JButton east;
    private javax.swing.JButton exit;
    private javax.swing.JLabel experience;
    private javax.swing.JLabel experience2;
    private javax.swing.JLabel fight1;
    private javax.swing.JLabel fight2;
    private javax.swing.JButton heal;
    private javax.swing.JProgressBar hp;
    private javax.swing.JProgressBar hp2;
    private javax.swing.JProgressBar hp3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel level;
    private javax.swing.JLabel level2;
    private javax.swing.JLabel location;
    private javax.swing.JLabel location2;
    private javax.swing.JTextArea log;
    private javax.swing.JButton magicattack;
    private javax.swing.JButton meleeattack;
    private javax.swing.JLabel mlvl1;
    private javax.swing.JLabel mlvl2;
    private javax.swing.JLabel mob;
    private javax.swing.JLabel mob2;
    private javax.swing.JLabel mobatk;
    private javax.swing.JLabel mobatk2;
    private javax.swing.JLabel mobhp;
    private javax.swing.JLabel mobhp2;
    private javax.swing.JLabel mobxp1;
    private javax.swing.JLabel mobxp2;
    private javax.swing.JProgressBar mp;
    private javax.swing.JProgressBar mp2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name2;
    private javax.swing.JButton north;
    private javax.swing.JPanel pnlgrid;
    private javax.swing.JButton save;
    private javax.swing.JButton south;
    private javax.swing.JButton specialattack;
    private javax.swing.JLabel vocation;
    private javax.swing.JLabel vocation2;
    private javax.swing.JLabel wep1;
    private javax.swing.JLabel wep2;
    private javax.swing.JButton west;
    // End of variables declaration//GEN-END:variables
}
