/**
 * @author James Oliver (1279126) & Luke Harper (1092144)
 * @date 04/04/14
 * 
 * *EXTENSIONS
 * PADDLE_SIZE IS NO LONGER FINAL TO ADJUST FOR THE SECOND SPECIAL BRICK.
 */

package Breakout;

import java.awt.*;

public class Paddle {
    
	public static int PADDLE_SIZE = 150;
	public static final int PADDLE_THICKNESS = 20;
	public int centreX;
        public int centreY;
	
	public void paintThis(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(centreX - PADDLE_SIZE / 2, centreY - PADDLE_THICKNESS / 2, PADDLE_SIZE, PADDLE_THICKNESS);
	}
	
	public Rectangle getBounds() {
		Rectangle rec = new Rectangle(centreX - PADDLE_SIZE / 2, centreY - PADDLE_THICKNESS / 2, PADDLE_SIZE, PADDLE_THICKNESS);
                return rec;
	}
}