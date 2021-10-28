/**
 * @author James Oliver (1279126) & Luke Harper (1092144)
 * @date 04/04/14
 */
package Breakout;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;

public class Breakout extends TimerTask {

    public Vector<Brick> bricks;
    public Vector<Ball> balls;
    public Ball ball;
    public Paddle paddle;
    public PaintingPanel panel;
    public boolean gameRunning;

    // GAME METHODS
    public Breakout() {
        //CREATE PADDLE, BALL, BRICKS
        Vector<Object> contents = new Vector<Object>();
        panel = new PaintingPanel(contents);
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(545, 500));

        bricks = new Vector<Brick>();
        paddle = new Paddle();
        paddle.centreX = 545 / 2;
        paddle.centreY = 500 - 10;
        contents.add(paddle);

        for (int i = 0; i < 5; ++i) {
            int x = 0, y = 0;
            if (i != 0) {
                y += 25 * i;
            }
            //SET COLOUR FOR DIFFERENT BRICK LEVELS
            Color g = Color.RED;
            if (y == 25) {
                g = Color.BLUE;
            }
            if (y == 50) {
                g = Color.GREEN;
            }
            if (y == 75) {
                g = Color.YELLOW;
            }
            if (y == 100) {
                g = Color.ORANGE;
            }
            //SET BRICK LAYOUT
            while (x < 500) {
                Brick brick = new Brick(x, y, 50, 20, g);
                //SPECIAL BRICK THAT RELEASES 2 BALLS
                if ((brick.topLeftX == 110 && brick.topLeftY == 50)) {
                    brick.colour = Color.PINK;
                    brick.isSpecial = true;
                }
                //EXTENSION** SPECIAL BRICK ADJUSTS PADDLE SIZE
                if((brick.topLeftX == 385 && brick.topLeftY == 50)) {
                    brick.colour = Color.PINK;
                }
                bricks.add(brick);
                contents.add(brick);
                x += 55;
            }
        }
        balls = new Vector<Ball>();
        ball = new Ball(545 / 2, 500 - 25, 5, Color.WHITE);
        balls.add(ball);
        contents.add(ball);
        showGUI(panel, this);
    }
    //BEGIN GAME
    public void beginGame() {
        gameRunning = true;
    }
    //TIMER RUN IMPLEMENTATION
    @Override
    public void run() {
        if (gameRunning) {

            for (Ball ball : balls) {
                ball.move();
            }
            handleCollisions();
            panel.repaint();
        }
    }
    //STOP GAME - WIN/LOSE
    public void stopGame() {
        gameRunning = false;
    }
    //CLOSSISION METHODS
    public void handleCollisions() {

        boolean isSpecial = false;
        if (bricks.isEmpty()) {
            stopGame();
        }
        int removeBallIndex = -1;

        for (Ball ball : balls) {
            if (ball.y > 500) {
                removeBallIndex = balls.indexOf(ball);
            }
            if (ball.x < 8 || ball.x > 540) {
                if (ball.x < 8) {
                    ball.x = 8;
                }
                if (ball.x > 540) {
                    ball.x = 540;
                }
                ball.reflect(true, false);
            }
            if (ball.y < 5) {
                ball.y = 5;
                ball.reflect(false, true);
            }
            //REMOVE BRICK ONCE COLLIDED
            for (Brick brick : bricks) {
                if (ball.getBounds().intersects(brick.getBounds())) {
                    ball.reflect(false, true);
                    bricks.remove(brick);
                    panel.contents.remove(brick);
                    if (brick.isSpecial) {
                        isSpecial = true;
                    }
                    if (!brick.isSpecial && brick.colour == Color.PINK)
                        paddle.PADDLE_SIZE = 75;
                    break;
                }
            }
            //EXTENSION* BALL PROJECTION RESPONDS TO THE LOCATION IT INTERSECTS
            //ON THE PADDLE.
            if (ball.getBounds().intersects(paddle.getBounds())) {
                double dist = ball.x - paddle.centreX;
                dist *= -1;
                ball.dirX = dist / 100;
                ball.reflect(false, true);
            }
        }
        //YOU LOSE
        if (removeBallIndex != -1) {
            balls.remove(removeBallIndex);
        }
        if (balls.isEmpty()) {
            stopGame();
            panel.gameEnded = true;
        }
        
        //BALL NO.2 THREAD
        if (isSpecial) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    Ball ball = new Ball(160, 75, 5, Color.WHITE);
                    ball.reflect(false, true);

                    balls.add(ball);
                    panel.contents.add(ball);
                    if (gameRunning) {
                        ball.move();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            });
            thread.start();
        }
        //BALL NO.3 THREAD
        if (isSpecial) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    Ball ball = new Ball(110, 75, 5, Color.WHITE);
                    ball.reflect(false, true);
                    balls.add(ball);
                    panel.contents.add(ball);
                    if (gameRunning) {
                        ball.move();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            });
            thread.start();
        }
    }
        //MAIN METHOD
    public static void main(String[] args) {
        Timer t = new Timer();
        t.schedule(new Breakout(), 0, 40);
    }
        //BREAKOUT GUI
    public static void showGUI(PaintingPanel panel, Breakout game) {
        JFrame frame = new JFrame("Breakout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.addKeyListener(game.new KeyBoardHandler());
        frame.setSize(new Dimension(545, 500));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private class KeyBoardHandler extends KeyAdapter {
        //KEY HANDLER TO MOVE PADDLE AND START GAME.
        //**EXTENSION** PADDLE SIZE HANDLER
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    paddle.centreX -= 20;
                    //EXTENSION HANDLES PADDLE SIZE ADJUSTMENT ONCE
                    //SPECIAL BRICK HIT
                    if(paddle.PADDLE_SIZE == 75) {
                        if(paddle.centreX <= 38)
                            paddle.centreX = 38;
                    } else
                        if (paddle.centreX <= 75) paddle.centreX = 75;
                    break;
                case KeyEvent.VK_RIGHT:
                    paddle.centreX += 20;
                    if(paddle.PADDLE_SIZE == 75) {
                        if (paddle.centreX >= 507) 
                            paddle.centreX = 507;
                    } else
                        if(paddle.centreX >= 470) paddle.centreX = 470;
                    break;
                case KeyEvent.VK_SPACE:
                    beginGame();
                    break;
            }
        }
    }
}
