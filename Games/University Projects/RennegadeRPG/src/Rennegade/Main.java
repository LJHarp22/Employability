package Rennegade;

import RennegadeGUI.*;
import rennegaderpg.*;

/**
 * 
 * @author LukeHarper 1092144
 * @version PDC Assignment 2014 Semester 1
 */
public class Main {
    
    /**
     * Main method of Rennegade RPG
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Loads the start menu
        final Menu startMenu = new Menu();
        startMenu.setVisible(true);
        
        while (startMenu.isVisible()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        // Loads the main game GUI
        final Game newGame = new Game (startMenu.getAccount());
        final RennegadeGUI gameGUI = new RennegadeGUI(newGame);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gameGUI.setVisible(true);
            }
        });
    }
}
