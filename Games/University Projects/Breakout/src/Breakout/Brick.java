/**
 * @author James Oliver (1279126) & Luke Harper (1092144)
 * @date 04/04/14
 * 
 * *EXTENSIONS
 * COLOR IS NO LONGER FINAL IN ORDER TO CHANGE THE COLOR TO PINK TO REPRESENT
 * A SPECIAL BRICK.
 */

package Breakout;

import java.awt.*;

public class Brick {
    
	public final int topLeftX, topLeftY, width, height;
	public Color colour;
        public boolean isSpecial;
	
	public Brick (int topLeftX, int topLeftY, int width, int height, Color colour) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.width = width;
		this.height = height;
		this.colour = colour;
                isSpecial = false;
                
                
	}
	
	public void paintThis(Graphics g) {
            g.setColor(colour);
            g.fillRect(topLeftX, topLeftY, width, height);
	}
	
	public Rectangle getBounds() {
            Rectangle rec = new Rectangle(topLeftX, topLeftY, width, height);
            return rec;
	}
}