package nz.ac.aut.prog2.ass3;

import nz.ac.aut.prog2.ass3.gameModel.Game;
import nz.ac.aut.prog2.ass3.gui.LemurIslandUI;

/**
 * Starting Project for P2 Assignment 3.
 * 
 * @author Anne Philpott & Stefan Marks
 * @version S2 2011
 */
public class Main 
{
    /**
     * Main method of Lemur Island.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // create the game object
        final Game game = new Game();
        // create the GUI for the game
        final LemurIslandUI  gui  = new LemurIslandUI(game);
        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                gui.setVisible(true);
            }
        });
    }

}
