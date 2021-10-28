/**
 * @author James Oliver (1279126) & Luke Harper (1092144)
 * @date 04/04/14
 */

package Breakout;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class PaintingPanel extends JPanel {
        
	public Vector<Object> contents;
        public boolean gameEnded;
	
	public PaintingPanel(Vector<Object> contents) {
             this.contents = contents;
	}
	
        @Override
	public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int score = 0;
            int brickCount = 50;
            int brickRemaining = 0;
            for(Object obj : contents) {
                if(obj instanceof Brick) {
                    Brick b = (Brick) obj;
                    b.paintThis(g);
                    ++brickRemaining;
                }
                if(obj instanceof Paddle) {
                    Paddle p = (Paddle) obj;
                    p.paintThis(g);
                }
                if(obj instanceof Ball) {
                    Ball b = (Ball) obj;
                    b.paintThis(g);
                }
            }
            score = (brickCount - brickRemaining) * 100;
            String brickScore = Integer.toString(score);
            
            //SCORE / YOU WIN / YOU LOSE
            g.setColor(Color.YELLOW);
            Font font = new Font("Arabia", Font.BOLD, 18);
            g.setFont(font);
            int width = g.getFontMetrics().stringWidth("Score : ");
            if(score == 5000)
                g.drawString("YOU WIN!", (getWidth()-width)/2,getHeight()/2 );
            else 
                if(gameEnded)
                    g.drawString("YOU LOSE!", (getWidth()-width)/2,getHeight()/2 );
             else
                g.drawString("Score : " + brickScore, (getWidth()-width)/2,getHeight()/2 );
        }
}

