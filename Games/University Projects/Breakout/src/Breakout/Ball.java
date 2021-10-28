/**
 * @author James Oliver (1279126) & Luke Harper (1092144)
 * @date 04/04/14
 */

package Breakout;

import java.awt.*;

public class Ball
{
	public int x, y;
	public final int radius;
	public final Color colour;
	public double dirX, dirY;
	public final int speed = 8;
	
	public Ball (int x, int y, int radius, Color colour)
	{
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.colour = colour;
            dirX = 0;
            dirY = 1.0;
	}
	
	public void paintThis(Graphics g)
	{
            g.setColor(this.colour);
            g.fillOval(x - radius, y - radius, radius*2, radius*2);
	}
	
	public Rectangle getBounds()
	{
            Rectangle ball = new Rectangle(x - radius, y - radius, radius*2, radius*2);
            return ball;
	}
	
        
	public void move() {
            x -= dirX * speed;
            y -= dirY * speed;
	}
	
	public void reflect(boolean verticle, boolean horizontal) {
            if(verticle)
                    dirX *= -1;
            if(horizontal)
                    dirY *= -1;       
	}
}