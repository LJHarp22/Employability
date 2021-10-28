package RennegadeGUI;

import java.awt.*;
import javax.swing.ImageIcon;
import rennegaderpg.*;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class GridSquarePanel extends javax.swing.JPanel {
    
    private final Game game;
    private final int rows, cols;
    
    /**
     * Constructs an object of Grid Square Panel
     * @param game the game object to display in this panel
     * @param rows the amount of rows on the game grid
     * @param cols the amount of columns on the game grid
     */
    public GridSquarePanel(Game game, int rows, int cols) {
        this.game = game;
        this.rows = rows;
        this.cols = cols;
        initComponents();
        update();
    }
    
    /**
     * Updates the state of the UI grid panel.
     */
    public void update() {

        Position pos = new Position(game.getCurrentMap(), cols, rows);
        GridSquare gs = game.getCurrentMap().getGridSquare(pos);
        
        setBackground(Color.BLACK);
        repaint();
    }
    
    @Override
    /**
     * Main method for painting the objects on the Grid Panel
     * @param g the graphics object, updates the loaded images
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Position pos = new Position(game.getCurrentMap(), rows, cols);
        GridSquare gs = game.getCurrentMap().getGridSquare(pos);
        jLabel1.setVisible(true);
        
        if (gs.isVisible()) {
            // Player, Battle, Trade images
            if (gs.hasPlayer()) {
                if (gs.getOccupant() == null) {
                    jLabel1.setIcon(new ImageIcon("components/player.png"));
                } else {
                    if (gs.getOccupant() instanceof Creature) {
                        jLabel1.setIcon(new ImageIcon("components/battle.png"));
                    } else {
                        jLabel1.setIcon(new ImageIcon("components/trade.png"));
                    }
                }
            } else {
                if (gs.getOccupant() != null) {
                    // Occupant Images
                    if (gs.getOccupant().getName().equals("Snake")) {
                        jLabel1.setIcon(new ImageIcon("components/snake.png"));
                    }
                    if (gs.getOccupant().getName().equals("Troll")) {
                        jLabel1.setIcon(new ImageIcon("components/troll.png"));
                    }
                    if (gs.getOccupant().getName().equals("Giant")) {
                        jLabel1.setIcon(new ImageIcon("components/giant.png"));
                    }
                    if (gs.getOccupant().getName().equals("Orc")) {
                        jLabel1.setIcon(new ImageIcon("components/orc.png"));
                    }
                    if (gs.getOccupant().getName().equals("Water Elemental")) {
                        jLabel1.setIcon(new ImageIcon("components/elemental.png"));
                    }
                    if (gs.getOccupant().getName().equals("Ice Witch")) {
                        jLabel1.setIcon(new ImageIcon("components/icewitch.png"));
                    }
                    if (gs.getOccupant().getName().equals("Dragon")) {
                        jLabel1.setIcon(new ImageIcon("components/dragon.png"));
                    }
                    if (gs.getOccupant().getName().equals("Crystal Spider")) {
                        jLabel1.setIcon(new ImageIcon("components/crystalspider.png"));
                    }
                    if (gs.getOccupant().getName().equals("Vampire")) {
                        jLabel1.setIcon(new ImageIcon("components/vampire.png"));
                    }
                    if (gs.getOccupant().getName().equals("Warlock")) {
                        jLabel1.setIcon(new ImageIcon("components/warlock.png"));
                    }
                    if (gs.getOccupant().getName().equals("Demon")) {
                        jLabel1.setIcon(new ImageIcon("components/demon.png"));
                    }
                    if (gs.getOccupant().getName().equals("Juggernaut")) {
                        jLabel1.setIcon(new ImageIcon("components/juggernaut.png"));
                    }
                    if (gs.getOccupant().getName().equals("Alf")) {
                        jLabel1.setIcon(new ImageIcon("components/npc.png"));
                    }
                } else {
                    jLabel1.setVisible(false);
                }
            }
        }

        // Terrain Images
        if (gs.isVisible()) {
            if (gs.getTerrain() == Terrain.SAND) {
                bck.setIcon(new ImageIcon("components/sand.png"));
            }

            if (gs.getTerrain() == Terrain.WATER) {
                bck.setIcon(new ImageIcon("components/water.png"));
            }

            if (gs.getTerrain() == Terrain.GRASS) {
                bck.setIcon(new ImageIcon("components/grass.png"));
            }

            if (gs.getTerrain() == Terrain.DIRT) {
                bck.setIcon(new ImageIcon("components/dirt.png"));
            }

            if (gs.getTerrain() == Terrain.TREE) {
                bck.setIcon(new ImageIcon("components/tree.png"));
            }

            if (gs.getTerrain() == Terrain.SNOW) {
                bck.setIcon(new ImageIcon("components/snow.png"));
            }

            if (gs.getTerrain() == Terrain.ROCK) {
                bck.setIcon(new ImageIcon("components/rock.png"));
            }

            if (gs.getTerrain() == Terrain.BRIDGE) {
                bck.setIcon(new ImageIcon("components/bridge.png"));
            }

            if (gs.getTerrain() == Terrain.CAVE) {
                bck.setIcon(new ImageIcon("components/cave.png"));
            }

            if (gs.getTerrain() == Terrain.MUD) {
                bck.setIcon(new ImageIcon("components/mud.png"));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bck = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(50, 50));
        setPreferredSize(new java.awt.Dimension(50, 50));
        setSize(new java.awt.Dimension(50, 50));

        bck.setPreferredSize(new java.awt.Dimension(50, 50));
        bck.setSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bck;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
